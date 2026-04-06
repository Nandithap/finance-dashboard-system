package com.finance.dashboard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.service.FinancialRecordService;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    @Autowired
    private FinancialRecordService recordService;

    // Create a new financial record
    @PostMapping
    public FinancialRecord createRecord(@RequestBody FinancialRecord record) {
        return recordService.saveRecord(record);
    }

    // Get all financial records
    @GetMapping
    public List<FinancialRecord> getAllRecords() {
        return recordService.getAllRecords();
    }

    // Get record by ID
    @GetMapping("/{id}")
    public FinancialRecord getRecordById(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    // Get records by user ID
    @GetMapping("/user/{userId}")
    public List<FinancialRecord> getRecordsByUser(@PathVariable Long userId) {
        return recordService.getRecordsByUserId(userId);
    }

    // Get records by category
    @GetMapping("/category/{category}")
    public List<FinancialRecord> getRecordsByCategory(@PathVariable String category) {
        return recordService.getRecordsByCategory(category);
    }

    // Get records by type (INCOME / EXPENSE)
    @GetMapping("/type/{type}")
    public List<FinancialRecord> getRecordsByType(@PathVariable String type) {
        return recordService.getRecordsByType(type);
    }

    // Update financial record
    @PutMapping("/{id}")
    public FinancialRecord updateRecord(@PathVariable Long id, @RequestBody FinancialRecord record) {
        return recordService.updateRecord(id, record);
    }

    // Delete record by id
    @DeleteMapping("/{id}")
    public String deleteRecord(@PathVariable Long id) {
        recordService.deleteRecordById(id);
        return "Record deleted successfully!";
    }

    // Delete all financial records
    @DeleteMapping
    public String deleteAllRecords() {
        recordService.deleteAllRecords();
        return "All financial records deleted successfully!";
    }
}