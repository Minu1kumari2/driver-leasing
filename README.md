# 🚗 Driver Leasing Management System

A full-featured **REST API backend** for managing driver and vehicle leasing operations, built with **Java 21** and **Spring Boot 3.5**.

---

## 🛠️ Tech Stack

| Technology | Usage |
|---|---|
| Java 21 | Core language |
| Spring Boot 3.5 | Application framework |
| Spring Security | Authentication & Authorization |
| Spring Data JPA | Database ORM |
| Hibernate 6.6 | JPA implementation |
| MySQL 8.0 | Relational database |
| HikariCP | Connection pooling |
| Maven | Build tool |

---

## ✨ Features

- ✅ Complete **Driver Management** (CRUD)
- ✅ Complete **Vehicle Management** (CRUD)
- ✅ **Lease Agreement** creation between drivers and vehicles
- ✅ Automatic vehicle **availability tracking** (marks unavailable when leased)
- ✅ Vehicle **auto-freed** when lease is completed or cancelled
- ✅ **Double-leasing prevention** — blocks leasing an already-leased vehicle
- ✅ **User Registration** with encrypted passwords (BCrypt)
- ✅ **Role-based Security** (ROLE_ADMIN, ROLE_USER) via Spring Security
- ✅ **Global Exception Handling** with meaningful error messages

---

## 📁 Project Structure

```
src/main/java/com/vehicle/leasing/
├── config/
│   └── SecurityConfig.java
├── controller/
│   ├── AuthController.java
│   ├── DriverController.java
│   ├── VehicleController.java
│   └── LeaseController.java
├── entity/
│   ├── Driver.java
│   ├── Vehicle.java
│   ├── LeaseAgreement.java
│   └── User.java
├── repository/
│   ├── DriverRepository.java
│   ├── VehicleRepository.java
│   ├── LeaseAgreementRepository.java
│   └── UserRepository.java
├── service/
│   ├── DriverService.java / DriverServiceImpl.java
│   ├── VehicleService.java / VehicleServiceImpl.java
│   ├── LeaseService.java / LeaseServiceImpl.java
│   └── CustomUserDetailsService.java
└── DriverLeasingApplication.java
```

---

## 🗄️ Database Schema

### `drivers`
| Column | Type |
|---|---|
| id | BIGINT (PK) |
| name | VARCHAR |
| phone | VARCHAR |
| license_number | VARCHAR |
| verified | BOOLEAN |

### `vehicles`
| Column | Type |
|---|---|
| id | BIGINT (PK) |
| brand | VARCHAR |
| model | VARCHAR |
| plate_number | VARCHAR |
| available | BOOLEAN |

### `lease_agreements`
| Column | Type |
|---|---|
| id | BIGINT (PK) |
| driver_id | BIGINT (FK) |
| vehicle_id | BIGINT (FK) |
| start_date | DATE |
| end_date | DATE |
| status | VARCHAR (ACTIVE/COMPLETED/CANCELLED) |
| amount | DOUBLE |

### `users`
| Column | Type |
|---|---|
| id | BIGINT (PK) |
| username | VARCHAR (UNIQUE) |
| password | VARCHAR (BCrypt) |
| role | VARCHAR |

---

## 🚀 Getting Started

### Prerequisites
- Java 21
- MySQL 8.0
- Maven

### 1. Clone the repository
```bash
git clone https://github.com/yourusername/driver-leasing.git
cd driver-leasing
```

### 2. Create MySQL database
```sql
CREATE DATABASE driver_leasing;
```

### 3. Configure `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/driver_leasing
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.open-in-view=false
```

### 4. Build and Run
```bash
mvnw clean package -DskipTests
mvnw spring-boot:run
```

App runs on `http://localhost:8080`

---

## 📡 API Endpoints

### 🔐 Auth
| Method | Endpoint | Description | Auth Required |
|---|---|---|---|
| POST | `/api/auth/register` | Register a new user | No |

### 🧑 Drivers
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/drivers` | Create a driver |
| GET | `/api/drivers` | Get all drivers |
| GET | `/api/drivers/{id}` | Get driver by ID |
| PUT | `/api/drivers/{id}` | Update driver |
| DELETE | `/api/drivers/{id}` | Delete driver |

### 🚗 Vehicles
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/vehicles` | Create a vehicle |
| GET | `/api/vehicles` | Get all vehicles |
| GET | `/api/vehicles/{id}` | Get vehicle by ID |
| PUT | `/api/vehicles/{id}` | Update vehicle |
| DELETE | `/api/vehicles/{id}` | Delete vehicle |

### 📋 Leases
| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/leases/driver/{driverId}/vehicle/{vehicleId}` | Create a lease |
| GET | `/api/leases` | Get all leases |
| GET | `/api/leases/{id}` | Get lease by ID |
| GET | `/api/leases/driver/{driverId}` | Get leases by driver |
| PATCH | `/api/leases/{id}/status?status=COMPLETED` | Update lease status |
| DELETE | `/api/leases/{id}` | Delete a lease |

---

## 🔑 Authentication

All endpoints except `/api/auth/register` require **Basic Authentication**.

**Register first:**
```json
POST /api/auth/register
{
  "username": "admin",
  "password": "1234",
  "role": "ROLE_ADMIN"
}
```

Then use **Basic Auth** with `username` and `password` in every request.

---

## 📬 Sample Requests

### Create a Driver
```json
POST /api/drivers
{
  "name": "Ravi Kumar",
  "phone": "9876543210",
  "licenseNumber": "UP14X1234",
  "verified": true
}
```

### Create a Vehicle
```json
POST /api/vehicles
{
  "brand": "Toyota",
  "model": "Innova",
  "plateNumber": "UP14AB1234"
}
```

### Create a Lease
```json
POST /api/leases/driver/1/vehicle/1
{
  "startDate": "2026-04-18",
  "endDate": "2026-05-18",
  "amount": 5000.00
}
```

### Complete a Lease
```
PATCH /api/leases/1/status?status=COMPLETED
```

---

## 🌐 Deployment

This project is deployed on **Railway** with a cloud MySQL database.

**Live URL:** `https://your-app.railway.app`

---

## 👤 Author

**Your Name**  
📧 minu1kumari2@gmail.com  
🔗 https://www.linkedin.com/in/minu-kumari-57111224a/
🐙 https://github.com/Minu1kumari2

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).
