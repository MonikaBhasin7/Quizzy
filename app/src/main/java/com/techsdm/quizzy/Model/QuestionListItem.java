package com.techsdm.quizzy.Model;

/**
 * Created by Monika Bhasin on 24-07-2018.
 */

public class QuestionListItem {

    public String name;
    public String imageLink;

    public QuestionListItem() {
    }

    public QuestionListItem(String name, String imageLink) {
        this.name = name;
        this.imageLink = imageLink;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }
}
