package com.dpsimple.fragments.CarScreen;

import android.os.Parcel;
import android.os.Parcelable;

import com.dpsimple.models.CarModel;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarListDTOScreen implements Parcelable {

    public CarModel choosenCar;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.choosenCar, flags);
    }

    public CarListDTOScreen() {
    }

    private CarListDTOScreen(Parcel in) {
        this.choosenCar = in.readParcelable(CarModel.class.getClassLoader());
    }

    public static final Parcelable.Creator<CarListDTOScreen> CREATOR = new Parcelable.Creator<CarListDTOScreen>() {
        public CarListDTOScreen createFromParcel(Parcel source) {
            return new CarListDTOScreen(source);
        }

        public CarListDTOScreen[] newArray(int size) {
            return new CarListDTOScreen[size];
        }
    };
}
