# 📝 ATS Resume Checker

An Basic AI-powered ATS (Applicant Tracking System) resume checker web application that evaluates resumes for job readiness using NLP techniques. It calculates a matching score between the resume content and the job description and helps users improve their resumes for better job application success.

---

## 🛠️ Tech Stack

### ⚙️ Backend (Java)
- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- Apache POI (for reading Word documents)
- OpenNLP (for natural language processing)
- Hibernate
- MySQL or H2 (for local dev)
- CORS configuration for API calls

### 🌐 Frontend (ReactJS)
- React 18 with Vite
- Axios (API integration)
- Tailwind CSS (UI styling)
- File upload with preview
- Responsive design

---

## 📁 Project Structure

```plaintext
ATSResumeChecker/
│
├── Backend/ats_resume_checker/
│   ├── src/main/java/com/atsresumechecker/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   ├── utils/
│   │   └── AtsResumeCheckerApplication.java
│   ├── resources/
│   │   └── application.properties
│   └── pom.xml
│
├── Frontend/ats_resume_checker/
│   ├── public/
│   ├── src/
│   │   ├── components/
│   │   │   └── ResumeScoreChecker.jsx
│   │   └── App.jsx
│   ├── index.html
│   ├── vite.config.js
│   └── package.json
```

---

## 🚀 Features

- Upload resume in `.doc` or `.docx` format.
- Paste job description text.
- Calculate ATS score based on keyword match.
- Real-time feedback.
- Clean and responsive frontend UI.
- RESTful API integration.

---

## ⚙️ How to Run

### ✅ Backend Setup (Spring Boot)

1. Clone the repo:
   ```bash
   git clone https://github.com/yourusername/ATSResumeChecker.git
   cd ATSResumeChecker/Backend/ats_resume_checker
   ```

2. Open in your favorite IDE (e.g., IntelliJ, Eclipse).

3. Configure your database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/atschecker
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update
   spring.cors.allowed-origins=http://localhost:5173
   ```

4. Build and run the project:
   ```bash
   mvn spring-boot:run
   ```

5. The backend will run on: `http://localhost:8080`

---

### ✅ Frontend Setup (React with Vite)

1. Go to the frontend folder:
   ```bash
   cd ../../Frontend/ats_resume_checker
   ```

2. Install dependencies:
   ```bash
   npm install
   ```

3. Start development server:
   ```bash
   npm run dev
   ```

4. Access the app:
   ```
   http://localhost:5173
   ```

---

## 📦 API Reference

### `POST /api/resume/score`
- **Content-Type:** `multipart/form-data`
- **Fields:**
  - `resume` — DOCX/DOC file
  - `jobDescription` — Job description text

### Sample cURL:
```bash
curl -X POST http://localhost:8080/api/resume/score \
  -F "resume=@your_resume.docx" \
  -F "jobDescription=Java developer with Spring Boot"
```

---

## 📸 Screenshots

![image](https://github.com/user-attachments/assets/9cafb8f1-2622-47d8-8876-4d2367616262)


---

## 📌 To Do

- Add user login system
- Store past scores
- Export report as PDF
- Add AI recommendations

---

## 🤝 Contribution

Feel free to fork this repo and contribute! Pull requests are welcome.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).

---

## 👨‍💻 Author

Built with 💛 by [Mohammed Thoufiq](https://github.com/MohammedThoufiq)

```

---
