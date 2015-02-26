package com.dpsimple.fragments;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user_sca on 27.02.2015.
 */
public class CarDescriptionDTOScreen implements Parcelable {

    public String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
    }

    public CarDescriptionDTOScreen() {
    }

    public CarDescriptionDTOScreen(String description) {
        this.description = description;
    }

    private CarDescriptionDTOScreen(Parcel in) {
        this.description = in.readString();
    }

    public static final Parcelable.Creator<CarDescriptionDTOScreen> CREATOR = new Parcelable.Creator<CarDescriptionDTOScreen>() {
        public CarDescriptionDTOScreen createFromParcel(Parcel source) {
            return new CarDescriptionDTOScreen(source);
        }

        public CarDescriptionDTOScreen[] newArray(int size) {
            return new CarDescriptionDTOScreen[size];
        }
    };
}
