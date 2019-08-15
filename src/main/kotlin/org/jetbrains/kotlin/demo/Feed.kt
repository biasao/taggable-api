package org.jetbrains.kotlin.demo

import kotlinx.serialization.Serializable

@Serializable
data class Feed(val user: User, val tags: MutableList<Tag> = mutableListOf<Tag>(), val _rev: String = "")
