package Model

import android.os.Parcel
import android.os.Parcelable

class History (
               var nopol:String?,
               var namapemilik:String?,
               var merek:String?,
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nopol)
        parcel.writeString(namapemilik)
        parcel.writeString(merek)
    }
    var imageUri: String = ""
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<History> {
        override fun createFromParcel(parcel: Parcel): History {
            return History(parcel)
        }

        override fun newArray(size: Int): Array<History?> {
            return arrayOfNulls(size)
        }
    }
}