package com.newgen.ems.repository;

import com.newgen.ems.model.Employee;

import java.util.Arrays;



public class EmployeeRepository {
    private Employee[] employees;
    private int count;

    public EmployeeRepository(int initialCapacity) {

        this.employees = new Employee[initialCapacity];
        this.count = 0;
    }
    public void add(Employee employee ){


        if(employee==null){
           System.out.println("Employee is null");
        }

        if(search(employee.getId())!=null){
                    throw new DuplicateEmployeeIdException("employee Id "+employee.getId()+" Already exists");
        }


        if(count== employees.length){
                int newCapacity = employees.length*2;
                System.out.printf("Reposotory full (capacity %d) -- growing to %d.%n",employees.length,newCapacity);
                employees = Arrays.copyOf(employees, newCapacity);
        }
        employees[count]=employee;
        count++;
        }

        private Employee search(int id){
            for(int i=0;i<count;i++){
                if(employees[i].getId()==id){
                    return employees[i];
                }
            }
            return null;

        }

        public Employee findById(int id) throws EmployeeNotFoundException {

            Employee employee=search(id);
            if(employee==null){
                throw new EmployeeNotFoundException("No employee with id "+id);

            }
            return employee;


        }
        public Employee[] findAll(){
        return Arrays.copyOf(employees,count);

        }
        public int size(){
        return count;
        }

}
