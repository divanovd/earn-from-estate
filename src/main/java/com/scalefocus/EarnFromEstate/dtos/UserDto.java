package com.scalefocus.EarnFromEstate.dtos;

import com.scalefocus.EarnFromEstate.validators.ValidEmail;
import com.scalefocus.EarnFromEstate.validators.ValidPassword;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
public class UserDto implements Serializable {

    private static final long serialVersionUID = -1170561944941405880L;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    @ValidPassword
    private String password;

    @NotNull
    @NotEmpty
    private String matchingPassword;

    @NotNull
    @NotEmpty
    private String mobilePhone;

    private UserAddressDto userAddress;
}
