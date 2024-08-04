import React, { useState, useEffect } from 'react';
import { getTenants, createTenant, updateTenant, deleteTenant } from '../api';

const Tenants = () => {
    const [tenants, setTenants] = useState([]);
    const [newTenant, setNewTenant] = useState({
        name: '',
        contact: '',
        startDate: '',
        endDate: '',
        roomNo: '',
        rent: '',
    });
    const [editingTenant, setEditingTenant] = useState(null);

    useEffect(() => {
        fetchTenants();
    }, []);

    const fetchTenants = async () => {
        try {
            const data = await getTenants();
            setTenants(data);
        } catch (error) {
            console.error('Error fetching tenants', error);
        }
    };

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewTenant({ ...newTenant, [name]: value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            if (editingTenant) {
                await updateTenant(editingTenant.id, newTenant);
                setEditingTenant(null);
            } else {
                await createTenant(newTenant);
            }
            fetchTenants();
            setNewTenant({
                name: '',
                contact: '',
                startDate: '',
                endDate: '',
                roomNo: '',
                rent: '',
            });
        } catch (error) {
            console.error('Error saving tenant', error);
        }
    };

    const handleEdit = (tenant) => {
        setEditingTenant(tenant);
        setNewTenant(tenant);
    };

    const handleDelete = async (id) => {
        try {
            await deleteTenant(id);
            fetchTenants();
        } catch (error) {
            console.error('Error deleting tenant', error);
        }
    };

    return (
        <div className="container">
            <h1>Tenants</h1>
            <form onSubmit={handleSubmit} className="mb-4">
                <div className="form-group">
                    <label htmlFor="name">Name</label>
                    <input
                        type="text"
                        name="name"
                        className="form-control"
                        value={newTenant.name}
                        onChange={handleInputChange}
                        placeholder="Name"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="contact">Contact</label>
                    <input
                        type="text"
                        name="contact"
                        className="form-control"
                        value={newTenant.contact}
                        onChange={handleInputChange}
                        placeholder="Contact"
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="startDate">Start Date</label>
                    <input
                        type="date"
                        name="startDate"
                        className="form-control"
                        value={newTenant.startDate}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="endDate">End Date</label>
                    <input
                        type="date"
                        name="endDate"
                        className="form-control"
                        value={newTenant.endDate}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="roomNo">Room No</label>
                    <input
                        type="text"
                        name="roomNo"
                        className="form-control"
                        value={newTenant.roomNo}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label htmlFor="rent">Rent</label>
                    <input
                        type="number"
                        name="rent"
                        className="form-control"
                        value={newTenant.rent}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">
                    {editingTenant ? 'Update Tenant' : 'Add Tenant'}
                </button>
                {editingTenant && (
                    <button
                        type="button"
                        className="btn btn-secondary ml-2"
                        onClick={() => setEditingTenant(null)}
                    >
                        Cancel
                    </button>
                )}
            </form>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Contact</th>
                        <th>Start Date</th>
                        <th>End Date</th>
                        <th>Room No</th>
                        <th>Rent</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {tenants.map((tenant) => (
                        <tr key={tenant.id}>
                            <td>{tenant.name}</td>
                            <td>{tenant.contact}</td>
                            <td>{new Date(tenant.startDate).toLocaleDateString()}</td>
                            <td>{new Date(tenant.endDate).toLocaleDateString()}</td>
                            <td>{tenant.roomNo}</td>
                            <td>{tenant.rent}</td>
                            <td>
                                <button
                                    className="btn btn-warning btn-sm"
                                    onClick={() => handleEdit(tenant)}
                                >
                                    Edit
                                </button>
                                <button
                                    className="btn btn-danger btn-sm ml-2"
                                    onClick={() => handleDelete(tenant.id)}
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

export default Tenants;
