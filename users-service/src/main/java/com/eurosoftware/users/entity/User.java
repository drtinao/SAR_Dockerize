package com.eurosoftware.users.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() { return id; }

    private String name;

    public String getName() { return name; }

    private String residence;

    public String getResidence() { return residence; }

    private String bankAccount;

    public String getBankAccount() { return bankAccount; }

    private String identificationNumber;

    public String getIdentificationNumber() { return identificationNumber; }

    private String email;

    public String getEmail() { return email; }

    private String phone;

    public String getPhone() { return phone; }
}
