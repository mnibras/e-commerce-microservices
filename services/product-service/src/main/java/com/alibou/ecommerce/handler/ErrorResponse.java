package com.alibou.ecommerce.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

    private Map<String, String> errors; // from @Valid. Eg: Key = FirstName, Value = Customer firstname is required

}
