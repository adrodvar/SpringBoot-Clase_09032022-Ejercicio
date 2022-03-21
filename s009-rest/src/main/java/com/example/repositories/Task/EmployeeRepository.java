package com.example.repositories.Task;

import com.example.entities.TaskDTO.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

        @Transactional
        @Query("Select m from Employee m where m.name = :name")
        List<Employee> findByName(String name);
}
