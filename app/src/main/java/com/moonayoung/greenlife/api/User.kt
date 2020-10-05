package com.moonayoung.greenlife.api

class User{
    var email: String

    var nickname: String = ""

    var passwd: String

    constructor(email:String, nickname: String, passwd: String)

    {

        this.email = email

        this.nickname = nickname

        this.passwd = passwd

    }

    constructor(email:String, passwd: String)

    {

        this.email = email

        this.passwd = passwd

    }
}



