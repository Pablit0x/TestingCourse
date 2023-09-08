package com.plcoding.testingcourse.part7.data

import com.plcoding.testingcourse.part7.domain.Post
import com.plcoding.testingcourse.part7.domain.User

class UserApiFake : UserApi {

    val users = listOf(
        User(id = "1", username = "Paul"),
        User(id = "2", username = "Jake"),
        User(id = "3", username = "Adam")
    )

    val posts = listOf(
        Post(id = "1", userId = "1", "Post 1", "Post 1 Body"),
        Post(id = "2", userId = "1", "Post 2", "Post 2 Body"),
        Post(id = "3", userId = "1", "Post 3", "Post 3 Body"),
        Post(id = "4", userId = "1", "Post 4", "Post 4 Body"),
        Post(id = "5", userId = "2", "Post 5", "Post 5 Body")
    )
    override suspend fun getUser(id: String): User? {
        return users.find { it.id == id }
    }

    override suspend fun getPosts(id: String): List<Post> {
        return posts.filter {
            it.userId == id
        }
    }
}