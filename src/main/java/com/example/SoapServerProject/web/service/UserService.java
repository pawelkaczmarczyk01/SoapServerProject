package com.example.SoapServerProject.web.service;

import localhost._8080.FindAllUsersResponse;
import localhost._8080.FindUserByIdResponse;
import localhost._8080.InfoResponse;
import localhost._8080.UserRequest;

public interface UserService {

    InfoResponse addUser(UserRequest userRequest);

    InfoResponse updateUser(int id, UserRequest userRequest);

    InfoResponse deleteUser(int id);

    FindUserByIdResponse findUserById(int id);

    FindAllUsersResponse findAll();
}
