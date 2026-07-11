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
mvn spring-boot:run or ./mvnw spring-boot:run
```
*Note: Make sure your target database server is active and running locally before booting up the application.*

---

## 🏗️ Core Architectural Modules & API Specifications

The platform is engineered around six tightly-coupled operational modules. Each module encapsulates distinct business logic, database guardrails, and dedicated endpoint routing matrices to maintain strict transactional integrity:

### 1. Book Catalog System
Handles robust physical asset inventory tracking including validation loops on unique ISBN inputs, state flags for clean system soft-deletes, and high-throughput batch operations.
- `POST /api/books` ➔ Register a single book instance into catalog stock.
- `POST /api/books/bulk` ➔ Stream array payloads for heavy-duty pipeline database insertions.
- `GET /api/books/isbn/{isbn}` ➔ Instantly extract matching volumes via raw barcode scanner indexing.
- `DELETE /api/books/{id}` ➔ Execute safe inventory archival sequences (Soft-Delete).

### 2. Recursive Genre Hierarchy
Implements an unlimited, multi-tier self-referential nested tree model architecture (`Fiction ➔ Mystery ➔ Detective Fiction`). Employs custom bi-directional helper functions inside the database layer to keep complex parent-child node linkages synchronously accurate across relational queries.
- `POST /api/genres` ➔ Set up brand new root nodes or configure target sub-level parent anchors.
- `GET /api/genres/active/hierarchy` ➔ Traverses hierarchy logs recursively to pull complete active category subtrees.
- `GET /api/genres/search` ➔ Request paginated metadata queries supporting sort criteria loops.

### 3. Circulation & Book Loan Pipeline
A stateful application loan engine executing automated transaction tracking flags (`CHECKED_OUT`, `RETURNED`, `OVERDUE`). Runs automated profile validation routines to enforce allocation boundaries and actively lock delinquent users out of system assets.
- `POST /api/book-loans/checkout` ➔ Spin up active user borrowing allocation streams.
- `POST /api/book-loans/renew` ➔ Request time extension parameters if constraint thresholds pass verification.

### 4. Verified Book Review Hub
Allows client review additions backed by database optimization indices (`idx_book_id`, `idx_user_id`) to accelerate dashboard metrics. Implements multi-column unique constraints targeting user and book pairs to eliminate feedback spamming or duplicate rating injections.
- `POST /api/reviews` ➔ Post comprehensive star ratings and commentary content logs (Verified readers only).
- `GET /api/reviews/book/{bookId}/statistics` ➔ Pull aggregate breakdown distributions and multi-score averages.

### 5. Decoupled Fine Ledger Subsystem
Isolates financial balance penalty logs from core volatile loan records to guarantee audit compliance. Tracks independent calculations to maintain real-time status points (`PENDING`, `PARTIALLY_PAID`, `PAID`, `WAIVED`) and dynamic balance reductions upon incoming transaction hooks.
- `POST /api/fines/{id}/pay` ➔ Initiate transaction processing to clear outstanding liability tabs.
- `POST /api/fines/waive` ➔ Administrative workflow path to dismiss penalty dues with required override justification logging.

### 6. Dynamic Reservation Queues
An asynchronous event-driven hold module managing asset allocation optimization when active book volumes hit zero copies. Automatically transitions waiting states and tracks line counts through priority metrics.
- `POST /api/reservations` ➔ Enqueue consumer profiles into real-time item hold queues.
- `GET /api/reservations/{id}/queue-position` ➔ Instantly map queue positions and return a scannable context-text indicator tracking wait positions.


## 🛡️ Business Rules & System Validations

The application utilizes a multi-layered verification strategy to guarantee absolute database integrity. Incoming transactional requests must sequentially satisfy the business logic pipeline below before data operations are committed to storage:

```text
                        Incoming Request Pipeline
                                   │
                                   ▼
   [ Guardrail 1: ISBN Unique Check ] ───(Violated)───► [ 409 Conflict Response ]
                                   │
                                   ▼
   [ Guardrail 2: Inventory > 0 Check ] ─(Violated)───► [ 400 Bad Request / Hold Queue ]
                                   │
                                   ▼
   [ Guardrail 3: Account Clear Check ] ─(Violated)───► [ 409 Delinquency Block ]
                                   │
                                   ▼
                        Secured Database Commit
```

### 📋 Deep-Dive Validation Guardrails

1. **ISBN Uniqueness (Identity Guardrail)**
   - **Database Rule:** The `isbn` column is configured with an absolute database-level uniqueness constraint.
   - **Pipeline Execution:** Payloads attempting duplication fail the initial entity validation sweep instantly.
   - **Exception Mapping:** The global controller advice intercepts the data constraint violation to return a structured `409 Conflict` response to the client.

2. **Copy Control Integrity (Operational Inventory Bounds)**
   - **Database Rule:** Available book copies are guarded by strict application logic thresholds requiring:
     $$\text{availableCopies} \ge 0 \quad \text{AND} \quad \text{availableCopies} \le \text{totalCopies}$$
   - **Pipeline Execution:** Circulation requests check live stock metrics before modifying records.
   - **Exception Mapping:** If units match zero, checkouts are blocked with a `400 Bad Request` or safely routed into the asynchronous queuing logic of the `Reservation Module`.

3. **Delinquency Freezes (Circulation Eligibility)**
   - **Database Rule:** Active loan state machines audit borrower account status parameters prior to modifying assets.
   - **Pipeline Execution:** Background cron scanners flag overdue materials (`status == OVERDUE`) and track unpaid balance liabilities.
   - **Exception Mapping:** Profiles flagged with delinquent markers or matching max extension thresholds (`renewalCount == maxRenewals`) are dynamically blocked via a `409 Delinquency Block` from requesting system renewals.

---

## 🤝 Connect & Support

Thank you for checking out my Production-Grade Library Management System! This project represents my commitment to clean architecture, type-safe business rules, and scalable database design. 

If you are a tech recruiter, an open-source contributor, or a fellow software engineering student, I would love to connect with you!

<p align="left"><a href="mailto:mrstudieshelper@gmail.com"><img src="https://shields.io" alt="Email Me"/></a> <a href="https://linkedin.com"><img src="https://shields.io" alt="LinkedIn"/></a> <a href="https://leetcode.com"><img src="https://shields.io" alt="LeetCode"/></a></p>



### 📄 License
This repository is open-source and licensed under the **MIT License**. Feel free to fork it, explore the code optimization frameworks, or adapt the database schemas for your own workflows.

***
*“Even if the world loses its value, I won’t lose mine.” — Built with discipline and daily momentum by Surya V.*

