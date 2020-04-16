package com.example.SoapServerProject.web.serviceImpl;

import com.example.SoapServerProject.db.dao.UserDAO;
import com.example.SoapServerProject.db.daoModel.User;
import com.example.SoapServerProject.web.service.UserService;
import localhost._8080.*;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public InfoResponse addUser(UserRequest userRequest) {
        User userFromDb = userDAO.findUserByUserLogin(userRequest.getUserLogin());
        if (userFromDb == null) {
            User user = new User()
                    .withUserName(userRequest.getUserName())
                    .withUserLastName(userRequest.getUserLastName())
                    .withUserLogin(userRequest.getUserLogin())
                    .withUserPassword(userRequest.getUserPassword())
                    .withIsAdmin(Boolean.FALSE);
            userDAO.save(user);

            return createInfoResponseMessage("Successfully added user");
        } else {
            throw new ServiceException("Login must be unique");
        }
    }

    @Override
    public InfoResponse updateUser(int id, UserRequest userRequest) {
        if (userRequest.getUserLogin().equals("admin")) {
            throw new ServiceException("You can't modify administrator");
        }

        User userFromDb = userDAO.findUserByUserLogin(userRequest.getUserLogin());
        if (userFromDb == null) {
            User user = userDAO.findUserById(id);
            if ( user != null) {
                if (!userRequest.getUserName().isEmpty() && userRequest.getUserName() != null) {
                    user.setUserName(userRequest.getUserName());
                }
                if (!userRequest.getUserLastName().isEmpty() && userRequest.getUserLastName() != null) {
                    user.setUserLastName(userRequest.getUserLastName());
                }
                if (!userRequest.getUserLogin().isEmpty() && userRequest.getUserLogin() != null) {
                    user.setUserLogin(userRequest.getUserLogin());
                }
                if (!userRequest.getUserPassword().isEmpty() && userRequest.getUserPassword() != null) {
                    user.setUserPassword(userRequest.getUserPassword());
                }
                userDAO.save(user);

                return createInfoResponseMessage("Successfully updated user");
            } else {
                throw new ServiceException("User with given id is not existed");
            }
        } else {
            throw new ServiceException("Login must be unique");
        }
    }

    @Override
    public InfoResponse deleteUser(int id) {
        if (userDAO.findUserById(id) != null) {

            if (userDAO.findUserById(id).getUserLogin().equals("admin")) {
                throw new ServiceException("You can't deleted administrator");
            }

            userDAO.deleteUserById(id);

            return createInfoResponseMessage("Successfully deleted user");
        } else {
            throw new ServiceException("User with given id is not existed");
        }
    }

    @Override
    public FindUserByIdResponse findUserById(int id) {
        User user = userDAO.findUserById(id);
        FindUserByIdResponse findUserByIdResponse = new FindUserByIdResponse();
        UserResponse userResponse = new UserResponse();

        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setUserLastName(user.getUserLastName());
        userResponse.setUserLogin(user.getUserLogin());

        findUserByIdResponse.setUser(userResponse);
        return findUserByIdResponse;
    }

    @Override
    public FindAllUsersResponse findAll() {
        List<User> userListFromDb = userDAO.findAll();
        FindAllUsersResponse findAllUsersResponse = new FindAllUsersResponse();

        for (User userFromDb : userListFromDb) {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(userFromDb.getId());
            userResponse.setUserName(userFromDb.getUserName());
            userResponse.setUserLastName(userFromDb.getUserLastName());
            userResponse.setUserLogin(userFromDb.getUserLogin());
            findAllUsersResponse.getUsersList().add(userResponse);
        }

        return findAllUsersResponse;
    }

    public InfoResponse createInfoResponseMessage(String message) {
        InfoResponse infoResponse = new InfoResponse();
        infoResponse.setInfo(message);
        return infoResponse;
    }
}
