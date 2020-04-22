package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.UserDAO;
import com.example.SoapServerProject.db.daoModel.User;
import com.example.SoapServerProject.web.service.AuthService;
import localhost._8080.*;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public LoginResponse login(String login, String password) throws InterruptedException {
        LoginResponse loginResponse = new LoginResponse();
        User userFromDb = userDAO.findUserByUserLogin(login);
        getAsyncValue();
        if (userFromDb == null) {
            throw new ServiceException("Login or password is invalid");
        }
        if (userFromDb.getUserPassword().equals(password)) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(userFromDb.getId());
            userResponse.setUserLogin(userFromDb.getUserLogin());
            userResponse.setUserName(userFromDb.getUserName());
            userResponse.setUserLastName(userFromDb.getUserLastName());

            loginResponse.setIsAdmin(userFromDb.getIsAdmin());
            loginResponse.setUser(userResponse);
            return loginResponse;
        } else {
            throw new ServiceException("Login or password is invalid");
        }
    }

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User userFromDao = userDAO.findUserByUserLogin(registerRequest.getUser().getUserLogin());
        if (userFromDao == null) {
            if (registerRequest.getUser().getUserPassword().equals(registerRequest.getUser().getUserConfirmPassword())) {
                User user = new User()
                        .withIsAdmin(Boolean.FALSE)
                        .withUserName(registerRequest.getUser().getUserName())
                        .withUserLastName(registerRequest.getUser().getUserLastName())
                        .withUserLogin(registerRequest.getUser().getUserLogin())
                        .withUserPassword(registerRequest.getUser().getUserPassword());
                userDAO.save(user);
                return createRegisterResponseMessage("User has been registered");
            } else {
                return createRegisterResponseMessage("Passwords is different");
            }
        } else {
            return createRegisterResponseMessage("User login already exist");
        }
    }

    @Override
    public ChangePasswordResponse changePassword(int userId, String oldPassword, String newPassword, String confirmPassword) {
        User userFromDb = userDAO.findUserById(userId);
        if (newPassword.equals(confirmPassword)) {
            if (userFromDb.getUserPassword().equals(oldPassword)) {
                if (!userFromDb.getUserPassword().equals(newPassword)) {
                    userFromDb.setUserPassword(newPassword);
                    userDAO.save(userFromDb);
                    return createChangePasswordResponseMessage("Password has been changed");
                } else {
                    return createChangePasswordResponseMessage("New password can't be the same like old password");
                }
            } else {
                return createChangePasswordResponseMessage("Old password is wrong");
            }
        } else {
            return createChangePasswordResponseMessage("New passwords is different");
        }
    }

    public RegisterResponse createRegisterResponseMessage(String message) {
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setInfo(message);
        return registerResponse;
    }

    public ChangePasswordResponse createChangePasswordResponseMessage(String message) {
        ChangePasswordResponse changePasswordResponse = new ChangePasswordResponse();
        changePasswordResponse.setInfo(message);
        return changePasswordResponse;
    }

    public static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Async("threadPoolTaskExecutor")
    public void getAsyncValue() throws InterruptedException {
        logger.info("async job started: " + Thread.currentThread().getId());
        Thread.sleep(1000);
        logger.info("async job finished: " + Thread.currentThread().getId());
    }
}
