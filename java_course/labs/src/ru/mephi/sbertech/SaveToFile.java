package ru.mephi.sbertech;

public class SaveToFile {

    public static void main(String[] args) {

    }
}

class Flight {
    int number;
    String name;

    public Flight(int flightNumber, String flightName) {
        this.number = flightNumber;
        this.name = flightName;
    }

    public void saveToFile(String fileName) {

    }

    public static Flight createFromFile(String fileName) {
        return null;
    }
}