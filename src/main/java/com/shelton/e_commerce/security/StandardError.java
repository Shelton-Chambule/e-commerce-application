package com.shelton.e_commerce.security;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
public class StandardError {

    private Instant timeStamp;
    private  Integer status;
    private String error;
    private String message;
    private String path;

}
