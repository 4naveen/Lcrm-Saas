package com.project.lorvent.way2freshers.models;

/**
 * Created by User on 3/2/2017.
 */

public class Exam {
    String exam_name,category_id;

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getExam_name() {
        return exam_name;
    }

    public void setExam_name(String exam_name) {
        this.exam_name = exam_name;
    }
}
