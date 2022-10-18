# KIDSSCHOOLBE
BE for KIDSCHOOL app

Build in for KIDSCHOOL app .
This is back-end server. 
Document Restful API: 
http://localhost:8080/swagger-ui.html

It set up CORS for client app:
http://localhost:3000

Launch App Guide:
Step 1: Build Dependency.
Step 2: Create new DB in MySQL. 
  - Name of DB: project
  - Update spring.jpa.hibernate.ddl-auto = create-drop in application.properties 
Step 3: Run Java App. After that, please change spring.jpa.hibernate.ddl-auto = update.

ROLE: Please apply roleName in Uppercase Format
USER (student), 
TEACHER (default roleID: 4), 
ACADEMIC, 
HR (Human resources), 
PRINCIPAL, 
ADMIN


***NOTE*** 
Make sure your file .gitignore: having field: .logs/ . This is very IMPORTANT
