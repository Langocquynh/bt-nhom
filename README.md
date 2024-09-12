# Quizlet Clone Project

## Overview
Our Quizlet Clone project uses Java Spring Boot and MySQL for the backend, ReactJS for the frontend, and Docker for containerization. Our team consists of four members, each contributing to various aspects of the project.

## Team Members and Roles

### Phạm Đức Chính (me) - Leader, Fullstack
- Planning and Task Assignment: Coordinated team workflow.
- Database Design: Designed a flexible, scalable database.
- Backend API Development: Implemented APIs for user and flashcard management.
- Security: Configured Spring Security, HTTPS, and CORS.
- Authentication: Used JWT for user authentication.
- Pagination: Implemented backend pagination.
- Frontend Support: Assisted with UI/UX and API integration.
- Debugging: Tested and fixed frontend issues.

### Nguyễn Quốc Khánh - Fullstack
- Fullstack Development: Contributed to both frontend and backend.
- API Development: Created APIs for card and tag management.
- Database Design: Contributed to backend design.
- Frontend Pages: Developed search, folder, and settings pages.
- Pagination: Implemented pagination for cards and folders.
- Report Writing: Documented project progress.

### Phạm Hữu Đoàn - Frontend
- UI Development: Built pages like registration, login, and flashcards.
- Core Features: Developed flashcard games (Learn, Test, Match).
- Data Validation: Implemented form validations.
- Project Structure: Ensured scalable frontend structure.
- User Interaction: Created interactive notifications.
- Navbar Component: Developed a reusable navbar.

### Nguyễn Đức An - Frontend
- UI Development: Built home, create set, edit set, and privacy policy pages.
- Navbar Component: Developed a reusable navbar.
- API Integration: Connected backend APIs to the frontend.
- Project Structure: Ensured scalable frontend structure.
- Testing: Tested UI and functionality.
- Report Writing: Documented project progress.

## Technologies Used
- Backend: Java Spring Boot, MySQL
- Frontend: ReactJS [Github repo](https://github.com/huudoann/Quizlet-Clone-ReactJS)
- Containerization: Docker

## Deployment Instructions
To deploy the Quizlet Clone project using Docker, follow these steps:

### Prerequisites
Ensure you have the following installed:
- Docker desktop

### Steps to Deploy

1. **Clone the Repository:**

    ```bash
    https://github.com/deltaDC/Quizlet-Clone-Java-Spring-Boot.git
    ```

2. **Navigate to the Project Directory:**

    ```bash
    cd path_to_project_in_your_computer
    ```

3. **Build the Docker Image:**

    ```bash
    docker-compose build
    ```

4. **Run the Docker Containers:**

    ```bash
    docker-compose up -d
    ```

5. **Access Your Application:**

    Once the containers are up and running, you can access your application by sending request to `http://localhost:8080` in your web browser or in Postman.

## Documentation

For detailed documentation, please refer to [this PDF report](https://drive.google.com/file/d/1O2e_e0efrJXfuhF2nRZ1GuWEgkWo8iUP/view?usp=sharing).

## Conclusion

Quizlet clone may have some bugs and missing features. Please be aware that the development team is no longer actively working on the project. Your understanding is appreciated, and feel free to provide feedback for potential improvements.
