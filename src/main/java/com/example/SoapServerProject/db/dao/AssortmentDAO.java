package com.example.SoapServerProject.db.dao;

import com.example.SoapServerProject.db.daoModel.Assortment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssortmentDAO extends JpaRepository<Assortment, Integer> {

    Assortment findAssortmentById(int id);

    void deleteAssortmentById(int id);
}
