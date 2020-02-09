package com.svtechcorp.schoolapp;

public class recyclerListItem {
    private String Head;
    private String Desc;

    public recyclerListItem(String Head, String Desc) {
        this.Head = Head;
        this.Desc = Desc;
    }

    public String getHead() {
        return Head;
    }

    public String getDesc() {
        return Desc;
    }
}
