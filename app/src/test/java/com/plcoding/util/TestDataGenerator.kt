package com.plcoding.util

import androidx.compose.ui.input.key.Key.Companion.U
import com.plcoding.testingcourse.part7.domain.Post
import com.plcoding.testingcourse.part7.domain.Profile
import com.plcoding.testingcourse.part7.domain.User
import java.util.UUID


fun user(): User {
    return User(
        id = UUID.randomUUID().toString(), username = "Test User"
    )
}

fun post(userId: String): Post {
    return Post(
        id = UUID.randomUUID().toString(), userId = userId, title = "Test title", body = "Test body"
    )
}

fun profile(): Profile {
    val user = user()
    return Profile(user = user, posts = (1..10).map {
        post(userId = user.id)
    })
}