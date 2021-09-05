package com.wonderlabz.bankservice.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "users")
public class Users {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "is_active")
    private String isActive;

    @Column(name = "dob")
    private LocalDate dob;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "savings_account_number", nullable = false)
    private SavingsAccount savingsAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="current_account_number",nullable =false)
    private CurrentAccount currentAccount;
}
