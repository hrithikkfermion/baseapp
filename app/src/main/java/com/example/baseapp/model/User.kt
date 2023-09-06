package com.example.baseapp.model


import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("support")
    var support: Support
) {
    data class Data(
        @SerializedName("avatar")
        var avatar: String,
        @SerializedName("email")
        var email: String,
        @SerializedName("first_name")
        var firstName: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("last_name")
        var lastName: String
    )

    data class Support(
        @SerializedName("text")
        var text: String,
        @SerializedName("url")
        var url: String
    )
}