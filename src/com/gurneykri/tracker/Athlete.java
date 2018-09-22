package com.gurneykri.tracker;

import java.util.Objects;

public class Athlete {
    private String name;
    private int bibNumber;
    private String firstName;
    private String lastName;
    private int age;
    private String status;
    private double distance;
    private int time;
    private int startTime;
    private int finishTime;

    private String gender;

    public String getAthleteCommandString(){
        return "Athlete,"+ bibNumber + "," + firstName + "," + lastName + "," + gender + "," + age;
    }

    public String getAthleteStatusString(){
        return "Status,"+ bibNumber + "," + status + "," + startTime + "," + distance + "," + time + "," + finishTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBibNumber() {
        return bibNumber;
    }

    public void setBibNumber(int bibNumber) {
        this.bibNumber = bibNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Athlete athlete = (Athlete) o;
        return bibNumber == athlete.bibNumber;
    }

    @Override
    public int hashCode() {

        return Objects.hash(bibNumber);
    }
}
