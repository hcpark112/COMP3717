package ca.bcit.finalexam;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Movie {
    private String title;
    private String desc;
    private String link;

    public Movie() {

    }

    public Movie(String title, String desc, String link) {
        this.title = title;
        this.desc = desc;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String new_title) {
        this.title = new_title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String new_desc) {
        this.desc = new_desc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String new_link) {
        this.link = new_link;
    }
}
