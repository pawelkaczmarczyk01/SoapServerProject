package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.AccountDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Account extends JpaRepository<AccountDao, Integer> {
}
