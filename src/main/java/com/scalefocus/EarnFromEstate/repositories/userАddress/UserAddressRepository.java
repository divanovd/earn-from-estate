package com.scalefocus.EarnFromEstate.repositories.userАddress;

import com.scalefocus.EarnFromEstate.entities.UserAddress;

public interface UserAddressRepository {

    UserAddress getUserAddressByAllFields(UserAddress userAddress);

    UserAddress getUserAddressById(Long id);

    int saveUserAddress(UserAddress userAddress);

}
