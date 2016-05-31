package com.recglobal.example;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TestBean implements Serializable {

    private int id;
    private String name;

    public TestBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        // System.out.println("eq");
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return "*" + name;
    }

}
