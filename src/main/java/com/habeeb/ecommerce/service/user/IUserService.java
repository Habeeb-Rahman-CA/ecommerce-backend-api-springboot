package com.habeeb.ecommerce.service.user;

import com.habeeb.ecommerce.model.User;
import com.habeeb.ecommerce.request.CreateUserReq;
import com.habeeb.ecommerce.request.UpdateUserReq;

public interface IUserService {

    User getUserById(Long userId);
    User createUser(CreateUserReq req);
    User updateUser(UpdateUserReq req, Long userId);
    void deleteUser(Long userId);

}
