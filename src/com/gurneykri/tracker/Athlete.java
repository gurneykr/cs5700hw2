package com.gurneykri.tracker;

import java.util.Objects;

public class Athlete {
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

    public Athlete() { }

    public Athlete(int bibNumber, String firstName, String lastName, int age, String status, String gender) {
        this.bibNumber = bibNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.status = status;
        this.gender = gender;
    }

    public String getAthleteCommandString(){
        return "Athlete,"+ bibNumber + "," + firstName + "," + lastName + "," + gender + "," + age;
    }

    public String getAthleteStatusString(){
        return "Status,"+ bibNumber + "," + status + "," + startTime + "," + distance + "," + time + "," + finishTime;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
