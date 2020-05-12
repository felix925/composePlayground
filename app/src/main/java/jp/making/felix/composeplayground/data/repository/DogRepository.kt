package jp.making.felix.composeplayground.data.repository

import jp.making.felix.composeplayground.data.util.DogImage
import jp.making.felix.composeplayground.data.util.toImageUrl
import jp.making.felix.composeplayground.di.DogApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class DogRepository @Inject constructor(
    private val dogApi:DogApiService
) : DogDataSource{
    override suspend fun getDogs(): DogImage {
        return dogApi.getDogs().toImageUrl()
    }
}