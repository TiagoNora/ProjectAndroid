package com.example.biblioteca.Model;

public class BibliotecaInfo {
    private String name;
    private String address;
    private String openTime;
    private String closeTime;
    private String openDays;

    public BibliotecaInfo(String name, String address, String openTime, String closeTime, String openDays){
        this.name = name;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
        this.openDays = openDays;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getOpenDays() {
        return openDays;
    }

    public void setOpenDays(String openDays) {
        this.openDays = openDays;
    }

    @Override
    public String toString() {
        return " Nome:" + name + "\n" +
                " Morada:" + address + "\n" +
                " Abertura:" + openTime + "\n" +
                " Fecho: " + closeTime + "\n" +
                " Dias Abertos:" + openDays + "\n";
    }


}
