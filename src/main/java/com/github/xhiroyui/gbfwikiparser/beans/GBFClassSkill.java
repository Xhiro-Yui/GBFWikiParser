package com.github.xhiroyui.gbfwikiparser.beans;

public class GBFClassSkill implements GBFWikiObject {

    private String ix;
    private String family;
    private int row;
    private String job; // Getters and setters are class instead of job as that is the actual name of the field but it is not acceptable as a variable name in programming.
//    private File icon;
    private String name;
    private String description;
    private String cooldown;
    private String duration;
    private int empCost;
    private String notes;
    private boolean ex;

    public String getIx() {
        return ix;
    }

    public void setIx(String ix) {
        this.ix = ix;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCooldown() {
        return cooldown;
    }

    public void setCooldown(String cooldown) {
        this.cooldown = cooldown;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getEmpCost() {
        return empCost;
    }

    public void setEmpCost(int empCost) {
        this.empCost = empCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public boolean isEx() {
        return ex;
    }

    public void setEx(boolean ex) {
        this.ex = ex;
    }
}
