package com.doan.cnpm.services;

import com.doan.cnpm.dto.request.CartResponseDTO;
import com.doan.cnpm.dto.request.OrdersRequestDTO;
import com.doan.cnpm.dto.response.DetailOrderResponseDTO;
import com.doan.cnpm.dto.response.HistoryResponse;
import com.doan.cnpm.dto.response.ListOrderResponseDTO;
import com.doan.cnpm.dto.response.TotalSaleTodayResponseDTO;
import com.doan.cnpm.entity.*;
import com.doan.cnpm.mapper.OrderMapper;
import com.doan.cnpm.mapper.TotalSaleTodayMapper;
import com.doan.cnpm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final IncludeRepository includeRepository;
    private final OrderMapper orderMapper;
    private final TotalSaleTodayMapper totalSaleTodayMapper;
    private final CustomerService customerService;


    public void AddToCart(OrdersRequestDTO ordersRequestDTO) {
        Long customerId = customerService.myInfo().getCustomerID();
        if ( customerId == null || ordersRequestDTO.getProductId() == null
                || ordersRequestDTO.getQuantity() == null) {
            throw new RuntimeException("lỗi k truyền đủ biến");
        }

        // lấy ra product
        Optional<Product> product = productRepository.findById(ordersRequestDTO.getProductId());
        if( product.isEmpty() ) {
            throw new RuntimeException("truyền sai productId");
        }

        // Tạo mới Order nếu chưa có
        Optional<Orders> orders = ordersRepository
                .findOrdersByCustomerID(customerId);
        Orders order = new Orders();
        if( orders.isEmpty() ) {
            order.setDate(ordersRequestDTO.getDate());
            order.setStatus(Orders.Status.pending); // Trạng thái mặc định
            Random random = new Random();
            long id = random.nextInt(10) + 1; // Tạo số từ 1 đến 10
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Customer customer = customerRepository.findById(customerId)
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            order.setEmployee(employee); // Liên kết với Employee
            order.setCustomer(customer);
            ordersRepository.save(order);
        }
        else{
            order = orders.get();
        }


        Optional<Include> existingInclude = includeRepository.findByOrdersAndProduct(order, product.get());
        Include include = new Include();
        if (existingInclude.isEmpty()) {

            // Nếu sản phẩm chưa có trong đơn hàng, tạo mới Include và thiết lập IncludePK
            IncludePK includePK = new IncludePK(); // Khởi tạo IncludePK
            includePK.setOrderID(order.getOrderID()); // Gán orderID từ order
            includePK.setProductID(product.get().getProductID()); // Gán productID từ product

            include.setId(includePK); // Thiết lập khóa chính kết hợp
            include.setOrders(order);
            include.setProduct(product.get());
            include.setQuantity(ordersRequestDTO.getQuantity());
        } else {
            // Nếu sản phẩm đã có trong đơn hàng, cập nhật số lượng
            include = existingInclude.get();
            include.setQuantity(include.getQuantity() + ordersRequestDTO.getQuantity());
        }
        // 6. Lưu danh sách Include vào cơ sở dữ liệu
        includeRepository.save(include);

        // 7. Tính tổng giá trị đơn hàng và cập nhật lại Order
        double totalPrice = 0;
        List<Include> allIncludes = includeRepository.findAllByOrders(order); // Lấy tất cả Include cho Order
        for (Include inc : allIncludes) {
            totalPrice += inc.getProduct().getPriceSelling() * inc.getQuantity(); // Tính giá trị tổng cho mỗi Include
        }

        // Cập nhật lại tổng giá trị đơn hàng
        order.setTotalPrice(totalPrice);

        // Lưu lại Order với tổng giá trị mới
        ordersRepository.save(order);
    }

    public List<CartResponseDTO> ShoppingCart() {
        Long id = customerService.myInfo().getCustomerID();
        if ( id == null ) {
            throw new RuntimeException("Bị looix đăng nhập thì phải??");
        }
        return orderMapper.toCartResponseDTOs(ordersRepository.findAllProductCart(id));
    }

    public void orderCheckOut() {
        Long customerID = customerService.myInfo().getCustomerID();
        // kiểm tra input đầu vào
        if ( customerID == null ) {
            throw new RuntimeException("Vui lòng đăng nhập !!");
        }
        //kiểm tra giỏ hàng của người đó có đang có đồ hay không
        Orders order = ordersRepository.findOrderByCustomerIDAndStatusPending(customerID)
                .orElseThrow(() -> new RuntimeException("Không có sản phẩm nào trong giỏ hàng, vui lòng thêm sản phẩm!"));

        order.setStatus(Orders.Status.completed);
        order.setDate(LocalDate.now());
        ordersRepository.save(order);
    }


    public void orderRemoveItem(Long productID) {
        Long customerID = customerService.myInfo().getCustomerID();
        if ( customerID == null || productID == null ) {
            throw new RuntimeException("truyền thiếu biến!! hoặc chưa truyền đủ biến!!");
        }
        Orders order = ordersRepository.findOrderByCustomerIDAndStatusPending(customerID)
                .orElseThrow(() -> new RuntimeException("Không có sản phẩm nào trong giỏ hàng, " +
                        "thì sao mà xóa được, truyền sai rùi...!"));
        includeRepository.deleteItemInCart(order.getOrderID(), productID);
    }

