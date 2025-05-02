# Customer Management System (Frontend)

A React-based frontend for managing customer information with Bootstrap styling and API integration.

## Features

- 📋 View customer list with pagination-ready table
- ➕ Create new customers via modal form
- ✏️ Edit customer details with inline validation
- 🗑️ Delete customers with confirmation
- 📱 Responsive design powered by React-Bootstrap
- 🚦 Error handling and user feedback

## Installation

### Prerequisites
- Node.js ≥14.x
- npm ≥7.x

1. Navigate to project directory:
   ```bash
   cd frontend
   npm install axios react-bootstrap bootstrap react-router-dom
Running the Application
  npm start
Runs on: http://localhost:3000

Auto-opens in default browser

Requires backend server running on http://localhost:4567



<body>
<table>
    <thead>
        <tr>
            <th>Method</th>
            <th>Endpoint</th>
            <th>Component</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>GET</td>
            <td>/api/customers</td>
            <td>CustomerList</td>
        </tr>
        <tr>
            <td>POST</td>
            <td>/api/customers</td>
            <td>CreateCustomerModal</td>
        </tr>
        <tr>
            <td>PUT</td>
            <td>/api/customers/{id}</td>
            <td>EditCustomerModal</td>
        </tr>
        <tr>
            <td>DELETE</td>
            <td>/api/customers/{id}</td>
            <td>DeleteConfirmation</td>
        </tr>
    </tbody>
</table>
API Documentation:
    <p>Click <a href="https://documenter.getpostman.com/view/44617995/2sB2j4gBcK">here</a> to View Postman API Docs.</p>

#Troubleshooting
Common Issues
1. Data Not Loading

Verify backend server is running

Check browser console for network errors

Ensure no CORS restrictions (backend must allow requests from http://localhost:3000)

2. Form Submission Failures

Required fields: Name, Email, Phone

Email validation: Must follow standard format

Phone validation: Accepts numbers and +()-. characters

# Development Notes
src/
├── App.js # Router configuration <br>
├── components/<br>
│ └── CustomerList.js # Main component with CRUD operations<br>
├── App.css # Custom styles<br>
└── index.js # Root render<br>

# Key Dependencies
```json
{
  "dependencies": {
    "react": "^18.2.0",
    "react-dom": "^18.2.0",
    "react-bootstrap": "^2.9.2",
    "axios": "^1.6.2",
    "react-router-dom": "^6.22.3",
    "bootstrap": "^5.3.3"
  }
}

### Support

For immediate issues, ensure:

- Backend server is running on port 4567
- No browser extensions are blocking API requests
- All required fields are populated in forms
