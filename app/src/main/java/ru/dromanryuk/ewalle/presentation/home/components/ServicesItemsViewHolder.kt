package ru.dromanryuk.ewalle.presentation.home.components

import androidx.recyclerview.widget.RecyclerView
import ru.dromanryuk.ewalle.databinding.ItemServiceBinding
import ru.dromanryuk.ewalle.domain.model.service.Service

class ServicesItemsViewHolder(
    private val binding: ItemServiceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(service: Service) {
        binding.serviceImageView.setImageResource(service.img)
        binding.serviceTitleTextView.text = service.title
    }
}