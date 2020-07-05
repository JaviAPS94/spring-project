package com.kenect.kenectspringtest.data.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ContactDto {
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private List<String> phones;
    @NotNull
    private List<String> emails;
    @NotNull
    private List<AddressDto> addresses;
}
