import React from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Tenants from './components/Tenants';
import RentPayments from './components/RentPayments';
import Login from './components/Login';
import './App.css'; 

function App() {
    return (
        <Router>
            <div className="container">
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <Link className="navbar-brand" to="/">App</Link>
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
                </nav>

                <div className="mt-4">
                    <Routes>
                        <Route path="/" element={<Login />} />
                        <Route path="/rent-payments" element={<RentPayments />} />
                        <Route path="/tenants" element={<Tenants />} />
                    </Routes>
                </div>
            </div>
        </Router>
    );
}

export default App;
