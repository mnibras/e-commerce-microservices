package com.alibou.ecommerce.kafka.order;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private String id;
    private String firstname;
    private String lastname;
    private String email;

}
