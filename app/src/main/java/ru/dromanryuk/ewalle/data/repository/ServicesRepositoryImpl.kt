package ru.dromanryuk.ewalle.data.repository

import ru.dromanryuk.ewalle.R
import ru.dromanryuk.ewalle.domain.model.service.Service
import ru.dromanryuk.ewalle.domain.repository.ServicesRepository

class ServicesRepositoryImpl : ServicesRepository {
    override fun getAllServices(): List<Service> = getServicesList()
}

private fun getServicesList(): List<Service> {
    val service1 = Service(
        id = 1,
        title = "Send Money",
        img = R.drawable.ic_send_money
    )

    val service2 = Service(
        id = 2,
        title = "Receive Money",
        img = R.drawable.ic_receive_money
    )

    val service3 = Service(
        id = 3,
        title = "Mobile Prepaid",
        img = R.drawable.ic_mobile_prepaid
    )

    val service4 = Service(
        id = 4,
        title = "Electricity Bill",
        img = R.drawable.ic_electricity_bill
    )

    val service5 = Service(
        id = 5,
        title = "Cashback Offer",
        img = R.drawable.ic_cashback_offer
    )

    val service6 = Service(
        id = 6,
        title = "Movie Tickets",
        img = R.drawable.ic_movie_tickets
    )

    val service7 = Service(
        id = 7,
        title = "Flight Tickets",
        img = R.drawable.ic_flight_tickets
    )

    val service8 = Service(
        id = 8,
        title = "More Options",
        img = R.drawable.ic_more_options
    )
    return listOf(service1, service2, service3, service4, service5, service6, service7, service8)
}