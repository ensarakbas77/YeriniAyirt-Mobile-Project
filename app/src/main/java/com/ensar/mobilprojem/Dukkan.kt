package com.ensar.mobilprojem

import android.os.Parcel
import android.os.Parcelable

data class Dukkan(val image:Int,val name:String):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(image)
        parcel.writeString(name)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Dukkan> {
        override fun createFromParcel(parcel: Parcel): Dukkan {
            return Dukkan(parcel)
        }

        override fun newArray(size: Int): Array<Dukkan?> {
            return arrayOfNulls(size)
        }
    }
}
