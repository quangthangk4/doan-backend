package com.doan.cnpm.services;

import com.doan.cnpm.dto.request.AuthenticationRequest;
import com.doan.cnpm.dto.request.IntrospectRequest;
import com.doan.cnpm.dto.response.AuthenticationResponse;
import com.doan.cnpm.dto.response.IntrospectResponse;
import com.doan.cnpm.exception.AppException;
import com.doan.cnpm.exception.ErrorCode;
import com.doan.cnpm.repository.CustomerRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final CustomerRepository customerRepository;

    @NonFinal
    @Value("${jwt.signerkey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(IntrospectRequest request)
            throws JOSEException, ParseException {
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());
        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospectResponse.builder()
                .valid(verified && expiration.after(new Date()))
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var user = customerRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User không tồn tại!"));

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());

        // trả về lỗi nếu không matches mật khẩu
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        var token = generateToken(authenticationRequest.getEmail(), user.getCustomerID());

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();

    }

    // tạo token
    private String generateToken(String email,Long customerId) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(email)
                .issuer("http://localhost:3000/")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("customerId", customerId)
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }
}
