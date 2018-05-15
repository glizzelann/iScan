    package com.example.asus.iscan;


    import android.os.Parcel;
    import android.os.Parcelable;

    public class AppPermission implements Parcelable{
        private String name;
        private int type;

        public AppPermission(String name, int type) {
            this.name = name;
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public int getType() {
            return type;
        }

        @Override
        public String toString() {
            return "AppPermission{" +
                    "name='" + name + '\'' +
                    ", type=" + type +
                    '}';
        }

        public static class Type {
            public final static int NORMAL = 0;
            public final static int DANGEROUS = 1;
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(Parcel out, int flags) {
            out.writeString(name);
            out.writeInt(type);
        }

        public static final Parcelable.Creator<AppPermission> CREATOR
                = new Parcelable.Creator<AppPermission>() {
            public AppPermission createFromParcel(Parcel in) {
                return new AppPermission(in);
            }

            public AppPermission[] newArray(int size) {
                return new AppPermission[size];
            }
        };

        private AppPermission(Parcel in){
            name = in.readString();
            type = in.readInt();
        }
    }
