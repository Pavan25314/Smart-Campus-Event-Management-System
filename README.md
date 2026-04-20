# Campus Event Management System

A comprehensive Spring Boot application for universities to manage events, workshops, and seminars.

## ✨ Features

### 🎓 For Students
- **Browse Events**: View upcoming events with filtering by department and type.
- **Visual Feedback**: Real-time capacity indicators and event ratings visible on cards.
- **Easy Registration**: Simple form with validation to sign up for events.
- **Registration History**: View all registered events by entering your email.
- **Post-Event Feedback**: Provide star ratings and comments for events you've attended.

### 🛡️ For Admins
- **Secure Dashboard**: Access restricted to authorized personnel.
- **Full CRUD**: Create, Update, and Delete events with ease.
- **Advanced Filtering**: Filter events by date range, department, and type in the administrative view.
- **Analytics Dashboard**: 
  - Visual charts (using Chart.js) for event popularity.
  - Key Performance Indicators (Total registrations, Fill rates).
  - Average ratings tracking.

## 🚀 Technical Stack
- **Backend**: Spring Boot 3, Spring Data JPA, Spring Security.
- **Database**: H2 (In-memory, with H2 Console enabled at `/h2-console`).
- **Frontend**: Thymeleaf templates, CSS3 (Custom Design System with Glassmorphism).
- **Validation**: Jakarta Bean Validation for forms.
- **Exception Handling**: Global `@ControllerAdvice` for robust error management.

## ⚙️ How to Run
1. Clone the repository.
2. Ensure you have **JDK 17** installed.
3. Run the application using Maven:
   ```bash
   mvn spring-boot:run
   ```
4. Access the application at: `http://localhost:8080`

## 🔑 Default Credentials
- **Admin Portal**: 
  - URL: `http://localhost:8080/admin/dashboard`
  - Username: `admin`
  - Password: `admin123`

## 📜 Project Structure
- `com.campus.events.model`: JPA Entities (Event, Registration).
- `com.campus.events.repository`: Data Access Layer (Spring Data JPA).
- `com.campus.events.service`: Business Logic.
- `com.campus.events.controller`: MVC and REST Controllers.
- `resources/templates`: Thymeleaf HTML views.
- `resources/static/css`: Custom styling.
