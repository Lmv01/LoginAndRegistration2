package com.mistershorr.loginandregistration
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Conversation(
    var description: String  = "",
    var downvotes: Int = 0,
    var threadId: String = "",
    var title: String= "",
    var upvotes: Int = 0,
    var objectId: String? = "",
    var ownerId: String? = ""

): Parcelable
