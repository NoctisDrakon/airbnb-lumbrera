package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by NoctisDrakon on 20/05/2017.
 */

public class ProfileItem implements Parcelable {

    public static final int TYPE_PROFILE_HEADER = 147;
    public static final int TYPE_PROFILE_ITEM = 123;

    public String name;
    public int type;

    public ProfileItem(String name) {
        this.name = name;
        type = TYPE_PROFILE_ITEM;
    }

    public ProfileItem() {
        type = TYPE_PROFILE_HEADER;
    }

    protected ProfileItem(Parcel in) {
        name = in.readString();
        type = in.readInt();
    }

    public static final Creator<ProfileItem> CREATOR = new Creator<ProfileItem>() {
        @Override
        public ProfileItem createFromParcel(Parcel in) {
            return new ProfileItem(in);
        }

        @Override
        public ProfileItem[] newArray(int size) {
            return new ProfileItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(type);
    }
}
