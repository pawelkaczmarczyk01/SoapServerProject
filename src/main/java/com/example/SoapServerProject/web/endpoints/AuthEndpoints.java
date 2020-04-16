package com.example.SoapServerProject.web.endpoints;

import com.example.SoapServerProject.web.service.AuthService;
import localhost._8080.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class AuthEndpoints {

    @Autowired
    private AuthService authService;

    private static final String NAMESPACE_URI = "http://localhost:8080";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "loginRequest")
    @ResponsePayload
    public LoginResponse login(@RequestPayload LoginRequest loginRequest) {
        return authService.login(loginRequest.getLogin(), loginRequest.getPassword());
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerRequest")
    @ResponsePayload
    public InfoResponse register(@RequestPayload RegisterRequest registerRequest) {
        return authService.register(registerRequest);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "changePasswordRequest")
    @ResponsePayload
    public InfoResponse changePassword(@RequestPayload ChangePasswordRequest changePasswordRequest) {
        return authService.changePassword(changePasswordRequest.getUserId(),
                changePasswordRequest.getOldPassword(),
                changePasswordRequest.getNewPassword(),
                changePasswordRequest.getConfirmPassword());
    }
}
