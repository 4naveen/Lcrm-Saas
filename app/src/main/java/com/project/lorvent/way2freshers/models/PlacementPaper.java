package com.project.lorvent.way2freshers.models;

/**
 * Created by User on 2/14/2017.
 */

public class PlacementPaper {
    String company_logo,company_name,no_of_ques,paper_name_url;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getNo_of_ques() {
        return no_of_ques;
    }

    public void setNo_of_ques(String no_of_ques) {
        this.no_of_ques = no_of_ques;
    }

    public String getPaper_name_url() {
        return paper_name_url;
    }

    public void setPaper_name_url(String paper_name_url) {
        this.paper_name_url = paper_name_url;
    }
}
