package com.newgen.ems.model;

public interface Payable {
    double calculateMonthlySalary();
    //defualt make the method not mandetory
    default double calculateAnnualSalary(){
        return calculateMonthlySalary() * 12;
    }

}
