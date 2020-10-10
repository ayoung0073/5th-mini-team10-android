package com.moonayoung.greenlife.api

import com.google.gson.annotations.SerializedName

class User{
    @SerializedName("email") // JSON 객체 매칭
    var email: String
    @SerializedName("nickname")
    var nickname: String = ""
    @SerializedName("password")
    var password: String

    constructor(email:String, nickname: String, password: String)

    {

        this.email = email

        this.nickname = nickname

        this.password = password

    }

    constructor(email:String, password: String)

    {

        this.email = email

        this.password = password

    }
}



