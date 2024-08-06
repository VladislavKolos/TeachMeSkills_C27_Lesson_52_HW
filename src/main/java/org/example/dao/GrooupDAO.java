package org.example.dao;

import org.example.model.Grooup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrooupDAO extends JpaRepository<Grooup, Integer> {

    boolean existsByTitle(String title);

}
