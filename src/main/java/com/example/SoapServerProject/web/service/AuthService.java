package com.example.SoapServerProject.web.service;

import localhost._8080.InfoResponse;
import localhost._8080.LoginResponse;
import localhost._8080.RegisterRequest;
public interface AuthService {

    LoginResponse login(String login, String password);

    InfoResponse register(RegisterRequest registerRequest);

    InfoResponse changePassword(int userId, String oldPassword, String newPassword, String confirmPassword);
}
