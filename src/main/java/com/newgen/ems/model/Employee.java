package com.newgen.ems.model;

public abstract class Employee implements Payable {
    private final int id;
    private final String name;
    private String department;
    protected double baseSalary;
    private boolean active;

    public Employee(int id, String name, String department, double baseSalary, boolean active) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.baseSalary = baseSalary;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }


    public abstract String designation();

    @Override
    public double calculateMonthlySalary() {
        return baseSalary;
    }

}