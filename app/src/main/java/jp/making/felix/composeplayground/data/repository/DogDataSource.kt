package jp.making.felix.composeplayground.data.repository

import jp.making.felix.composeplayground.data.util.DogImage

interface DogDataSource {
    suspend fun getDogs(): DogImage
}