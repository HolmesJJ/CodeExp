package com.example.codeexp.entity.enterprise.home;

import android.os.Parcel;
import android.os.Parcelable;

public class EnterpriseHome implements Parcelable {
    private int id;
    private String avatar;
    private String name;
    private String position;
    private int numberOfEmployees;
    private String salary;
    private String workingDate;

    public EnterpriseHome(int id, String avatar, String name, String position, int numberOfEmployees, String salary, String workingDate) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.position = position;
        this.numberOfEmployees = numberOfEmployees;
        this.salary = salary;
        this.workingDate = workingDate;
    }

    protected EnterpriseHome(Parcel in) {
        id = in.readInt();
        avatar = in.readString();
        name = in.readString();
        position = in.readString();
        numberOfEmployees = in.readInt();
        salary = in.readString();
        workingDate = in.readString();
    }

    public static final Creator<EnterpriseHome> CREATOR = new Creator<EnterpriseHome>() {
        @Override
        public EnterpriseHome createFromParcel(Parcel in) {
            return new EnterpriseHome(in);
        }

        @Override
        public EnterpriseHome[] newArray(int size) {
            return new EnterpriseHome[size];
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
