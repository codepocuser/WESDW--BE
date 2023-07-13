package com.cncbinternational.websocketpoc.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
@Component
public class JwtHelper {


    @Value("${jwt-variables.KEY}")
    private String KEY;

    @Value("${jwt-variables.ISSUER}")
    private String ISSUER;

    @Value("${jwt-variables.EXPIRES_ACCESS_TOKEN_MINUTE}")
    private Integer EXPIRES_ACCESS_TOKEN_MINUTE;


    public String createJwtToken(String username) {

        Algorithm algorithm = Algorithm.HMAC256(KEY.getBytes());

        return JWT.create().withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + (EXPIRES_ACCESS_TOKEN_MINUTE * 60 * 1000)))
                .withIssuedAt(convertToDateViaSqlDate(LocalDate.now()))
                .withIssuer(ISSUER)
                .withClaim("roles", "ROLE_USER").sign(algorithm);
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }


}
