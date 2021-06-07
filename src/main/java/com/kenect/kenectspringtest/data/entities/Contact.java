package com.kenect.kenectspringtest.data.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @JsonIgnore
    @CreationTimestamp
    private Date createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private Date updatedAt;

    @OneToMany(mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Email> emails = new ArrayList<>();

    @OneToMany(mappedBy = "contact",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    public void addPhone(Phone phone) {
        phones.add(phone);
        phone.setContact(this);
    }

    public void addEmail(Email email) {
        emails.add(email);
        email.setContact(this);
    }

    public void addAddress(Address address) {
        addresses.add(address);
        address.setContact(this);
    }
}
