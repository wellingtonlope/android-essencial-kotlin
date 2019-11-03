package br.com.livroandroid.carros.domain

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Carro() : Parcelable {
    @PrimaryKey
    var id: Long = 0
    var tipo = ""
    var nome = ""
    var desc = ""
    //    @SerializedName("url_foto")
    var urlFoto = ""
    var urlInfo = ""
    var urlVideo = ""
    var latitude: String = ""
        get() = if (field.trim().isEmpty()) "0.0" else field
    var longitude: String = ""
        get() = if (field.trim().isEmpty()) "0.0" else field

    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        tipo = parcel.readString().toString()
        nome = parcel.readString().toString()
        desc = parcel.readString().toString()
        urlFoto = parcel.readString().toString()
        urlInfo = parcel.readString().toString()
        urlVideo = parcel.readString().toString()
        latitude = parcel.readString().toString()
        longitude = parcel.readString().toString()
    }

    override fun toString(): String {
        return "Carro(nome='$nome')"
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeString(tipo)
        parcel.writeString(nome)
        parcel.writeString(desc)
        parcel.writeString(urlFoto)
        parcel.writeString(urlInfo)
        parcel.writeString(urlVideo)
        parcel.writeString(latitude)
        parcel.writeString(longitude)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Carro> {
        override fun createFromParcel(parcel: Parcel): Carro {
            return Carro(parcel)
        }

        override fun newArray(size: Int): Array<Carro?> {
            return arrayOfNulls(size)
        }
    }
}