# AlphaSolutions

A Spring Boot-based project management application that allows users to manage projects, subprojects, and tasks with time tracking capabilities.

HOSTED SOLUTION: https://alphasolutions8-fzbbd8bebmawhbam.northeurope-01.azurewebsites.net/

## 🚀 Features

- **User Management**: User authentication and role-based access
- **Project Management**: Create and manage projects with descriptions, deadlines, and time estimates
- **Subproject Organization**: Break down projects into manageable subprojects
- **Task Tracking**: Detailed task management with time tracking and status updates
- **Time Estimation**: Track estimated vs actual time spent on projects, subprojects, and tasks
- **Priority Management**: Set priorities for better project organization
- **Web Interface**: Thymeleaf-based web interface for easy interaction

## 🛠️ Technologies Used

- **Java 17**
- **Spring Boot 3.4.5**
- **Spring Web** - RESTful web services
- **Spring JDBC** - Database connectivity
- **Thymeleaf** - Server-side templating engine
- **MySQL** - Primary database
- **H2 Database** - In-memory database for testing
- **Maven** - Dependency management and build tool
- **Spring Boot DevTools** - Development productivity tools

## 📋 Prerequisites

Before running this application, make sure you have the following installed:

- **Java 17** or higher
- **Maven 3.6+**
- **MySQL 8.0+** (for production)
- **Git** (for cloning the repository)

## 🔧 Installation & Setup

### 1. Clone the Repository

```bash
git clone <your-repository-url>
cd alphasolutions
```

### 2. Database Setup

#### MySQL Database Setup

1. **Install MySQL** (if not already installed):
   - **macOS**: `brew install mysql`
   - **Ubuntu**: `sudo apt-get install mysql-server`
   - **Windows**: Download from [MySQL official website](https://dev.mysql.com/downloads/mysql/)

2. **Start MySQL service**:
   ```bash
   # macOS
   brew services start mysql
   
   # Ubuntu
   sudo systemctl start mysql
   
   # Windows
   # Start MySQL service from Services panel
   ```

3. **Create Database**:
   ```sql
   mysql -u root -p
   CREATE DATABASE alphasolutions;
   CREATE USER 'alphauser'@'localhost' IDENTIFIED BY 'alphapassword';
   GRANT ALL PRIVILEGES ON alphasolutions.* TO 'alphauser'@'localhost';
   FLUSH PRIVILEGES;
   EXIT;
   ```

4. **Run Database Schema**:
   ```bash
   mysql -u alphauser -p alphasolutions < src/main/resources/db/create.sql
   ```

5. **Load Test Data** (optional):
   ```bash
   mysql -u alphauser -p alphasolutions < src/main/resources/db/testdata.sql
   ```

### 3. Environment Configuration

Set the following environment variables:

```bash
export JDBC_DATABASE_URL="jdbc:mysql://localhost:3306/alphasolutions"
export JDBC_DATABASE_USERNAME="alphauser"
export JDBC_DATABASE_PASSWORD="alphapassword"
```

**For Windows (Command Prompt):**
```cmd
set JDBC_DATABASE_URL=jdbc:mysql://localhost:3306/alphasolutions
set JDBC_DATABASE_USERNAME=alphauser
set JDBC_DATABASE_PASSWORD=alphapassword
```

**For Windows (PowerShell):**
```powershell
$env:JDBC_DATABASE_URL="jdbc:mysql://localhost:3306/alphasolutions"
$env:JDBC_DATABASE_USERNAME="alphauser"
$env:JDBC_DATABASE_PASSWORD="alphapassword"
```

### 4. Build and Run the Application

#### Using Maven Wrapper (Recommended)

```bash
# Build the application
./mvnw clean compile

# Run the application
./mvnw spring-boot:run
```

#### Using Maven (if installed globally)

```bash
# Build the application
mvn clean compile

# Run the application
mvn spring-boot:run
```

### 5. Access the Application

Once the application is running, you can access it at:
- **Web Interface**: http://localhost:8080
- **Application will be available on port 8080 by default**

## 🧪 Testing

### Run Tests

```bash
# Run all tests
./mvnw test
```

### Test Database

The application uses H2 in-memory database for testing, so no additional setup is required for running tests.

## 📁 Project Structure

```
src/
├── main/
│   ├── java/org/example/alphasolutions/
│   │   ├── AlphaSolutionsApplication.java    # Main application class
│   │   ├── controller/                       # Web controllers
│   │   ├── service/                         # Business logic
│   │   ├── repository/                      # Data access layer
│   │   ├── model/                          # Entity models
│   │   └── Interfaces/                     # Interface definitions
│   └── resources/
│       ├── application.properties          # Application configuration
│       ├── db/
│       │   ├── create.sql                 # Database schema
│       │   └── testdata.sql              # Sample data
│       ├── templates/                     # Thymeleaf templates
│       └── static/                       # Static web assets
└── test/                                 # Test files
```

## 🔧 Configuration

### Application Properties

The main configuration is in `src/main/resources/application.properties`:

```properties
spring.application.name=AlphaSolutions
spring.datasource.url=${JDBC_DATABASE_URL}
spring.datasource.username=${JDBC_DATABASE_USERNAME}
spring.datasource.password=${JDBC_DATABASE_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```


