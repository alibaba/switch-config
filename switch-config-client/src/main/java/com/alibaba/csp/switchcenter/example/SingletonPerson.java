package com.alibaba.csp.switchcenter.example;

public class SingletonPerson {

    private String name;

    private int age;

    private Boolean isMale;

    private float money;

    private static SingletonPerson INSTANCE;

    public SingletonPerson(String name, int age, Boolean isMale, float money) {
        super();
        this.name = name;
        this.age = age;
        this.isMale = isMale;
        this.money = money;
    }

    public static synchronized SingletonPerson getInstance(String name, int age, Boolean isMale, float money) {
        if (INSTANCE == null) {
            return new SingletonPerson(name, age, isMale, money);
        }

        return INSTANCE;
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
