package com.example.SoapServerProject.web.service;

import localhost._8080.*;

public interface UserService {

    AddUserResponse addUser(UserRequest userRequest);

    UpdateUserResponse updateUser(int id, UserRequest userRequest);

    DeleteUserByIdResponse deleteUser(int id);

    FindUserByIdResponse findUserById(int id);

    FindAllUsersResponse findAll();
}
