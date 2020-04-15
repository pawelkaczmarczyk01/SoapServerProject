package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User findUserById(int id);

    User findUserByUserLogin(String userLogin);

    void deleteUserById(int id);
}
