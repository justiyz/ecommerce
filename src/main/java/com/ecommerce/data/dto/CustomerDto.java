package com.ecommerce.data.dto;

import com.ecommerce.data.model.Gender;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class CustomerDto {

    private String email;

    private String firstName;

    private String lastName;

    private String contact;

    private Gender gender;
}
