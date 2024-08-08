package org.example.dao;

import org.example.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupDAO extends JpaRepository<Group, Integer> {

    boolean existsByName(String name);

}
