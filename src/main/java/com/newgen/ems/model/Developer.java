package com.newgen.ems.model;

public class Developer extends Employee implements Promotable{
    private String primaryLanguage;

    public Developer(int id, String name, String department, double baseSalary, String primaryLanguage) {
        super(id, name, department, baseSalary, true);
        this.primaryLanguage = primaryLanguage;
    }

    public String getPrimaryLanguage() {
        return primaryLanguage;
    }
    public double calculateMonthlySalay(){
    boolean highDemandSkill=(primaryLanguage.equalsIgnoreCase("java") || primaryLanguage.equalsIgnoreCase("Python"));
    double skillBonus= highDemandSkill ? baseSalary*0.10 : baseSalary*0.05;
    return baseSalary+skillBonus;
    }

    @Override
    public String designation() {
        return "Software Developer";
    }
    @Override
    public String nextRole() {
        return "Senior Developer";
    }
}
