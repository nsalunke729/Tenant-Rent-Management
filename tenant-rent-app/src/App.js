// src/App.js

import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Tenants from './components/Tenants';
import RentPayments from './components/RentPayments';
import Login from './components/Login';
import ProtectedRoute from './components/ProtectedRoute'; // Import ProtectedRoute
import { useAuth } from './AuthContext';
import './App.css';

function App() {
    const { isAuthenticated, logout } = useAuth();

    return (
        <Router>
            <div className="container" id="app">
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <Link className="navbar-brand" to="/">Tenant Rent App</Link>
                    <div className="collapse navbar-collapse">
                        <ul className="navbar-nav mr-auto">
                            <li className="nav-item">
                                <Link className="nav-link" to="/">Login</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/rent-payments">Rent Payments</Link>
                            </li>
                            <li className="nav-item">
                                <Link className="nav-link" to="/tenants">Tenants</Link>
                            </li>
                        </ul>
                    </div>
                    {isAuthenticated && (
                        <button onClick={logout} className="btn btn-outline-danger">Logout</button>
                    )}
                </nav>

                <div className="mt-4">
                    <Routes>
                        <Route path="/" element={<Login />} />
                        {/* Use ProtectedRoute for protected pages */}
                        <Route path="/rent-payments" element={<ProtectedRoute element={<RentPayments />} />} />
                        <Route path="/tenants" element={<ProtectedRoute element={<Tenants />} />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
