package org.example.dao;

import org.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {

    List<Student> findAllByName(String name);

    List<Student> findAllBySurname(String surname);

    List<Student> findAllByGroupId(Integer groupId);

    List<Student> findAllByGroupName(String groupName);

    List<Student> findByPaymentsIsEmpty();

    @Query(value = "UPDATE Student s SET s.group.id = :newGroupId WHERE s.id = :studentId")
    @Modifying
    void updateGroupById(@Param("studentId") Integer studentId, @Param("newGroupId") Integer newGroupId);

    boolean existsByName(String name);

    boolean existsBySurname(String surname);
}
