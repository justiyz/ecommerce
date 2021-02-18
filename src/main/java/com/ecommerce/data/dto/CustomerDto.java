package com.ecommerce.data.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomerDto {

    private String email;

    private String firstName;

    private String lastName;

    private String contact;
}
