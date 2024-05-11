package com.example.demo.repository;

import com.example.demo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {
    List<Employee> findByDesignation(String dg);
    List<Employee> findByDesignationAndJoiningDate(String dg, LocalDate jd);
    @Query(value = " Select * from employee where joining_date between :startDate and :endDate ", nativeQuery = true)
    List<Employee> getDataByJD(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

    @Query(value = " SELECT e.name, e.designation, a.punch_time, a.punch_date , a.punch_status FROM employee e LEFT JOIN `attendance` a ON e.id = a.emp_id ", nativeQuery = true)
    List<Object[]> getData();

}
