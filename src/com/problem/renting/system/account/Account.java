package com.problem.renting.system.account;

import com.problem.renting.system.constants.AccountStatus;

public abstract class Account {

    private int accountId;
    private String name;
    private AccountStatus status ;

    public Account(int accountId, String name, AccountStatus status) {
        this.accountId = accountId;
        this.name = name;
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }



}
