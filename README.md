# 🏦 Bank Statement Transaction Categorization System

This is a Java & Spring Boot–based web application that uses AI/NLP techniques to automatically analyze and categorize bank statement transactions. It helps users easily understand their spending patterns by processing uploads and categorizing expenses like Food, Travel, Shopping, Utilities, Rent, and more. :contentReference[oaicite:2]{index=2}

---

## 📌 Key Features

- Upload and process bank statements (CSV / PDF)  
- Automatic AI/NLP-based transaction classification  
- Category-wise expense insights  
- Secure backend with Spring Boot  
- User-friendly web interface :contentReference[oaicite:3]{index=3}

---

## 🏗️ Architecture

<p align="center">
  <img src="C:\Users\lohit\Pictures\Screenshots\architecture.png" width="700"/>
</p>

### 🧠 Architecture Overview

This diagram shows the logical flow:

- **Frontend** sends requests to REST API  
- **Backend (Spring Boot)** handles parsing & categorization  
- **AI/NLP Engine** analyzes descriptions  
- **Database** stores categorized transactions

---

## 🔄 Workflow

<p align="center">
  <img src="images/workflow.png" width="700"/>
</p>

### 🧩 How It Works

1. User uploads statement (CSV or PDF)  
2. Backend extracts text from file  
3. Extracted data processed into transactions  
4. AI/NLP categorizes each transaction  
5. Categorized data is stored & shown to user

---

## 🚀 Production Deployment

<p align="center">
  <img src="C:\Users\lohit\Pictures\Screenshots\production.png" width="700"/>
</p>

### 📦 Deployment Stack

- Backend: Spring Boot  
- Server: AWS / Azure / Heroku (your setup)  
- Database: MySQL / PostgreSQL  
- Build: Maven  
- AI: NLP + Machine Learning

---

## 🛠️ Tech Stack

- Java  
- Spring Boot  
- REST APIs  
- AI / NLP Analysis  
- MySQL, PostgreSQL  
- Maven  
- HTML, CSS, JavaScript (Frontend) :contentReference[oaicite:4]{index=4}

---

## 📂 Project Structure


