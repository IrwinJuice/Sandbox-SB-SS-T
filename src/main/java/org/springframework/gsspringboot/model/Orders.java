package org.springframework.gsspringboot.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @SequenceGenerator(name = "idGenerator", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column
    private String amount;

    @Column
    private String customerAddress;

    @Column(nullable = false)
    private String customerEmail;

    @Column(nullable = false)
    private String customerName;

    @Column (nullable = false)
    private String customerPhone;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Column (name = "order_number", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    @SequenceGenerator(name = "idGenerator", allocationSize = 1, initialValue = 1)
    private Integer orderNumber;

    public Orders() {}

    public Orders(String amount,
                  String customerAddress,
                  String customerEmail,
                  String customerName,
                  String customerPhone,
                  Date orderDate) {
        this.amount = amount;
        this.customerAddress = customerAddress;
        this.customerEmail = customerEmail;
        this.customerName = customerName;
        this.customerPhone = customerPhone;
        this.orderDate = orderDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }
}
