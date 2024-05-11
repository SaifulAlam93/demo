package com.example.demo.controller;


import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/emp")
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class EmployeeController {

    @Autowired
    public EmployeeService service;

    @GetMapping("/getAll")
    public List<Employee> getData() {
        return service.getData();
    }

    @GetMapping("/getByDg")
    public List<Employee> getDataByDg(@RequestParam("dg") String dg) {
        return service.getDataByDg(dg);
    }


    @GetMapping("/getByDgAndJd")
    public List<Employee> getDataByDgAndJoinDate(
            @RequestParam("dg") String dg,
            @RequestParam("jd") LocalDate jd) {
        return service.getDataByDgAndJd(dg, jd);
    }


    @GetMapping("/getByJd")
    public List<Employee> getDataByJD(
            @RequestParam("start") LocalDate startDate,
            @RequestParam("end") LocalDate endDate) {
        return service.getDataByJd(startDate, endDate);
    }



    @PostMapping
    public Employee addData(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getDataByID(@PathVariable("id") Long id) {
        return service.getDataByID(id);
    }

    @PutMapping
    public Employee updateData(@RequestBody Employee employee) {
        return service.save(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteData(@PathVariable("id") Long id) {
         service.deleteData(id);
    }

}