//    =========================================================================================
//    ADMIN
    public TotalSaleTodayResponseDTO totalSalesToday(){
        LocalDate today = LocalDate.now();
        Object[] object = ordersRepository.totalPriceSellingToday(today);
        object = (Object[]) object[0];
        TotalSaleTodayResponseDTO mapper = totalSaleTodayMapper.toTotalSaleTodayResponseDTO(object);
        return mapper;
    }

//    danh sách order cho admin
    public List<ListOrderResponseDTO> listOrders() {
        return orderMapper.toListOrderResponseDTOs(ordersRepository.listOrdersForAdmin());
    }


    public List<HistoryResponse> getHistoryProductOrder(){
        Long customerID = customerService.myInfo().getCustomerID();
        List<Object[]> results = ordersRepository.getHistoryProductBuyed(customerID);

        List<HistoryResponse> list = new ArrayList<>();
        for (Object[] o : results) {
            HistoryResponse item = HistoryResponse.builder()
                    .name((String) o[0])
                    .brand((String) o[1])
                    .material((String) o[2])
                    .price_selling((Double) o[3])
                    .product_id((Long) o[4])
                    .quantity((Long) o[5])
                    .total_price((Double) o[6])
                    .image((String) o[7])
                    .build();

            list.add(item);
        }

        return list;
    }

//   danh sách sản phẩm của order và thông tin của customer đó
    public DetailOrderResponseDTO getDetailOrder(Long orderID) {
        // Lấy dữ liệu từ repository
        List<Object[]> results = ordersRepository.AllProductInOrder(orderID);

        // Tạo danh sách sản phẩm
        List<DetailOrderResponseDTO.ProductDTO> productList = new ArrayList<>();
        String fullName = null, phoneNumber = null, emailAddress = null, shippingAddress = null;

        for (Object[] result : results) {
            // Ánh xạ từng sản phẩm
            DetailOrderResponseDTO.ProductDTO product = DetailOrderResponseDTO.ProductDTO.builder()
                    .name((String) result[0])
                    .brand((String) result[1])
                    .material((String) result[2])
                    .price_selling(((Number) result[3]).doubleValue())
                    .status(DetailOrderResponseDTO.ProductDTO.Status.valueOf((String) result[4]))
                    .quantity(((Number) result[5]).longValue())
                    .total_price(((Number) result[6]).doubleValue())
                    .image((String) result[7])
                    .build();

            productList.add(product);

            // Chỉ cần ánh xạ thông tin khách hàng một lần
            if (fullName == null) {
                fullName = (String) result[8];
                phoneNumber = (String) result[9];
                emailAddress = (String) result[10];
                shippingAddress = (String) result[11];
            }
        }

        // Tạo DTO cho chi tiết đơn hàng
        return DetailOrderResponseDTO.builder()
                .full_name(fullName)
                .phone_number(phoneNumber)
                .email(emailAddress)
                .shipping_address(shippingAddress)
                .products(productList)
                .build();
    }


}
