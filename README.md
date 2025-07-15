# springblogapp
# 📝 Spring Blog REST API

A simple and clean blog REST API built with **Spring Boot 3**, **Spring Data JPA**, **Hibernate**, and **ModelMapper**.  
This project allows users to register, create posts, and comment on posts.

---

## 🚀 Features

- 🧍 User registration
- 📰 Create, read, list blog posts
- 💬 Comment on posts
- 🔄 Pagination support
- ✅ DTO mapping & validation
- ⚠️ Global error handling
- 🧪 Ready to extend with authentication (JWT)

---

## 📦 Tech Stack

- Java 21
- Spring Boot 3.5.3
- Spring Data JPA (Hibernate)
- MySQL
- ModelMapper
- Lombok
- Jakarta Bean Validation

---

## 📁 Project Structure
- controller/ → REST API endpoints
- dto/ → Request & Response data transfer classes
- entity/ → JPA entity classes
- repository/ → Data access interfaces (Spring Data JPA)
- service/ → Business Logic ve DTO mapping

---

## 🔧 API Endpoints 

### Users
- `POST /users` → Register user  
- `GET /users/{id}` → Get user info  

### Posts
- `POST /posts` → Create post  
- `GET /posts/{id}` → Get post  
- `GET /posts?page=0&size=10` → List posts  
- `GET /posts/user/{userId}` → Posts by user  

### Comments
- `POST /comments` → Add comment  
- `GET /comments/post/{postId}` → Get comments for a post  

---

## 🛠 Getting Started

### 1. Clone & Build

```bash
git clone https://github.com/your-username/spring-blog-api.git
cd spring-blog-api
./mvnw spring-boot:run
