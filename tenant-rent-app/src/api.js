import axios from 'axios';

// Retrieve stored credentials (if they are stored in local storage)
const username = 'user';
const password = 'password';

function getBasicAuthHeader(username, password) {
    // Combine the username and password into a single string and encode it using Base64
    const credentials = `${username}:${password}`;
    const base64Credentials = btoa(credentials); // Encode to Base64
    return `Basic ${base64Credentials}`;
};

const apiClient = axios.create({
    baseURL: 'http://localhost:8081/api', // Replace with your back-end URL
    headers: {
        'Content-Type': 'application/json',
        // Generate the Basic Authentication header using the utility function
        'Authorization': getBasicAuthHeader(username, password),
    },
});

// Function to get all tenants
export const getTenants = async () => {
    try {
        const response = await apiClient.get('/tenants');
        return response.data;
    } catch (error) {
        console.error('Error fetching tenants', error);
        throw error;
    }
};

// Function to create a new tenant
export const createTenant = async (tenant) => {
    try {
        const response = await apiClient.post('/tenants', tenant);
        return response.data;
    } catch (error) {
        console.error('Error creating tenant', error);
        throw error;
    }
};

// Function to update a tenant
export const updateTenant = async (id, tenant) => {
    try {
        const response = await apiClient.put(`/tenants/${id}`, tenant);
        return response.data;
    } catch (error) {
        console.error('Error updating tenant', error);
        throw error;
    }
};

// Function to get tenant by ID
export const getTenantById = async (id) => {
    try {
        const response = await apiClient.get(`/tenants/${id}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching tenant by ID', error);
        throw error;
    }
};

// Function to delete a tenant
export const deleteTenant = async (id) => {
    try {
        await apiClient.delete(`/tenants/${id}`);
    } catch (error) {
        console.error('Error deleting tenant', error);
        throw error;
    }
};

// Similarly, add functions for rent payments
export const getRentPayments = async () => {
    try {
        const response = await apiClient.get('/rentpayments');
        return response.data;
    } catch (error) {
        console.error('Error fetching rent payments', error);
        throw error;
    }
};

// ... Other rent payment methods
// Function to create a new rentpayments
export const createRentpayments = async (rentpayments) => {
    try {
        const response = await apiClient.post('/rentpayments', rentpayments);
        return response.data;
    } catch (error) {
        console.error('Error creating rent payments', error);
        throw error;
    }
};

// Function to update a rent payments
export const updateRentpayments = async (id, rentpayments) => {
    try {
        const response = await apiClient.put(`/rentpayments/${id}`, { ...rentpayments, id });
        return response.data;
    } catch (error) {
        console.error('Error updating rent payments', error);
        throw error;
    }
};

// Function to get rentpayments by ID
export const getRentpaymentsById = async (id) => {
    try {
        const response = await apiClient.get(`/rentpayments/${id}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching rentpayments by ID', error);
        throw error;
    }
};

// Function to delete a rentpayments
export const deleteRentpayments = async (id) => {
    try {
        await apiClient.delete(`/rentpayments/${id}`);
    } catch (error) {
        console.error('Error deleting rentpayments', error);
        throw error;
    }
};