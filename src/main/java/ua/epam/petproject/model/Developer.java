package ua.epam.petproject.model;

import java.util.HashSet;
import java.util.Set;

public class Developer {
    private Set<Skill> developerSkills = new HashSet<Skill>();
    private Account developerAccount;
    private String name;
    private Long id;

    public Developer() { }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDeveloperSkills(Skill developerSkills) {
        this.developerSkills.add(developerSkills);
    }

    public void setDeveloperSkillsSet(Set<Skill> developerSkillsSet) {
        this.developerSkills = developerSkillsSet;
    }

    public void setDeveloperAccount(Account developerAccount) {
        this.developerAccount = developerAccount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Set<Skill> getDeveloperSkillsSet() {
        return developerSkills;
    }

    public Account getDeveloperAccount() {
        return developerAccount;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name == null ? "" : this.id + " " + this.name + " " + this.developerAccount.getAccount()
                + " " + this.developerAccount.getAccountStatus() + " " + this.developerSkills.toString() ;
    }
}