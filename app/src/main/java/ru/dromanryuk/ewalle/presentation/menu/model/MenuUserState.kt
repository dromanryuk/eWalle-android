package ru.dromanryuk.ewalle.presentation.menu.model

import ru.dromanryuk.ewalle.domain.model.location.City
import ru.dromanryuk.ewalle.domain.model.user.User

data class MenuUserState(
    val id: Int,
    val name: String,
    val img: Int,
    val city: String
)

fun User.toMenuUser() = MenuUserState(
    id = id,
    name = name,
    img = img,
    city = userCityToText(city)
)

fun userCityToText(city: City): String {
    val state = city.state?.title
    if (state != null) {
        return "${city.title},${state}"
    } else {
        return "${city.title},${city.country.title}"
    }
}
