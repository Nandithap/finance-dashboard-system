package com.finance.dashboard.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    // ---------------- Existing endpoints ----------------

    @GetMapping("/income")
    public double getTotalIncome() {
        return dashboardService.getTotalIncome();
    }

    @GetMapping("/expense")
    public double getTotalExpense() {
        return dashboardService.getTotalExpense();
    }

    @GetMapping("/balance")
    public double getNetBalance() {
        return dashboardService.getNetBalance();
    }

    @GetMapping("/category-summary")
    public ResponseEntity<Map<String, Double>> getCategorySummary() {
        return ResponseEntity.ok(dashboardService.getCategorySummary());
    }

    @GetMapping("/monthly-summary")
    public ResponseEntity<Map<String, Double>> getMonthlySummary() {
        return ResponseEntity.ok(dashboardService.getMonthlySummary());
    }

    // ---------------- User-specific endpoints ----------------

    @GetMapping("/category-summary/user")
    public ResponseEntity<Map<String, Double>> getCategorySummaryByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getCategorySummary(userId));
    }

    @GetMapping("/monthly-summary/user")
    public ResponseEntity<Map<String, Double>> getMonthlySummaryByUser(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getMonthlySummary(userId));
    }

    // ---------------- Role-based endpoints ----------------

    @GetMapping("/category-summary/role")
    public ResponseEntity<Map<String, Double>> getCategorySummaryByRole(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getCategorySummaryByRole(userId));
    }

    @GetMapping("/monthly-summary/role")
    public ResponseEntity<Map<String, Double>> getMonthlySummaryByRole(@RequestParam Long userId) {
        return ResponseEntity.ok(dashboardService.getMonthlySummaryByRole(userId));
    }
}