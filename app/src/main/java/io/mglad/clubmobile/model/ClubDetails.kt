package io.mglad.clubmobile.model

import java.io.Serializable

data class ClubDetails(val id: Int, val name: String, val description: String, val tweets: List<String>?) : Serializable
