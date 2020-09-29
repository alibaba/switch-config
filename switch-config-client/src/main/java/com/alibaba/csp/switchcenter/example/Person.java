package com.alibaba.csp.switchcenter.example;

public class Person {

    private String name;

    private int age;

    private Boolean isMale;

    private float money;


    public Person() {

    }

    public Person(String name, int age, Boolean isMale, float money) {
        super();
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.money = money;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }


    public Boolean getIsMale() {
        return isMale;
    }


    public void setIsMale(Boolean isMale) {
        this.isMale = isMale;
    }


    public float getMoney() {
        return money;
    }


    public void setMoney(float money) {
        this.money = money;
    }

}
