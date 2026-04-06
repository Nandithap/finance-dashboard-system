package com.finance.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.entity.Role;
import com.finance.dashboard.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.saveRole(role);
    }

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
