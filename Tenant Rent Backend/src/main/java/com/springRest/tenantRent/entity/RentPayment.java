package com.springRest.tenantRent.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "rentpayments")
public class RentPayment {

    @Id
    @Column(name = "paymentid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "tenantid", nullable = false)
    private Tenant tenant;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "paymentdate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;

    @Column(name = "paymentmethod", nullable = false, length = 50)
    private String paymentMethod;

    // Constructors, getters, and setters

    public RentPayment() {
    }

    public RentPayment(Integer id, Tenant tenant, Integer amount, Date paymentDate, String paymentMethod) {
        this.id = id;
        this.tenant = tenant;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTenantId(Integer tenantId){
        if(tenant == null) {
            tenant = new Tenant();
        }
        tenant.setId(tenantId);
    }

	public Integer getTenantId() {
		// TODO Auto-generated method stub
        if (tenant != null) {
            return tenant.getId();
        }else
		{
            throw new UnsupportedOperationException("Unimplemented method 'getTenantId'");
        }
	}
}