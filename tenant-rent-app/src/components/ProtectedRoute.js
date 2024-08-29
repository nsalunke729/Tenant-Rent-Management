// src/components/ProtectedRoute.js

import React from 'react';
import { Navigate } from 'react-router-dom';
import { useAuth } from '../AuthContext';

function ProtectedRoute({ element, ...rest }) {
    const { isAuthenticated } = useAuth(); // Check if the user is authenticated

    if (!isAuthenticated) {
        // If not authenticated, redirect to login
        return <Navigate to="/" replace />;
    }

    // If authenticated, render the component
    return element;
}

export default ProtectedRoute;
