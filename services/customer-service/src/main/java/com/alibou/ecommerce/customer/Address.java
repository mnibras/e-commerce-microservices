package com.alibou.ecommerce.customer;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class Address {

    private String street;
    private String houseNumber;
    private String zipCode;

}
