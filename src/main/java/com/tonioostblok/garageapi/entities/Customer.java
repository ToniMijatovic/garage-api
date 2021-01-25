package com.tonioostblok.garageapi.entities;

import javax.persistence.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String address;
    private String zipcode;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "customer_car",
            joinColumns = @JoinColumn(
                    name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(
                    name = "car_id", referencedColumnName = "id"))
    private Collection<Car> cars;

    public Customer(
            String firstname,
            String lastname,
            String phonenumber,
            String email,
            String address,
            String zipcode
    ){
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.email = email;
        this.address = address;
        this.zipcode = zipcode;
    }

    public Customer(){

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhonenumber() {
        return this.phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return this.zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public void setCars(Collection<Car> cars) {
        this.cars = cars;
    }
    public Collection<Car> getCars() {
        return this.cars;
    }

}
