package com.kenect.kenectspringtest.data.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AddressDto {
    @NotNull
    private String street;
    @NotNull
    private String streetNumber;
    @NotNull
    private String reference;
    @NotNull
    private Long city;
}
