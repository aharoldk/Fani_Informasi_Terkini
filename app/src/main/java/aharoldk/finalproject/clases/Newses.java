package aharoldk.finalproject.clases;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alwandy on 12/07/17.
 */

public class Newses implements Parcelable{
    private String title, status, image, desc, currentDate;

    public Newses() {
    }

    public Newses(String title, String status, String image, String desc, String currentDate) {
        this.title = title;
        this.status = status;
        this.image = image;
        this.desc = desc;
        this.currentDate = currentDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(status);
        parcel.writeString(image);
        parcel.writeString(desc);
        parcel.writeString(currentDate);
    }
}
