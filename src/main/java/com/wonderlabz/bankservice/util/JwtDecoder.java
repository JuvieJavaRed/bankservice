package com.wonderlabz.bankservice.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Service;

@Service
public class JwtDecoder {

    private String secret = "wonderlabzthebusiness";

    public String  decodeJwtToken(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().toString();
    }
}
