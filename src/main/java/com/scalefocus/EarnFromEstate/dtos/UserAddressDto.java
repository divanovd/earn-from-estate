package com.scalefocus.EarnFromEstate.dtos;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class UserAddressDto implements Serializable {

    private static final long serialVersionUID = -1562659218622375504L;

    @NotNull
    @NotEmpty
    private String country;

    @NotNull
    @NotEmpty
    private int postCode;

    @NotNull
    @NotEmpty
    private String city;

    @NotNull
    @NotEmpty
    private String street;

    @NotNull
    @NotEmpty
    private String streetNumber;
}
