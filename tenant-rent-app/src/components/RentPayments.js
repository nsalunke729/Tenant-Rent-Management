import React, { useState, useEffect } from 'react';
import { getRentPayments, createRentpayments, updateRentpayments, deleteRentpayments } from '../api';

const paymentMethods = [
    'Credit Card',
    'Debit Card',
    'Cash',
    'Bank Transfer',
    'Check'
];

const RentPayments = () => {
    const [rentPayments, setRentPayments] = useState([]);
    const [newPayment, setNewPayment] = useState({
        tenantId: '',
        amount: '',
        paymentDate: '',
        paymentMethod: ''
    });
    const [editingPayment, setEditingPayment] = useState(null);

    useEffect(() => {
        fetchRentPayments();
    }, []);

    const fetchRentPayments = async () => {
        try {
            const data = await getRentPayments();
            setRentPayments(data);
        } catch (error) {
            console.error('Error fetching rent payments', error);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewPayment({ ...newPayment, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (editingPayment) {
                await updateRentpayments(editingPayment.id, newPayment);
                setEditingPayment(null);
            } else {
                await createRentpayments(newPayment);
            }
            fetchRentPayments();
            setNewPayment({
                tenantId: '',
                amount: '',
                paymentDate: '',
                paymentMethod: ''
            });
        } catch (error) {
            console.error('Error saving rent payment', error);
        }
    };

    const handleEdit = (payment) => {
        setEditingPayment(payment);
        setNewPayment({
            tenantId: payment.tenantId,
            amount: payment.amount,
            paymentDate: payment.paymentDate.split('T')[0],
            paymentMethod: payment.paymentMethod
        });
    };

    const handleDelete = async (id) => {
        try {
            await deleteRentpayments(id);
            fetchRentPayments();
        } catch (error) {
            console.error('Error deleting rent payment', error);
        }
    };

    return (
        <div className="container">
            <h1>Rent Payments</h1>
            <form onSubmit={handleSubmit} className="mb-4">
                <div className="form-group">
                    <label htmlFor="tenantId">Tenant ID</label>
                    <input
                        type="number"
                        name="tenantId"
                        className="form-control"
                        value={newPayment.tenantId}
                        onChange={handleInputChange}
                        placeholder="Tenant ID"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="amount">Amount</label>
                    <input
                        type="number"
                        name="amount"
                        className="form-control"
                        value={newPayment.amount}
                        onChange={handleInputChange}
                        placeholder="Amount"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="paymentDate">Payment Date</label>
                    <input
                        type="date"
                        name="paymentDate"
                        className="form-control"
                        value={newPayment.paymentDate}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="paymentMethod">Payment Method</label>
                    <select
                        name="paymentMethod"
                        className="form-control"
                        value={newPayment.paymentMethod}
                        onChange={handleInputChange}
                        required
                    >
                        <option value="">Select Payment Method</option>
                        {paymentMethods.map(method => (
                            <option key={method} value={method}>
                                {method}
                            </option>
                        ))}
                    </select>
                </div>
                <button type="submit" className="btn btn-primary">
                    {editingPayment ? 'Update Payment' : 'Add Payment'}
                </button>
                {editingPayment && (
                    <button
                        type="button"
                        className="btn btn-secondary ml-2"
                        onClick={() => setEditingPayment(null)}
                    >
                        Cancel
                    </button>
                )}
            </form>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Tenant ID</th>
                        <th>Amount</th>
                        <th>Payment Date</th>
                        <th>Payment Method</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {rentPayments.map((payment) => (
                        <tr key={payment.id}>
                            <td>{payment.tenantId}</td>
                            <td>{payment.amount}</td>
                            <td>{new Date(payment.paymentDate).toLocaleDateString()}</td>
                            <td>{payment.paymentMethod}</td>
                            <td>
                                <button
                                    className="btn btn-warning btn-sm"
                                    onClick={() => handleEdit(payment)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger btn-sm ml-2"
                                    onClick={() => handleDelete(payment.id)}
                                >
                                    Delete
                                </button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default RentPayments;
