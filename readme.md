Tenant Rent Management  
Tenant Rent Management is a full-stack application designed to manage tenant information and rent payments. This application consists of a backend service developed in Java Spring Boot and a frontend client developed using React. The app allows users to add, update, delete, and view tenants and their rent payments seamlessly.

Project Structure

tenant-rent-management/  
|── tenant-app-backend/# Java Spring Boot Backend     
│   ├── src/    
│   ├── pom.xml      
│   ├── ...  
├── tenant-app-frontend/    # React Frontend  
│   ├── src/  
│   ├── package.json  
│   ├── ...  
|── README.md  
└── ...  

Technologies Used  
•	Backend:  
o	Java  
o	Spring Boot  
o	Hibernate    
o	RESTful APIs  
o	MySQL (or other relational databases)  

•	Frontend:  
o	React  
o	Axios  
o	React Router  
o	Bootstrap  

•	Others:  
o	Git & GitHub for version control  
o	Docker (optional for deployment)  
o	AWS Secrets Manager (for secure storage of secrets)    
o	Git & GitHub for version control    
o	Docker (optional for deployment)    
o	Netlify (for frontend deployment)    
o	EC2 (for backend deployment)    

Prerequisites  
Before running the application, ensure you have the following installed:  
•	Java (JDK 11 or later)  
•	Node.js (14.x or later)  
•	MySQL (or other preferred databases)  
•	Maven (for backend)  
•	npm or yarn (for frontend)  
•	Git  

Setup Instructions  
Backend Setup  
1.	Clone the Repository:  
git clone https://github.com/nsalunke729/Tenant-Rent-Management.git  
cd Tenant-Rent-Management/tenant-app-backend

3.	Configure Database:  
Update src/main/resources/application.properties with your MySQL database credentials.  

spring.datasource.url=jdbc:mysql://niranjantest.mysql.database.azure.com:3306/tenant_management?useSSL=true&requireSSL=false      
spring.datasource.username=your_username      
spring.datasource.password=${password}      
spring.jpa.hibernate.ddl-auto=update      
    
# AWS Secret Access    
spring.cloud.aws.secretsmanager.enabled=true    
spring.config.import=aws-secretsmanager:dev/tenantApp     

3.	Build and Run the Backend:  
mvn clean install  
mvn spring-boot:run  
The backend server will start at http://localhost:8081.  

Frontend Setup  
1.	Navigate to Frontend Directory:  
cd ../tenant-app-frontend    
2.	Install Dependencies:    
npm install    
3.	Run the Frontend:  
npm start  
The frontend application will run at http://localhost:3000.

Features  
•	Tenant Management:  
o	Add, update, and delete tenant information.  
o	View a list of all tenants with details such as name, room number, contact, etc.    
o	Default payment method dropdown for easy selection.    
•	Rent Payment Management:  
o	Add, update, and delete rent payments.  
o	View a list of rent payments, including payment methods and amounts.  
o	Default payment method dropdown for easy selection.  
o Pagination Support: View rent payments with pagination to improve performance and user experience. Use the page and size query parameters to specify the page number and size of the results. For example: /api/rentpayments?page=0&size=15.    
•	Authentication:  
o	Basic authentication implemented in the backend.  
•	Secrets Management:    
o	AWS Secrets Manager is used for securely managing sensitive information such as database passwords and SSL keys.    
•	Logging:    
o	Logs are managed in a separate file to help monitor application activities and troubleshoot issues.    

API Endpoints  
Tenants  
•	GET /api/tenants - Retrieve all tenants.  
•	POST /api/tenants - Create a new tenant.  
•	PUT /api/tenants/{id} - Update a tenant by ID.  
•	DELETE /api/tenants/{id} - Delete a tenant by ID.  
Rent Payments  
•	GET /api/rentpayments -  - Retrieve all rent payments. Supports pagination with page and size parameters (e.g., /api/rentpayments?page=0&size=15).     
•	POST /api/rentpayments - Create a new rent payment (requires tenant ID).  
•	PUT /api/rentpayments/{id} - Update a rent payment by ID (requires payment ID).  
•	DELETE /api/rentpayments/{id} - Delete a rent payment by ID.  

Contributing
Contributions are welcome! Here's how you can help:  
1.	Fork the repository and clone it locally.
2.	Create a new branch for your feature or bug fix.
3.	Commit your changes and push them to your fork.
4.	Submit a pull request to the main repository.

Troubleshooting  
Common Issues  
1.	Database Connection:  
o	Ensure MySQL(Azure Database) is running and the connection details are correct in application.properties.
2.	Port Conflicts:  
o	Make sure the ports 8443 and 3000 are free or change them as needed in the configuration files.
3.	Dependencies:  
o	Run mvn clean install or npm install again if you face dependency issues.
4. Secrets Not Loaded:    
o	Ensure that AWS Secrets Manager is correctly set up and the application has the right IAM permissions to access the secrets.    

Contact  
For questions or issues, please open an issue in this repository or contact me via:  
•	Email: nsalunke729@gmail.com	  
•	LinkedIn: LinkedIn | Niranjan Salunke

