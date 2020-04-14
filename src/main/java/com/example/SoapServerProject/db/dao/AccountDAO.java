package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends JpaRepository<Account, Integer> {
}
