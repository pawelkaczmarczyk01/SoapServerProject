package com.example.SoapServerProject.web.endpoints;

import com.example.SoapServerProject.web.service.UserService;
import localhost._8080.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoints {

    private static final String NAMESPACE_URI = "http://localhost:8080";

    @Autowired
    private UserService userService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addUserRequest")
    @ResponsePayload
    public AddUserResponse addUser(@RequestPayload AddUserRequest addUserRequest) {
        return userService.addUser(addUserRequest.getUser());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest updateUserRequest) {
        return userService.updateUser(updateUserRequest.getId(), updateUserRequest.getUser());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteUserByIdRequest")
    @ResponsePayload
    public DeleteUserByIdResponse deleteUser(@RequestPayload DeleteUserByIdRequest deleteUserByIdRequest) {
        return userService.deleteUser(deleteUserByIdRequest.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findUserByIdRequest")
    @ResponsePayload
    public FindUserByIdResponse findUserById(@RequestPayload FindUserByIdRequest findUserByIdRequest) {
        return userService.findUserById(findUserByIdRequest.getId());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "findAllUsersRequest")
    @ResponsePayload
    public FindAllUsersResponse findAllUser() {
        return userService.findAll();
    }

}
