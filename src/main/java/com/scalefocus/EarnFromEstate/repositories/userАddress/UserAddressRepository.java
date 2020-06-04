package com.scalefocus.EarnFromEstate.repositories.userАddress;

import com.scalefocus.EarnFromEstate.entities.UserAddress;

public interface UserAddressRepository {

    UserAddress getUserAddressByAllFields(final UserAddress userAddress);

    int saveUserAddress(final UserAddress userAddress);

}
