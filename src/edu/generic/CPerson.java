package edu.generic;

public class CPerson {

    protected String fName;
    protected String name;
    protected int year;

    public CPerson(String fName, String name, int year) {
        this.fName = fName;
        this.name = name;
        this.year = year;
    }

    @Override
    public String toString() {
        return fName + ' ' + name + ", ur. w " + year;
    }
}
