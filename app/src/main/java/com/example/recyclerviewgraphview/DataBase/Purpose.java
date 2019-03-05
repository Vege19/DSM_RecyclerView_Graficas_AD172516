package com.example.recyclerviewgraphview.DataBase;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

@Entity
public class Purpose implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "purpose_id")
    public int purpose_id;

    @ColumnInfo(name = "purpose_title")
    public String purpose_title;

    @ColumnInfo(name = "purpose_description")
    public String purpose_description;

    @ColumnInfo(name = "purpose_date")
    public String purpose_date;


    public Purpose(String purpose_title, String purpose_description, String purpose_date) {
        this.purpose_title = purpose_title;
        this.purpose_description = purpose_description;
        this.purpose_date = purpose_date;
    }

    protected Purpose(Parcel in) {
        purpose_id = in.readInt();
        purpose_title = in.readString();
        purpose_description = in.readString();
        purpose_date = in.readString();
    }

    public static final Creator<Purpose> CREATOR = new Creator<Purpose>() {
        @Override
        public Purpose createFromParcel(Parcel in) {
            return new Purpose(in);
        }

        @Override
        public Purpose[] newArray(int size) {
            return new Purpose[size];
        }
    };

    public int getPurpose_id() {
        return purpose_id;
    }

    public void setPurpose_id(int purpose_id) {
        this.purpose_id = purpose_id;
    }

    public String getPurpose_title() {
        return purpose_title;
    }

    public void setPurpose_title(String purpose_title) {
        this.purpose_title = purpose_title;
    }

    public String getPurpose_description() {
        return purpose_description;
    }

    public void setPurpose_description(String purpose_description) {
        this.purpose_description = purpose_description;
    }

    public String getPurpose_date() {
        return purpose_date;
    }

    public void setPurpose_date(String purpose_date) {
        this.purpose_date = purpose_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(purpose_id);
        dest.writeString(purpose_title);
        dest.writeString(purpose_description);
        dest.writeString(purpose_date);
    }
}

