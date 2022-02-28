package db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_information")
public class EventModalClass implements Parcelable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "tripName")
    private String tripName;

    @ColumnInfo(name = "trip_desc")
    private String tripDescription;

    @ColumnInfo(name = "trip_start_location")
    private String tripStartLocation;

    @ColumnInfo(name = "trip_destination")
    private String tripDestination;

    @ColumnInfo(name = "trip_start_date")
    private String tripStartDate;

    @ColumnInfo(name = "trip_end_date")
    private String tripEndDate;

    @ColumnInfo(name = "trip_budget")
    private String tripBudget;

    public EventModalClass(String tripName, String tripDescription, String tripStartLocation, String tripDestination, String tripStartDate, String tripEndDate, String tripBudget) {
        this.tripName = tripName;
        this.tripDescription = tripDescription;
        this.tripStartLocation = tripStartLocation;
        this.tripDestination = tripDestination;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.tripBudget = tripBudget;
    }

    protected EventModalClass(Parcel in) {
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

    public static final Creator<EventModalClass> CREATOR = new Creator<EventModalClass>() {
        @Override
        public EventModalClass createFromParcel(Parcel in) {
            return new EventModalClass(in);
        }

        @Override
        public EventModalClass[] newArray(int size) {
            return new EventModalClass[size];
        }
    };

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public void setTripDescription(String tripDescription) {
        this.tripDescription = tripDescription;
    }

    public void setTripStartLocation(String tripStartLocation) {
        this.tripStartLocation = tripStartLocation;
    }

    public void setTripDestination(String tripDestination) {
        this.tripDestination = tripDestination;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public void setTripBudget(String tripBudget) {
        this.tripBudget = tripBudget;
    }

    public String getTripName() {
        return tripName;
    }

    public String getTripDescription() {
        return tripDescription;
    }

    public String getTripStartLocation() {
        return tripStartLocation;
    }

    public String getTripDestination() {
        return tripDestination;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public String getTripBudget() {
        return tripBudget;
    }
}
