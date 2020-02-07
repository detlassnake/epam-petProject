package ua.epam.petproject.model;

public class Skill {
    private String skill;
    private Long id;

    public Skill() { }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkill() {
        return skill;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.skill == null ? "" : this.id + " " + this.skill;
    }
}