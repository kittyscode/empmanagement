package com.newgen.ems.model;

public class Manager  extends Employee implements Promotable{

    private int teamsize;

    public Manager(int id, String name, String department, double baseSalary, boolean active, int teamsize) {
        super(id, name, department, baseSalary, active);
        this.teamsize = teamsize;
    }

    public int getTeamsize() {
        return teamsize;
    }


    public void setTeamsize(int teamsize) {}
    @Override
    public String designation() {
        return "Manager";
    }
    @Override
    public double calculateMonthlySalary() {
        double teamBonus=teamsize*50.0;
        return baseSalary+teamBonus;
    }
    @Override
    public String nextRole(){
        return "Senior Manager";
    }


}
