package com.mistershorr.loginandregistration
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Response(
    var downvotes: Int = 0,
    var text: String = "",
    var upvotes: Int = 0,
    var conversationID: String = "",
    var objectId: String? = "",
    var ownerId: String?= ""
): Parcelable
