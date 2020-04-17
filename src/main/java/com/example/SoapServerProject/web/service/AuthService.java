package com.example.SoapServerProject.web.service;

import localhost._8080.ChangePasswordResponse;
import localhost._8080.LoginResponse;
import localhost._8080.RegisterRequest;
import localhost._8080.RegisterResponse;

public interface AuthService {

    LoginResponse login(String login, String password);

    RegisterResponse register(RegisterRequest registerRequest);

    ChangePasswordResponse changePassword(int userId, String oldPassword, String newPassword, String confirmPassword);
}
