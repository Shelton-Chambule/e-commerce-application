package com.shelton.e_commerce.service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.shelton.e_commerce.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class TokenService {

    @Value("${e-commerce.api}")
    private String secretKey;

    public String generatedToken(User user){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("e-commerce.application")
                    .withSubject(user.getEmail())
                    .withExpiresAt(expireToken())
                    .sign(algorithm);
        }catch (JWTCreationException exception){
            throw new IllegalStateException("Error while genetation token!");
        }
    }

    public String validateToken (String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.require(algorithm)
                    .withIssuer("e-commerce.application")
                    .build()
                    .verify(token)
                    .getSubject();
        }catch (JWTVerificationException exception){
            return "";  // colocar um mensagem de erro pra a validação do token
        }
    }

    public Instant expireToken(){
        return Instant.now().plus(2, ChronoUnit.HOURS);
    }

}
