package com.zencoderz.bluebank.api.account;

import com.zencoderz.bluebank.api.transaction.Transaction;
import com.zencoderz.bluebank.api.user.User;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "user_id" })})
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 4, nullable = false)
    private String agency;

    @Column(length = 10,  nullable = false)
    private String accountNumber;

    @Column(length = 2, nullable = false)
    private String digit;

    private Double balance;

    private Double credit;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="from")
    private List<Transaction> madeTransfers = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "to")
    private List<Transaction> receivedTransfers = new ArrayList<>();

    public Account() {

    }

    public Account(UUID id, String agency, String accountNumber, String digit, Double balance, Double credit, User user) {
        this.id = id;
        this.agency = agency;
        this.accountNumber = accountNumber;
        this.digit = digit;
        this.balance = balance;
        this.credit = credit;
        this.user = user;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDigit() {
        return digit;
    }

    public void setDigit(String digit) {
        this.digit = digit;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}