package jp.making.felix.composeplayground.data.util

import jp.making.felix.composeplayground.data.util.DogResponse

fun DogResponse.toImageUrl():DogImage{
    return DogImage(url = message)
}