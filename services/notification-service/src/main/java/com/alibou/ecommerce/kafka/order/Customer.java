package com.alibou.ecommerce.kafka.order;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Customer {

    private String id;
    private String firstname;
    private String lastname;
    private String email;

}
