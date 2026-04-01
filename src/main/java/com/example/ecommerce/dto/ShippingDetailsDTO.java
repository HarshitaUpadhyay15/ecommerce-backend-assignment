package com.example.ecommerce.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShippingDetailsDTO {
    @NotBlank(message = "Shipping details cannot be blank")
    private String shippingDetails;
}
