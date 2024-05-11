package com.example.demo.controller;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
public class EmpController {


    //GET
    //GGE_TALL
    //Post
    //PUT,
    //DELETE

    @GetMapping("/all")
    public String allAccess() {
        return "Public Content GET_ ALL.";
    }

    @PostMapping
    public String createEmp(@RequestParam ("val") String abc){
        return abc + "Data posted";
    }


    @PutMapping("/user")
//    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "Data updated!!";
    }

    @DeleteMapping("/mod")
//    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Data Deleted.";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}
