package com.alibou.ecommerce.customer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerResponse {

    private String id;
    private String firstname;
    private String lastname;
    private String email;

}
