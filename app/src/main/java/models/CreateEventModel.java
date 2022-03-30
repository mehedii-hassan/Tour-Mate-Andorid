package models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_information")
public class CreateEventModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    int id;
    private String tripName;

    private String tripDescription;

    private String tripStartLocation;

    private String tripDestination;

    private String tripStartDate;

    private String tripEndDate;

    private String tripBudget;

    private String createDate;
    private String startDate;
    private String howDaysLeft;

    public CreateEventModel(@NonNull String tripName, String tripDescription, String tripStartLocation, String tripDestination, String tripStartDate, String tripEndDate, String tripBudget, String createDate, String startDate, String howDaysLeft) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.tripStartLocation = tripStartLocation;
        this.tripDestination = tripDestination;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.tripBudget = tripBudget;
        this.createDate = createDate;
        this.startDate = startDate;
        this.howDaysLeft = howDaysLeft;
    }

    protected CreateEventModel(Parcel in) {
        tripName = in.readString();
        tripDescription = in.readString();
        tripStartLocation = in.readString();
        tripDestination = in.readString();
        tripStartDate = in.readString();
        tripEndDate = in.readString();
        tripBudget = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(tripName);
        dest.writeString(tripDescription);
        dest.writeString(tripStartLocation);
        dest.writeString(tripDestination);
        dest.writeString(tripStartDate);
        dest.writeString(tripEndDate);
        dest.writeString(tripBudget);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CreateEventModel> CREATOR = new Creator<CreateEventModel>() {
        @Override
        public CreateEventModel createFromParcel(Parcel in) {
            return new CreateEventModel(in);
        }

        @Override
        public CreateEventModel[] newArray(int size) {
            return new CreateEventModel[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(@NonNull String tripName) {
        this.tripName = tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public String getTripStartLocation() {
        return tripStartLocation;
    }

    public void setTripStartLocation(String tripStartLocation) {
        this.tripStartLocation = tripStartLocation;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getTripBudget() {
        return tripBudget;
    }

    public void setTripBudget(String tripBudget) {
        this.tripBudget = tripBudget;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getHowDaysLeft() {
        return howDaysLeft;
    }

    public void setHowDaysLeft(String howDaysLeft) {
        this.howDaysLeft = howDaysLeft;
    }

    public static Creator<CreateEventModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString() {
        return "CreateEventModel{" +
                "tripName='" + tripName + '\'' +
                ", tripDescription='" + tripDescription + '\'' +
                ", tripStartLocation='" + tripStartLocation + '\'' +
                ", tripDestination='" + tripDestination + '\'' +
                ", tripStartDate='" + tripStartDate + '\'' +
                ", tripEndDate='" + tripEndDate + '\'' +
                ", tripBudget='" + tripBudget + '\'' +
                ", createDate='" + createDate + '\'' +
                ", startDate='" + startDate + '\'' +
                ", howDaysLeft='" + howDaysLeft + '\'' +
                '}';
    }
}
