package com.alibou.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.validation.annotation.Validated;

@Getter
@AllArgsConstructor
@Validated
public class Customer {
    private final String id;

    @NotNull(message = "Firstname is required")
    private final String firstname;

    @NotNull(message = "Lastname is required")
    private final String lastname;

    @NotNull(message = "Email is required")
    @Email(message = "The customer email is not correctly formatted")
    private final String email;
}

