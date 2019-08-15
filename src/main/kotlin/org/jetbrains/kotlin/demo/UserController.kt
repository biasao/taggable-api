package org.jetbrains.kotlin.demo

import org.json.JSONObject
import org.springframework.web.bind.annotation.*
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException



@RestController
class UserController {

    final val baseURL = "http://192.168.99.100:5984"

    @GetMapping("/users")
    fun getUsers(): User {
        val response = khttp.get(url = "$baseURL/users/")
        println(response)
        return User("mateus", "mateus")
    }

    @GetMapping("/users/{id}")
    fun getUser(@PathVariable id: String): User {
        val response = khttp.get(url = "$baseURL/users/$id")
        if(response.statusCode == 200) {
            return User(response.jsonObject.getString("handle"), response.jsonObject.getString("name"))
        }
        throw ResponseStatusException(HttpStatus.NOT_FOUND, "user not found")
    }

    @PostMapping("/users", "application/json", "application/json")
    fun postUser(@RequestBody user: User): User {
        val feedsUrl = "$baseURL/feeds/${user.handle}"
        val feedsResponse = khttp.put(url = feedsUrl, data = JSONObject(Feed(user)))
        if(feedsResponse.statusCode != 201) {
            throw ResponseStatusException(HttpStatus.valueOf(feedsResponse.statusCode))
        }

        val usersUrl = "$baseURL/users/${user.handle}"
        val usersResponse = khttp.put(url = usersUrl, data = JSONObject(user))
        if(usersResponse.statusCode == 201) {
            return user
        }
        throw ResponseStatusException(HttpStatus.valueOf(usersResponse.statusCode))
    }
}