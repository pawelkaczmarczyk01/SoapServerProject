package com.example.SoapServerProject.db.daoModel;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public AccountDao withId(Integer id) {
        setId(id);
        return this;
    }

    @Column
    private String accountName;

    public String getAccountName() {
        return accountName;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public AccountDao withAccountName(String accountName) {
        setAccountName(accountName);
        return this;
    }

    @Column
    private String accountLastName;

    public String getAccountLastName() {
        return accountLastName;
    }
    public void setAccountLastName(String accountLastName) {
        this.accountLastName = accountLastName;
    }
    public AccountDao withAccountLastName(String accountLastName) {
        setAccountLastName(accountLastName);
        return this;
    }

    @Column
    private String accountLogin;

    public String getAccountLogin() {
        return accountLogin;
    }
    public void setAccountLogin(String accountLogin) {
        this.accountLogin = accountLogin;
    }
    public AccountDao withAccountLogin(String accountLogin) {
        setAccountLogin(accountLogin);
        return this;
    }

    @Column
    private String accountPassword;

    public String getAccountPassword() {
        return accountPassword;
    }
    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
    public AccountDao withAccountPassword(String accountPassword) {
        setAccountPassword(accountPassword);
        return this;
    }
}
