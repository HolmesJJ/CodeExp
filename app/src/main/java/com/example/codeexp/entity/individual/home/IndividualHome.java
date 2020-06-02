package com.example.codeexp.entity.individual.home;

import android.os.Parcel;
import android.os.Parcelable;

public class IndividualHome implements Parcelable {
    private int id;
    private String avatar;
    private String name;
    private String position;
    private int numberOfEmployees;
    private String salary;
    private String workingDate;

    public IndividualHome(int id, String avatar, String name, String position, int numberOfEmployees, String salary, String workingDate) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.position = position;
        this.numberOfEmployees = numberOfEmployees;
        this.salary = salary;
        this.workingDate = workingDate;
    }

    protected IndividualHome(Parcel in) {
        id = in.readInt();
        avatar = in.readString();
        name = in.readString();
        position = in.readString();
        numberOfEmployees = in.readInt();
        salary = in.readString();
        workingDate = in.readString();
    }

    public static final Creator<IndividualHome> CREATOR = new Creator<IndividualHome>() {
        @Override
        public IndividualHome createFromParcel(Parcel in) {
            return new IndividualHome(in);
        }

        @Override
        public IndividualHome[] newArray(int size) {
            return new IndividualHome[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getSalary() {
        return salary;
    }

    public String getWorkingDate() {
        return workingDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(avatar);
        dest.writeString(name);
        dest.writeString(position);
        dest.writeInt(numberOfEmployees);
        dest.writeString(salary);
        dest.writeString(workingDate);
    }
}
