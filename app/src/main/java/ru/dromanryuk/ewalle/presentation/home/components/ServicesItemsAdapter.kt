package ru.dromanryuk.ewalle.presentation.home.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dromanryuk.ewalle.databinding.ItemServiceBinding
import ru.dromanryuk.ewalle.domain.model.service.Service

class ServicesItemsAdapter : RecyclerView.Adapter<ServicesItemsViewHolder>() {
    private var services: MutableList<Service> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicesItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemServiceBinding.inflate(inflater, parent, false)
        return ServicesItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicesItemsViewHolder, position: Int) {
        val serviceItem = services[position]
        holder.bind(serviceItem)
    }

    override fun getItemCount(): Int = services.size

    fun setData(services: List<Service>) {
        if (services.isNotEmpty()) {
            this.services = services as MutableList<Service>
            notifyDataSetChanged()
        }
    }
}