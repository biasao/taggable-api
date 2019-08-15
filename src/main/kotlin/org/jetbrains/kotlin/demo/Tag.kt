package org.jetbrains.kotlin.demo

import kotlinx.serialization.Serializable

@Serializable
data class Tag(val id: String, val url: String, val sourceUser: User, val targetUsers: List<User>)
