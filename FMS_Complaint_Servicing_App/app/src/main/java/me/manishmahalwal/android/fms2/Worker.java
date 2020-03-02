package me.manishmahalwal.android.fms2;

public class Worker {

    public String phoneNumber, assignedStatus, gender, name, assignedRoom, type, id, valid;
    public Worker(String id, String name, String number, String gender, String assignedStatus, String assignedRoom, String type, String valid) {
        this.id = id;
        this.valid = valid;
        this.type = type;
        this.name = name;
        this.assignedRoom = assignedRoom;
        this.phoneNumber = number;
        this.assignedStatus = assignedStatus;
        this.gender = gender;
    }
    public Worker(){

    }
}
