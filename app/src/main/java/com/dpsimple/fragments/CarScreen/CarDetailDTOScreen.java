package com.dpsimple.fragments.CarScreen;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarDetailDTOScreen implements Parcelable {

    public String name;



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
    }

    public CarDetailDTOScreen() {
    }



    private CarDetailDTOScreen(Parcel in) {
        this.name = in.readString();
    }

    public static final Creator<CarDetailDTOScreen> CREATOR = new Creator<CarDetailDTOScreen>() {
        public CarDetailDTOScreen createFromParcel(Parcel source) {
            return new CarDetailDTOScreen(source);
        }

        public CarDetailDTOScreen[] newArray(int size) {
            return new CarDetailDTOScreen[size];
        }
    };
}
