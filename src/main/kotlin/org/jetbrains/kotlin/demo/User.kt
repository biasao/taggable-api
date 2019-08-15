package org.jetbrains.kotlin.demo

import kotlinx.serialization.Serializable

@Serializable
data class User(val handle: String, val name: String)
