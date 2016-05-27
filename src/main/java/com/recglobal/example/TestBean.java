package com.recglobal.example;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TestBean implements Serializable {

    private String name;

    public TestBean(String name) {
        this.name = name;
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

}
