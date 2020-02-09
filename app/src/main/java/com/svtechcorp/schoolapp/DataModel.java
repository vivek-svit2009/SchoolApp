package com.svtechcorp.schoolapp;


public class DataModel {

    int Id;
    String Clas;
    String Description;
    String LastDate;
    String IsAttachemet;
    String Url;
    String Subject;
    String Date;

    public DataModel(int Id,String Clas, String Description, String LastDate, String IsAttachemet, String Url, String Date, String Subject ) {
        this.Id=Id;
        this.Clas=Clas;
        this.Description=Description;
        this.LastDate=LastDate;
        this.IsAttachemet=IsAttachemet;
        this.Url = Url;
        this.Date=Date;
        this.Subject = Subject;

    }


    public int getId() {
        return Id;
    }


    public String getClas() {
        return Clas;
    }

    public String getDescription() {
        return Description;
    }

    public String getLastDate() {
        return LastDate;
    }

    public String getIsAttachemet() {
        return IsAttachemet;
    }

    public String getUrl() {
        return Url;
    }

    public String getDate() {
        return Date;
    }

    public  String getSubject(){return  Subject; }

}
