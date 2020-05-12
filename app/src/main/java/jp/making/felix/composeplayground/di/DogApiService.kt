package jp.making.felix.composeplayground.di

import jp.making.felix.composeplayground.data.util.DogResponse
import retrofit2.http.GET

internal interface DogApiService {

    @GET("random/10")
    suspend fun getDogs(): DogResponse

    companion object {
        val ENDPOINT = "https://dog.ceo/api/breeds/image/"
    }
}