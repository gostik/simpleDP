package com.dpsimple.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by user_sca on 26.02.2015.
 */
public class CarModel extends Model implements Parcelable {

    public CarModel(Long id, String name) {
        super(id, name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
    }

    private CarModel(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
    }

    public static final Parcelable.Creator<CarModel> CREATOR = new Parcelable.Creator<CarModel>() {
        public CarModel createFromParcel(Parcel source) {
            return new CarModel(source);
        }

        public CarModel[] newArray(int size) {
            return new CarModel[size];
        }
    };
}
