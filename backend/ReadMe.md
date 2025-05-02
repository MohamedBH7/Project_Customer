# Customer Management System (Backend)

Java backend with SparkJava framework and SQLite database for customer management operations.

## Features

- 🗄️ SQLite database with auto-creation
- 🔄 RESTful API endpoints
- 🔒 CORS configuration
- 🛠️ CRUD operations with JDBC
- 📦 Pre-populated sample data

## Prerequisites

- Java JDK 17+
- Maven 3.8+
- SQLite JDBC driver (auto-installed)

## Installation

1. Navigate to backend directory:
   ```bash
   cd backend
2. Install dependencies with Maven:
      ```bash
   mvn clean install


#Project Structure
src/main/java/org/example/<br>
├── WebService.java     # API endpoints<br>
├── Main.java           # Database initialization<br>
├── dao/<br>
│   └── CustomerDAO.java # Database operations<br>
└── models/<br>
    ├── Customer.java   # Data model<br>
    └── ConnectDB.java  # Database connection<br>
#Running the Application : 
      mvn package
#Run the webservice:
java -cp target/classes:target/dependency/* org.example.WebService

Runs on: http://localhost:4567

Database file: backend/mydb.db

<table>
    <tr>
        <th>Method</th>
        <th>Endpoint</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>GET</td>
        <td>/api/customers</td>
        <td>Get all customers</td>
    </tr>
    <tr>
        <td>GET</td>
        <td>/api/customers/{id}</td>
        <td>Get single customer</td>
    </tr>
    <tr>
        <td>POST</td>
        <td>/api/customers</td>
        <td>Create new customer</td>
    </tr>
    <tr>
        <td>PUT</td>
        <td>/api/customers/{id}</td>
        <td>Update existing customer</td>
    </tr>
    <tr>
        <td>DELETE</td>
        <td>/api/customers/{id}</td>
        <td>Delete customer</td>
    </tr>
</table>
Complete API Documentation:
    <p>Click <a href="https://documenter.getpostman.com/view/44617995/2sB2j4gBcK">here</a> to access Complete API Documentation.</p>

# Troubleshooting : 
Common Issues
1. Port Conflicts

Check if port 4567 is available:
 netstat -ano | findstr :4567
 
#Database Connection Errors

Verify mydb.db exists in backend folder
Check file permissions for database file

<table>
    <tr>
        <th>Database Information</th>
    </tr>
    <tr>
        <td><strong>Location:</strong> backend/mydb.db</td>
    </tr>
    <tr>
        <td><strong>Initial Data:</strong> 2 sample customers pre-loaded</td>
    </tr>
    <tr>
        <td><strong>Schema:</strong> Auto-created on first run</td>
    </tr>
    <tr>
        <td><strong>JDBC URL:</strong> jdbc:sqlite:mydb.db</td>
    </tr>
</table>


# Development Notes
Key Dependencies <br>
<code>
&lt;dependencies&gt;
  &lt;dependency&gt;
    &lt;groupId&gt;com.sparkjava&lt;/groupId&gt;
    &lt;artifactId&gt;spark-core&lt;/artifactId&gt;
    &lt;version&gt;2.9.4&lt;/version&gt;
  &lt;/dependency&gt;
  &lt;dependency&gt;
    &lt;groupId&gt;org.xerial&lt;/groupId&gt;
    &lt;artifactId&gt;sqlite-jdbc&lt;/artifactId&gt;
    &lt;version&gt;3.44.1.0&lt;/version&gt;
  &lt;/dependency&gt;
  &lt;dependency&gt;
    &lt;groupId&gt;com.google.code.gson&lt;/groupId&gt;
    &lt;artifactId&gt;gson&lt;/artifactId&gt;
    &lt;version&gt;2.10.1&lt;/version&gt;
  &lt;/dependency&gt;
&lt;/dependencies&gt;
</code>
