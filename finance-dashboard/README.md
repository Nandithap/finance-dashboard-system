# Finance Data Processing and Access Control Backend

## Project Overview

This project is a Finance Dashboard Backend System developed using Spring Boot.
It manages users, roles, financial records, and provides dashboard analytics with role-based access control.

---

## Features

* User Management
* Role Management (ADMIN, ANALYST, USER)
* Financial Record Management
* Income / Expense Tracking
* Net Balance Calculation
* Category-wise Summary
* Monthly Summary
* User-specific Dashboard
* Role-based Access Control

---

## Roles and Permissions

| Role    | Access                  |
| ------- | ----------------------- |
| ADMIN   | Full access to all data |
| ANALYST | Can view all summaries  |
| USER    | Can view only own data  |

---

## Architecture

Client → Controller → Service → Repository → Database

* Controller → Handles API requests
* Service → Business logic + role checks
* Repository → Database operations
* Entity → Database tables

---

## Technologies Used

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* H2 Database
* Maven

---

## API Endpoints

### Dashboard APIs

* `/dashboard/income`
* `/dashboard/expense`
* `/dashboard/balance`
* `/dashboard/category-summary`
* `/dashboard/monthly-summary`
* `/dashboard/category-summary/user?userId=1`
* `/dashboard/monthly-summary/user?userId=1`
* `/dashboard/category-summary/role?userId=1`
* `/dashboard/monthly-summary/role?userId=1`

---

## H2 Database

URL:
http://localhost:8080/h2-console

JDBC URL:
jdbc:h2:mem:testdb

Username:
sa

Password:
(empty)

---

## How to Run

1. Import project into IDE
2. Run Spring Boot application
3. Open browser or Postman
4. Test APIs
5. Open H2 console

---

## Summary

This project demonstrates backend development using Spring Boot with REST APIs, database integration, dashboard analytics, and role-based access control using a clean layered architecture.
The backend follows layered architecture where the Controller handles API requests, the Service layer contains business logic and role-based access control, the Repository layer interacts with the database using JPA, and Entities represent database tables. The system manages users, roles, financial records, and dashboard analytics such as income, expenses, category summaries, and monthly summaries.