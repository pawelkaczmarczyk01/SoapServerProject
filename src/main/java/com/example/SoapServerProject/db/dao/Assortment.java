package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.AssortmentDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Assortment extends JpaRepository<AssortmentDao, Integer> {
}
