package com.mistershorr.loginandregistration
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Conversation(
    var description: String,
    var downvotes: Int,
    var threadId: String,
    var title: String,
    var upvotes: Int,
    var ownerId: String?,
    var objectId: String?
): Parcelable
