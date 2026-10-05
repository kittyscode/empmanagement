package com.newgen.ems.model;

public class Intern extends Employee
{

    public String mentorName;

    public Intern(int id, String name, String department, double baseSalary, boolean active, String mentorName) {
        super(id, name, department, baseSalary, active);
        this.mentorName = mentorName;
    }

    public String getMentorName() {
        return mentorName;
    }



    @Override
    public String designation() {
        return "Intern";
    }
}
