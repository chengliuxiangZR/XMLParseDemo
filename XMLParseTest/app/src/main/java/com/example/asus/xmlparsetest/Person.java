package com.example.asus.xmlparsetest;

public class Person {
    Integer id;
    String name;
    Short age;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"+
                "id="+id+
                ",name="+name+
                ",age="+age+
                "}";
    }
}
