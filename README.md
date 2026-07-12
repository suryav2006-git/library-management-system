# 📚 Production-Grade Library Management System (Java Full-Stack)

A robust, enterprise-ready full-stack application designed to handle complex library lifecycles, real-time tracking, recursive catalog categorization, and financial audit logging.

---

## 🛠️ Tech Stack & Ecosystem

- **Backend:** Java, Spring Boot, Spring Data JPA, Hibernate
- **Database:** Relational Database (SQL-compliant) with performance indices
- **Architecture:** Monolithic REST API with Data Transfer Object (DTO) patterns
- **Data Formats:** Strict JSON payload handling with comprehensive exception mapping

---

## 📊 Core Architecture & Relational Entities

The system relies on six deeply coupled operational entities to ensure business rules remain transactional across the application lifecycle:

### 1. Book Catalog System
- Maps a core product lifecycle with automated validations.
- Implements comprehensive tracking across fields like unique `isbn` strings, soft-delete boolean tracking flags (`active`), and auto-managed timestamps.
- Includes bulk operation controller endpoints (`POST /api/books/bulk`) to fast-track catalog scaling.

### 2. Recursive Genre Hierarchy
- Supports an unlimited nested sub-genre tree architecture structure (`Fiction ➔ Mystery ➔ Detective Fiction`).
- Configured with a self-referential `parentGenre` field mapping and high-performance querying indices on indexed data categories (`code`, `name`, `active`).

### 3. Book Loan & Checkout Pipeline
- Manages the transactional borrowing workflow from initialization to check-in.
- Uses a stateful `BookLoanStatus` ENUM (`CHECKED_OUT`, `RETURNED`, `OVERDUE`) coupled with dynamic logic thresholds preventing renewals on delinquent or maxed accounts.

### 4. Automated Fine & Transaction Audit
- Decouples financial penalization from raw loans to enforce granular monitoring, waivers, and transaction tracking.
- Evaluates real-time statuses (`PENDING`, `PARTIALLY_PAID`, `PAID`, `WAIVED`) and dynamic balance reductions through internal methods.

### 5. Multi-User Waiting Queue (Reservations)
- Implements automated queuing patterns when targeted inventory numbers drop to zero copies.
- Features lifecycle transitions along with real-time queue position lookup strategies (`GET /api/reservations/{id}/queue-position`).

### 6. Subscription Membership Tracks
- Segregates user groups into specific multi-tier access plans like `GOLD_001` or `PLATINUM_001`.
- Enforces runtime checks evaluating maximum concurrent book bounds and personalized checkout window policies.

---

## 🚀 Quick Setup Instructions

Get the project up and running locally in less than two minutes:

```bash
# 1. Clone the repository
git clone https://github.com/suryav2006-git/library-management-system.git

# 2. Build the application and download dependencies
mvn clean install

# 3. Boot up the Spring Boot application
mvn spring-boot:run
```
*Note: Make sure your target database server is active and running locally before booting up the application.*

---

## 🤝 Connect & Support

Thank you for checking out my Production-Grade Library Management System! This project represents my commitment to clean architecture, type-safe business rules, and scalable database design. 

If you are a tech recruiter, an open-source contributor, or a fellow software engineering student, I would love to connect with you!

<p align="left">
  <a href="mailto:mrstudieshelper@gmail.com"><img src="https://shields.io" alt="Email Me"/></a> 
  <a href="https://www.linkedin.com/in/suryav2006-lin"><img src="https://shields.io" alt="LinkedIn"/></a> 
  <a href="https://leetcode.com/u/suryav2006-lee/"><img src="https://shields.io" alt="LeetCode"/></a>
</p>

---

## 🎓 Acknowledgements & Credits

While every line of this codebase was written, typed, and debugged by hand to master these architectural patterns, the foundational design, database schema concepts, and full-stack integration strategies were inspired by the tutorials on the **[Code With Zosh YouTube Channel](https://youtube.com/@codewithzosh)**.

---

## 📄 License
This repository is open-source and licensed under the **MIT License**. Feel free to fork it, explore the code optimization frameworks, or adapt the database schemas for your own workflows.

***
*“Even if the world loses its value, I won’t lose mine.” — Built with discipline and daily momentum by Surya V.*
