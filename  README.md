# Quizera Backend API

Quizera is a modern **Quiz Management System** built with **Spring Boot**.
It supports role-based access for **Admin, Teacher, and Student**, allowing users to create classrooms, manage quizzes, attempt questions, and track performance.

---

## Features

### Authentication & Authorization

* User registration & login
* JWT-based authentication
* Role-based access:

    * **ADMIN**
    * **TEACHER**
    * **STUDENT**

---

### Classroom Management

* Teachers can create classrooms
* Unique class code for joining
* Students can join classrooms
* Manage class members

---

### Quiz Management

* Teachers create quizzes
* Assign quizzes to classrooms
* Public / Private quiz visibility
* Quiz duration support

---

### Question Management

* Add **single or multiple questions**
* Multiple Choice Questions (MCQ)
* Optional image upload for questions
* Bulk question creation supported

---

### Admin Dashboard

* Total users
* Total quizzes
* Total attempts
* Total flashcards
* Average score
* Top users leaderboard

---

### Leaderboard System

* Rank users based on performance
* Track XP and quiz results

---

## Tech Stack

* **Backend:** Spring Boot
* **Database:** PostgreSQL / MySQL
* **ORM:** Spring Data JPA (Hibernate)
* **Security:** Spring Security + JWT
* **Build Tool:** Gradle
* **API Testing:** Postman / Swagger

---

## Project Structure

```
src/main/java/co/istad/quizera/project
│
├── controller     # REST APIs
├── service        # Business logic
├── service/impl   # Service implementations
├── repository     # JPA repositories
├── entity         # Database models
├── dto            # Data Transfer Objects
├── mapper         # Entity ↔ DTO mapping
└── config         # Security & configuration
```

---

## Setup & Run Locally

### 1. Clone repository

```bash
git clone https://github.com/your-username/quizera-backend.git
cd quizera-backend
```

### 2. Configure database

Update `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/quizera
    username: postgres
    password: your_password
```

---

### 3. Run application

```bash
./gradlew bootRun
```

App runs at:

```
http://localhost:8080
```

---

## Authentication

Use JWT token in headers:

```
Authorization: Bearer <your_token>
```

---

## API Endpoints

### Admin

```
GET    /api/admin/dashboard
GET    /api/admin/users
DELETE /api/admin/users/{id}

GET    /api/admin/quizzes
DELETE /api/admin/quizzes/{id}

GET    /api/admin/stats/leaderboard
```

---

### Quiz

```
POST   /api/quizzes
GET    /api/quizzes
GET    /api/quizzes/{id}
DELETE /api/quizzes/{id}
```

---

### Questions

```
POST   /api/questions/{quizId}          # Single
POST   /api/questions/{quizId}/bulk     # Multiple
GET    /api/questions/quiz/{quizId}
PUT    /api/questions/{id}
DELETE /api/questions/{id}
```

---

## Deployment (Railway)

### Steps:

1. Push project to GitHub
2. Go to Railway
3. Deploy from GitHub repo
4. Add PostgreSQL database
5. Configure environment variables:

```
DB_URL=
DB_USERNAME=
DB_PASSWORD=
JWT_SECRET=
```

---

## Known Issues / Notes

* Ensure quiz always has a classroom
* Avoid null relationships in mapping
* Clean invalid foreign key data before deployment
* Use safe mapping to prevent runtime errors

---

## Future Improvements

* Pagination & filtering
* Quiz timer & auto-submit
* Real-time leaderboard
* Notification system
* Frontend (React / Next.js)

---

## License

This project is for educational purposes.
