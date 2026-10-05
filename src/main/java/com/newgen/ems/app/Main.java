package com.newgen.ems.app;

import com.newgen.ems.model.*;
import com.newgen.ems.repository.EmployeeRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Employee Management System Application");
        Manager manager=new Manager(101,"Kalpesh Jadhav","Engineering",1200000,true,10);
        Developer developer=new Developer(102,"Kirti Jadhav","Engineering",130000,true,"java");
        Intern intern=new Intern(103,"Ganesh Dhangar","Enineering",15000,true,"Kalpesh Jadhav");

        Developer secondDeveloper=new Developer(104,"Sejal Patil","Engineering",86900,true,"Python");

        EmployeeRepository employeeRepository=new EmployeeRepository(2);
        employeeRepository.add(manager);
        employeeRepository.add(developer);
        employeeRepository.add(intern);
        employeeRepository.add(secondDeveloper);

        //print salary of every employee
        for(Employee employee:employeeRepository.findAll()){
            printPayslip(employee);
        }

        //linear search by id

        int searchID=103;
        Employee found=employeeRepository.findById(searchID);

        if(found!=null){
            System.out.println("Found Employee "+searchID+": "+found.designation());

        }else{
            System.out.println("Employee not found with ID: "+searchID);
        }


        char performanceGrade='A';
        double bonusMultiplier=switch(performanceGrade){
            case 'A'->0.20;
            case 'B'->0.10;
            default -> 0.0;
        };
    System.out.printf("%nBonus multiplier for grade %c: 5.0f%%%n",performanceGrade,bonusMultiplier*100);
    if(developer.getBaseSalary()>50.000 && developer.isActive()){
        System.out.println(developer.getName()+" is eligible for the annual stock grant.");

    }

        Promotable[] promotables={manager,developer,secondDeveloper};
        for(Promotable promotable:promotables){
           promotable.promote();
        }

        double yearToDate =0;
        for(int month=1;month<=12;month++){
            yearToDate+=manager.calculateMonthlySalary();

        }
        System.out.printf("%n%s year-to-date earnings after 12 months :%.2f%n",manager.getName(),yearToDate);

        System.out.println();
        System.out.println("Default Object#toString() (we'llfic this in Section 15)"+ manager);





    }
    private static void printPayslip(Employee employee){
       String payslip=String.format("""
               ---------------------------
               Payslip for %-15s
               Designation: %s
               Department: %s
               Monthly Pay: %.2f
               ---------------------------
               """,employee.getName(), employee.designation(), employee.getDepartment(),
               employee.calculateMonthlySalary());
       System.out.println(payslip);
    }

}