package ua.epam.petproject.model;

public class Account {
    private String account;
    private AccountStatus accountStatus;
    private Long id;

    public Account() { }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    @Override
    public String toString() {
        return this.account == null ? "" : this.id + " " + this.account + " " + this.accountStatus;
    }
}