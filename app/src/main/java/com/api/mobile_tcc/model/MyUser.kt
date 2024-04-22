package com.api.mobile_tcc.model

class MyUser() {

    private var id: Long? = null
    private var username: String? = null
    private var email: String? = null
    private var password: String? = null

    fun getId(): Long? {
        return this.id
    }

    fun setId(id: Long?) {
        this.id = id
    }

    fun getUsername(): String? {
        return this.username
    }

    fun setUsername(username: String?) {
        this.username = username
    }

    fun getEmail(): String? {
        return this.email
    }

    fun setEmail(email: String?) {
        this.email = email
    }

    fun getPassword(): String? {
        return this.password
    }

    fun setPassword(password: String?) {
        this.password = password
    }
}
