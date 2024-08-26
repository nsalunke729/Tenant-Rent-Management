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
    const [currentPage, setCurrentPage] = useState(0);
    const [pageSize, setPageSize] = useState(10);
    const [totalPages, setTotalPages] = useState(0);

    useEffect(() => {
        fetchRentPayments(currentPage, pageSize);
    }, [currentPage, pageSize]);

    const fetchRentPayments = async (page, size) => {
        try {
            const data = await getRentPayments(page, size);
            if (data && data.content) {
                setRentPayments(data.content);
                setTotalPages(data.totalPages);
            } else {
                // Handle unexpected data structure
                setRentPayments([]);
                setTotalPages(0);
            }
        } catch (error) {
            console.error('Error fetching rent payments', error);
            setRentPayments([]); // Set to empty array in case of error
            setTotalPages(0);
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
            fetchRentPayments(currentPage, pageSize);
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
            fetchRentPayments(currentPage, pageSize);
        } catch (error) {
            console.error('Error deleting rent payment', error);
        }
    };

    const handlePageChange = (newPage) => {
        setCurrentPage(newPage);
    };

    return (
        <div className="container">
            <h1>Rent Payments</h1>
            {/* Form for adding/updating payments */}
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

            {/* Table displaying rent payments */}
            {rentPayments.length > 0 ? (
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
            ) : (
                <p>No rent payments found.</p>
            )}

            {/* Pagination controls */}
            <div className="pagination">
                <button
                    className="btn btn-primary"
                    onClick={() => handlePageChange(currentPage - 1)}
                    disabled={currentPage === 0}
                >
                    Previous
                </button>
                <span className="mx-2">Page {currentPage + 1} of {totalPages}</span>
                <button
                    className="btn btn-primary"
                    onClick={() => handlePageChange(currentPage + 1)}
                    disabled={currentPage >= totalPages - 1}
                >
                    Next
                </button>
            </div>
        </div>
    );
};

export default RentPayments;
