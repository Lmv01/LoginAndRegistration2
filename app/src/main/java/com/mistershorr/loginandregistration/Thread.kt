package com.mistershorr.loginandregistration

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Thread(
    var description : String,
    var title: String,
    var objectId: String?,
    var ownerId: String?
): Parcelable
