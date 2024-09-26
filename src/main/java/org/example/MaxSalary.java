package org.example;

public class MaxSalary {
    private String name;
    private int salary;

    @Override
    public String toString() {
        return "MaxSalary{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public MaxSalary(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
}
