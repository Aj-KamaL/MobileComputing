package me.manishmahalwal.android.fms2;

public class ObjComplaintStatusStudent {

    String desc;
    String num;
    String room;
    String to;
    String type;
    String location;
    String priority;

    public ObjComplaintStatusStudent(String desc, String num, String room, String to, String type, String priority, String location) {
        this.desc = desc;
        this.num = num;
        this.room = room;
        this.to = to;
        this.type = type;
        this.priority = priority;
        this.location = location;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
}