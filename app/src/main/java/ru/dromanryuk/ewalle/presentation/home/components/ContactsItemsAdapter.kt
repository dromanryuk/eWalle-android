package ru.dromanryuk.ewalle.presentation.home.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dromanryuk.ewalle.databinding.ItemContactBinding
import ru.dromanryuk.ewalle.presentation.home.model.ContactsListItem

class ContactsItemsAdapter : RecyclerView.Adapter<ContactItemsViewHolder>() {
    private var contacts: MutableList<ContactsListItem> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactItemsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactItemsViewHolder, position: Int) {
        val contactItem = contacts[position]
        holder.bind(contactItem)
    }

    override fun getItemCount(): Int = contacts.size

    fun setData(contacts: List<ContactsListItem>) {
        if (contacts.isNotEmpty()) {
            this.contacts = contacts as MutableList<ContactsListItem>
            notifyDataSetChanged()
        }
    }
}