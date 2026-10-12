package com.newgen.ems.app;

import com.newgen.ems.model.*;
import com.newgen.ems.repository.EmployeeNotFoundException;
import com.newgen.ems.repository.EmployeeRepository;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("Employee Management System Application");
        Manager manager=new Manager(101,"Kalpesh Jadhav","Engineering",1200000, 10);
        Developer developer=new Developer(102,"Kirti Jadhav","Engineering",130000, "java");
        Intern intern=new Intern(103,"Ganesh Dhangar","Enineering",15000, "Kalpesh Jadhav");

        Developer secondDeveloper=new Developer(104,"Sejal Patil","Engineering",86900, "Python");

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


        try {
            Employee found = employeeRepository.findById(searchID);
            System.out.println("Found Employee " + searchID + ": " + found.designation());
        } catch (EmployeeNotFoundException ex) {
            System.out.println("Employee not found with ID: " + searchID);
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


//        System.out.println();
//        System.out.println("Default Object#toString() (we'll fix this in Section 15)"+ manager);
        runConsoleMenu(employeeRepository);

    }
    private static void runConsoleMenu(EmployeeRepository employeeRepository){

        //automatically close scanner no manual need for close scanner with codeline
           try(Scanner sc=new Scanner(System.in)) {
               boolean running = true;
               while(running) {
               System.out.println();
               System.out.println("1. Add Employee");

               System.out.println("2. Find Employee By ID");

               System.out.println("3. List All Employees ");

               System.out.println("4. Exit");

               System.out.println("Choose an Option: ");

               int choice = sc.nextInt();
               switch (choice) {

                   case 1 -> addEmployeeFromConsole(sc, employeeRepository);

                   case 2 -> {
                       System.out.println("Enter Employee id: ");
                       int id = sc.nextInt();
                       printPayslip(employeeRepository.findById(id));

                   }
                   case 3 -> {

                       for (Employee employee : employeeRepository.findAll()) {
                           printPayslip(employee);
                       }
                   }
                   case 4->{
                       running=false;
                   }
                   default -> {
                       System.out.println("Invalid Option Choose 1 to 3 ");
                   }
                 }
               }
           }


    }

    private static void addEmployeeFromConsole(Scanner sc,EmployeeRepository employeeRepository){
       System.out.println("Enter Employee id: ");
       int id=sc.nextInt();

       System.out.println("Enter Employee name: ");
       String name=sc.next();

       System.out.println("Department");
       String department=sc.next().trim();

       System.out.println("Base Salary");
       double baseSalary=sc.nextDouble();

       System.out.println("1. Manager   2.Developer  3.Intern");
       System.out.println("Employee type: ");
       int type=sc.nextInt();

       Employee newEmployee= switch(type){
           case 1 -> {
               System.out.println("Team Size: ");
               int teamSize = sc.nextInt();

               yield new Manager(id, name, department, baseSalary, teamSize);
           }

           case 2 ->{
               System.out.print("Primary language: ");
               String language=sc.next();
               yield new Developer(id, name, department, baseSalary, language);
           }

           default -> {
               System.out.println("Mentor Name");
               String mentorName=sc.next();
               yield new Intern(id, name, department, baseSalary,  mentorName);
           }
       };

       employeeRepository.add(newEmployee);
       System.out.println("Employee "+id+" has been added");
       printPayslip(newEmployee);
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