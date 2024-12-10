package com.doan.cnpm.services;

import com.doan.cnpm.dto.request.CartResponseDTO;
import com.doan.cnpm.dto.request.OrdersRequestDTO;
import com.doan.cnpm.entity.*;
import com.doan.cnpm.mapper.OrderMapper;
import com.doan.cnpm.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
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


    public void AddToCart(OrdersRequestDTO ordersRequestDTO) {

        if ( ordersRequestDTO.getCustomerId() == null || ordersRequestDTO.getProductId() == null
        || ordersRequestDTO.getProductId() == null  || ordersRequestDTO.getQuantity() == null) {
            throw new RuntimeException("lỗi k truyền đủ biến");
        }

        // lấy ra product
        Optional<Product> product = productRepository.findById(ordersRequestDTO.getProductId());
        if( product.isEmpty() ) {
            throw new RuntimeException("truyền sai productId");
        }

        // Tạo mới Order nếu chưa có
        Optional<Orders> orders = ordersRepository.findOrdersByCustomerID(ordersRequestDTO.getCustomerId());
        Orders order = new Orders();
        if( orders.isEmpty() ) {
            order.setDate(ordersRequestDTO.getDate());
            order.setStatus(Orders.Status.pending); // Trạng thái mặc định
            Random random = new Random();
            long id = random.nextInt(10) + 1; // Tạo số từ 1 đến 10
            Employee employee = employeeRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Customer customer = customerRepository.findById(ordersRequestDTO.getCustomerId())
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

    public List<CartResponseDTO> ShoppingCart(Long customerID) {
        if ( customerID == null ) {
            throw new RuntimeException("chưa truyền customerID bây ơi!!");
        }
        return orderMapper.toCartResponseDTOs(ordersRepository.findAllProductCart(customerID));
    }

    public void orderCheckOut(Long customerID) {
        // kiểm tra input đầu vào
        if ( customerID == null ) {
            throw new RuntimeException("Vui lòng truyền customerID vào !!");
        }
        //kiểm tra giỏ hàng của người đó có đang có đồ hay không
        Orders order = ordersRepository.findOrderByCustomerIDAndStatusPending(customerID)
                .orElseThrow(() -> new RuntimeException("Không có sản phẩm nào trong giỏ hàng, vui lòng thêm sản phẩm!"));

        order.setStatus(Orders.Status.completed);
        ordersRepository.save(order);
    }


    public void orderRemoveItem(Long customerID, Long productID) {
        if ( customerID == null || productID == null ) {
            throw new RuntimeException("truyền thiếu biến!! hoặc chưa truyền đủ biến!!");
        }
        Orders order = ordersRepository.findOrderByCustomerIDAndStatusPending(customerID)
                .orElseThrow(() -> new RuntimeException("Không có sản phẩm nào trong giỏ hàng, " +
                        "thì sao mà xóa được, truyền sai rùi...!"));
        includeRepository.deleteItemInCart(order.getOrderID(), productID);
    }
}
