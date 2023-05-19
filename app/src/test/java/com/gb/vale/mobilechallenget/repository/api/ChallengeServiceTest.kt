package com.gb.vale.mobilechallenget.repository.api

import com.gb.vale.mobilechallenget.repository.db.entity.RecipeEntity
import com.gb.vale.mobilechallenget.repository.network.ChallengeService
import com.gb.vale.mobilechallenget.utils.listJson
import com.gb.vale.mobilechallenget.utils.listJsonTest
import com.gb.vale.mobilechallenget.utils.parseStringGsonList
import com.google.gson.Gson
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(MockitoJUnitRunner::class)
class ChallengeServiceTest {

    lateinit var mockWebServer: MockWebServer
    lateinit var challengeService: ChallengeService
    lateinit var gson: Gson

    @Before
    fun setup() {
        gson = Gson()
        mockWebServer = MockWebServer()
        challengeService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build().create(ChallengeService::class.java)
    }


    @Test
    fun `validate path list api test`() {
        runBlocking {
             val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = challengeService.loadRecipes()
            val request = mockWebServer.takeRequest()
            assertEquals("/api/user/loadRecipes",request.path)
            assertEquals(true, response.body()?.isEmpty() == true)
        }
    }

    @Test
    fun `validate correct data list api test`() = runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody(listJsonTest))
            val response = challengeService.loadRecipes()
            assertEquals(true, response.body() == RecipeEntity.toListRecipeResponse(
                parseStringGsonList(listJsonTest)
            ))
    }

    @Test
    fun `validate incorrect data list api test`() = runBlocking {
        val mockResponse = MockResponse()
        mockWebServer.enqueue(mockResponse.setBody(listJson))
        val response = challengeService.loadRecipes()
        assertEquals(true, response.body() != RecipeEntity.toListRecipeResponse(
            parseStringGsonList(listJsonTest)
        ))
    }


    @Test
    fun `validate not empty list api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody(listJsonTest))
            val response = challengeService.loadRecipes()
            assertEquals(true, response.body()?.isNotEmpty() == true)
        }
    }

    @Test
    fun `validate  empty list api test`() {
        runBlocking {
            val mockResponse = MockResponse()
            mockWebServer.enqueue(mockResponse.setBody("[]"))
            val response = challengeService.loadRecipes()
            assertEquals(true, response.body()?.isEmpty() == true)
        }
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
    }

}