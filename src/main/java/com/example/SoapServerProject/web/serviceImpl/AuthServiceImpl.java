package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.UserDAO;
import com.example.SoapServerProject.db.daoModel.User;
import com.example.SoapServerProject.web.service.AuthService;
import localhost._8080.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserDAO userDAO;


    @Override
    public LoginResponse login(String login, String password) {
        LoginResponse loginResponse = new LoginResponse();
        User userFromDb = userDAO.findUserByUserLogin(login);
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
    public InfoResponse register(RegisterRequest registerRequest) {
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
                return createInfoResponseMessage("User has been registered");
            } else {
                return createInfoResponseMessage("Passwords is different");
            }
        } else {
            return createInfoResponseMessage("User login already exist");
        }
    }

    @Override
    public InfoResponse changePassword(int userId, String oldPassword, String newPassword, String confirmPassword) {
        User userFromDb = userDAO.findUserById(userId);
        if (newPassword.equals(confirmPassword)) {
            if (userFromDb.getUserPassword().equals(oldPassword)) {
                if (!userFromDb.getUserPassword().equals(newPassword)) {
                    userFromDb.setUserPassword(newPassword);
                    userDAO.save(userFromDb);
                    return createInfoResponseMessage("Password has been changed");
                } else {
                    return createInfoResponseMessage("New password can't be the same like old password");
                }
            } else {
                return createInfoResponseMessage("Old password is wrong");
            }
        } else {
            return createInfoResponseMessage("New passwords is different");
        }
    }

    public InfoResponse createInfoResponseMessage(String message) {
        InfoResponse infoResponse = new InfoResponse();
        infoResponse.setInfo(message);
        return infoResponse;
    }
}
