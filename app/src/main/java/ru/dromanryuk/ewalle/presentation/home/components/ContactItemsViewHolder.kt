package ru.dromanryuk.ewalle.presentation.home.components

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.View
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import ru.dromanryuk.ewalle.databinding.ItemContactBinding
import ru.dromanryuk.ewalle.domain.model.user.User
import ru.dromanryuk.ewalle.presentation.home.model.ContactsListItem

class ContactItemsViewHolder(
    private val binding: ItemContactBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(contactsListItem: ContactsListItem) {
        when (contactsListItem) {
            is ContactsListItem.AddContactButton -> bindAddButtonData()
            is ContactsListItem.ContactListItem -> bindContactData(contactsListItem.user)
        }
    }

    private fun bindAddButtonData() {
        binding.addContactButton.visibility = View.VISIBLE
        binding.contactCardItem.visibility = View.GONE
    }

    private fun bindContactData(user: User) {
        binding.addContactButton.visibility = View.GONE
        binding.contactCardItem.visibility = View.VISIBLE

        binding.contactNameTextView.text = user.name
        binding.contactImageView.img = user.img
    }
}