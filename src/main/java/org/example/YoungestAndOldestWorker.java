package org.example;

import java.util.Date;

public class YoungestAndOldestWorker {
    private String name;
    private Date dateOfBirth;
    private String type;

    @Override
    public String toString() {
        return "YoungestAndOldestWorker{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", type='" + type + '\'' +
                '}';
    }

    public YoungestAndOldestWorker(String name, Date dateOfBirth, String type) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
