package com.finance.dashboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.finance.dashboard.entity.FinancialRecord;
import com.finance.dashboard.entity.User;
import com.finance.dashboard.repository.FinancialRecordRepository;
import com.finance.dashboard.repository.UserRepository;

@Service
public class DashboardService {

    @Autowired
    private FinancialRecordRepository recordRepository;

    @Autowired
    private UserRepository userRepository;

    // ---------------- Role Helper ----------------

    public String getUserRole(Long userId) {
        User user = userRepository.findById(userId).get();
        return user.getRole().getRoleName();
    }

    // ---------------- Existing methods ----------------

    public double getTotalIncome() {
        List<FinancialRecord> records = recordRepository.findAll();
        return records.stream()
                .filter(r -> r.getType().equalsIgnoreCase("income"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double getTotalExpense() {
        List<FinancialRecord> records = recordRepository.findAll();
        return records.stream()
                .filter(r -> r.getType().equalsIgnoreCase("expense"))
                .mapToDouble(FinancialRecord::getAmount)
                .sum();
    }

    public double getNetBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    // ---------------- Summary methods (All users) ----------------

    public Map<String, Double> getCategorySummary() {
        List<Object[]> results = recordRepository.getCategorySummary();
        Map<String, Double> summary = new HashMap<>();
        for (Object[] row : results) {
            summary.put((String) row[0], (Double) row[1]);
        }
        return summary;
    }

    public Map<String, Double> getMonthlySummary() {
        List<Object[]> results = recordRepository.getMonthlySummary();
        Map<String, Double> summary = new HashMap<>();
        for (Object[] row : results) {
            Integer year = (Integer) row[0];
            Integer month = (Integer) row[1];
            Double total = (Double) row[2];
            String key = year + "-" + (month < 10 ? "0" + month : month);
            summary.put(key, total);
        }
        return summary;
    }

    // ---------------- User-specific summary methods ----------------

    public Map<String, Double> getCategorySummary(Long userId) {
        List<Object[]> results = recordRepository.getCategorySummaryByUser(userId);
        Map<String, Double> summary = new HashMap<>();
        for (Object[] row : results) {
            summary.put((String) row[0], (Double) row[1]);
        }
        return summary;
    }

    public Map<String, Double> getMonthlySummary(Long userId) {
        List<Object[]> results = recordRepository.getMonthlySummaryByUser(userId);
        Map<String, Double> summary = new HashMap<>();
        for (Object[] row : results) {
            Integer year = (Integer) row[0];
            Integer month = (Integer) row[1];
            Double total = (Double) row[2];
            String key = year + "-" + (month < 10 ? "0" + month : month);
            summary.put(key, total);
        }
        return summary;
    }

    // ---------------- Role-Based Access Methods ----------------

    public Map<String, Double> getCategorySummaryByRole(Long userId) {
        String role = getUserRole(userId);

        if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("ANALYST")) {
            return getCategorySummary(); // all users
        } else {
            return getCategorySummary(userId); // only own data
        }
    }

    public Map<String, Double> getMonthlySummaryByRole(Long userId) {
        String role = getUserRole(userId);

        if (role.equalsIgnoreCase("ADMIN") || role.equalsIgnoreCase("ANALYST")) {
            return getMonthlySummary(); // all users
        } else {
            return getMonthlySummary(userId); // only own data
        }
    }
}