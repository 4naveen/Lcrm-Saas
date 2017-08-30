package com.project.lorvent.way2freshers.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 3/25/2017.
 */

public class JobList implements Parcelable {
    String title,url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
    }

    public JobList() {
    }

    protected JobList(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
    }

    public static final Parcelable.Creator<JobList> CREATOR = new Parcelable.Creator<JobList>() {
        @Override
        public JobList createFromParcel(Parcel source) {
            return new JobList(source);
        }

        @Override
        public JobList[] newArray(int size) {
            return new JobList[size];
        }
    };
}
