package com.finance.dashboard.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.finance.dashboard.entity.FinancialRecord;

@Repository
public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    // Existing methods
    List<FinancialRecord> findByCategory(String category);
    List<FinancialRecord> findByType(String type);
    List<FinancialRecord> findByUserId(Long userId);

    // ---------------- All-users summaries ----------------

    // Category Summary: Sum of amounts grouped by category (all users)
    @Query("SELECT f.category, SUM(f.amount) FROM FinancialRecord f GROUP BY f.category")
    List<Object[]> getCategorySummary();

    // Monthly Summary: Sum of amounts grouped by year-month (all users, H2-compatible)
    @Query("SELECT FUNCTION('YEAR', f.date), FUNCTION('MONTH', f.date), SUM(f.amount) " +
           "FROM FinancialRecord f " +
           "GROUP BY FUNCTION('YEAR', f.date), FUNCTION('MONTH', f.date)")
    List<Object[]> getMonthlySummary();

    // ---------------- User-specific summaries ----------------

    // Category Summary for a specific user
    @Query("SELECT f.category, SUM(f.amount) " +
           "FROM FinancialRecord f " +
           "WHERE f.user.id = :userId " +
           "GROUP BY f.category")
    List<Object[]> getCategorySummaryByUser(Long userId);

    // Monthly Summary for a specific user (H2-compatible)
    @Query("SELECT FUNCTION('YEAR', f.date), FUNCTION('MONTH', f.date), SUM(f.amount) " +
           "FROM FinancialRecord f " +
           "WHERE f.user.id = :userId " +
           "GROUP BY FUNCTION('YEAR', f.date), FUNCTION('MONTH', f.date)")
    List<Object[]> getMonthlySummaryByUser(Long userId);
}