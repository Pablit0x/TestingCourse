package com.plcoding.testingcourse.part7.presentation

import androidx.lifecycle.SavedStateHandle
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isNull
import com.plcoding.testingcourse.part4.presentation.GoodProfileViewModel
import com.plcoding.testingcourse.part7.data.UserRepositoryFake
import com.plcoding.util.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)

@ExtendWith(MainCoroutineExtension::class)
class ProfileViewModelTest {

    private lateinit var profileViewModel: ProfileViewModel
    private lateinit var userRepositoryFake: UserRepositoryFake


    @BeforeEach
    fun setUp(){

        userRepositoryFake = UserRepositoryFake()
        profileViewModel = ProfileViewModel(repository = userRepositoryFake,
            savedStateHandle = SavedStateHandle(
                initialState = mapOf("userId" to userRepositoryFake.profileToReturn.user.id)
            )
        )
    }

    @Test
    fun `Test loading profile success`()  = runTest {
        profileViewModel.loadProfile()

        advanceUntilIdle()

        assertThat(profileViewModel.state.value.profile).isEqualTo(userRepositoryFake.profileToReturn)
        assertThat(profileViewModel.state.value.isLoading).isFalse()
    }


    @Test
    fun `Test loading profile error`()  = runTest {
        userRepositoryFake.errorToReturn = Exception("Test Exception")
        profileViewModel.loadProfile()

        advanceUntilIdle()

        assertThat(profileViewModel.state.value.profile).isNull()
        assertThat(profileViewModel.state.value.isLoading).isFalse()
        assertThat(profileViewModel.state.value.errorMessage).isEqualTo("Test Exception")
    }



}