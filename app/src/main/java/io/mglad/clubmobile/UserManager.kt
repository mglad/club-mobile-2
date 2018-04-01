package io.mglad.clubmobile

import io.mglad.clubmobile.model.User

class UserManager {
    companion object {
        var sessionId: String? = null
        var user: User? = null
    }

    fun getSessionId() : String? {
        return sessionId
    }

    fun setSessionId(sId: String) {
        sessionId = sId
    }

    fun getUser() : User? {
        return user
    }

    fun setUser(u: User) {
        user = u
    }

    fun clear() {
        sessionId = null
        user = null
    }
}