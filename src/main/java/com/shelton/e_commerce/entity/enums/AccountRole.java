package com.shelton.e_commerce.entity.enums;
public enum AccountRole {

    ADMIN("admin"),
    USER("user");

   private String role;

    AccountRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
