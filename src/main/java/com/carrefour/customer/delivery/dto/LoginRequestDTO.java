package com.carrefour.customer.delivery.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class LoginRequestDTO {

    @NotBlank
    private String username;
    @NotBlank
    private String password;

}
