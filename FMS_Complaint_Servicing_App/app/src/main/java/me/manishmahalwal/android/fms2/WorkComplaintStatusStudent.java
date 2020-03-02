package me.manishmahalwal.android.fms2;

public class WorkComplaintStatusStudent {
    String assignedRoom;
    String assignedStatus;

    public void setAssignedRoom(String assignedRoom) {
        this.assignedRoom = assignedRoom;
    }

    public void setAssignedStatus(String assignedStatus) {
        this.assignedStatus = assignedStatus;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    String gender;
    String id;
    String name;

    public String getAssignedRoom() {
        return assignedRoom;
    }

    public String getAssignedStatus() {
        return assignedStatus;
    }

    public String getGender() {
        return gender;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getType() {
        return type;
    }

    public String getValid() {
        return valid;
    }

    String phoneNumber;
    String type;
    String valid;
    public WorkComplaintStatusStudent(String a, String b, String c, String d, String e, String f, String g,String h) {
        this.assignedRoom = a;
        this.assignedStatus = b;
        this.gender = c;
        this.id = d;
        this.name = e;
        this.phoneNumber = f;
        this.type = g;
        this.valid = h;

    }


}