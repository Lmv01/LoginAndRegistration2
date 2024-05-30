package com.mistershorr.loginandregistration
import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Response(
    var downvotes: Int,
    var text: String,
    var upvotes: Int,
    var objectId: String?,
    var ownerId: String?
): Parcelable
