package org.example.dao;

import org.example.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentDAO extends JpaRepository<Student, Integer> {

    Optional<Student> findByName(String name);

    Optional<Student> findBySurname(String surname);

    List<Student> findByGrooupId(int groupId);

    List<Student> findByGrooupTitle(String groupName);

    List<Student> findByPaymentsIsEmpty();

    Optional<Student> findById(int studentId);

    @Query("UPDATE Student s SET s.grooup.id = :newGroupId WHERE s.id = :studentId")
    @Modifying
    void updateGrooupById(@Param("studentId") int studentId, @Param("newGroupId") int newGroupId);

    boolean existsByName(String name);

    boolean existsBySurname(String surname);

    boolean existsByAge(int age);
}
