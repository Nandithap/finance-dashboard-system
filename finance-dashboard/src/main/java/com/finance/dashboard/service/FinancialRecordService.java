package com.finance.dashboard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.repository.FinancialRecordRepository;

@Service
public class FinancialRecordService {

    @Autowired
    private FinancialRecordRepository recordRepository;

    // Save record
    public FinancialRecord saveRecord(FinancialRecord record) {
        return recordRepository.save(record);
    }

    // Get all records
    public List<FinancialRecord> getAllRecords() {
        return recordRepository.findAll();
    }

    // Get record by ID
    public FinancialRecord getRecordById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found with id " + id));
    }

    // Get records by user ID
    public List<FinancialRecord> getRecordsByUserId(Long userId) {
        return recordRepository.findByUserId(userId);
    }

    // Get records by category
    public List<FinancialRecord> getRecordsByCategory(String category) {
        return recordRepository.findByCategory(category);
    }

    // Get records by type
    public List<FinancialRecord> getRecordsByType(String type) {
        return recordRepository.findByType(type);
    }

    // Update record
    public FinancialRecord updateRecord(Long id, FinancialRecord newRecord) {
        FinancialRecord record = recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(newRecord.getAmount());
        record.setCategory(newRecord.getCategory());
        record.setDate(newRecord.getDate());
        record.setDescription(newRecord.getDescription());
        record.setType(newRecord.getType());

        return recordRepository.save(record);
    }

    // Delete record by ID
    public void deleteRecordById(Long id) {
        recordRepository.deleteById(id);
    }

    // Delete all records
    public void deleteAllRecords() {
        recordRepository.deleteAll();
    }
}