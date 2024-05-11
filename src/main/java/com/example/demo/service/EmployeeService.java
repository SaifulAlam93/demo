package com.example.demo.service;



import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {


    @Autowired
    public EmployeeRepo repo;

    public List<Employee> getData() {
        return repo.findAll();
    }

    public Employee save(Employee employee) {
        return repo.save(employee);
    }

    public void deleteData(Long id) {
        repo.deleteById(id);
    }

    public Employee getDataByID(Long id) {
        return repo.findById(id).get();
    }


    public List<Employee> getDataByDg(String dg) {
        return repo.findByDesignation(dg);
    }

    public List<Employee> getDataByDgAndJd(String dg, LocalDate jd) {
        return repo.findByDesignationAndJoiningDate(dg, jd);
    }


    public List<Employee> getDataByJd(LocalDate startDate, LocalDate endDate) {

        return repo.getDataByJD(startDate, endDate);
    }
    
}
