package com.example.b07project;

public class Complaints {
    String subject;
    String complaints;
//    boolean visibility;

    public Complaints(String subject, String complaints) {
        this.complaints = complaints;
        this.subject = subject;
//        this.visibility = false;
    }

    public String getSubject() {
        return subject;
    }

    public String getComplaints() {
        return complaints;
    }

//    public boolean isVisibility(){
//        return visibility;
//    }
//    public void setVisibility(boolean b) {
//        this.visibility = b;
//        Log.d("Complaints", "Visibility set to: " + b);
//    }

}
