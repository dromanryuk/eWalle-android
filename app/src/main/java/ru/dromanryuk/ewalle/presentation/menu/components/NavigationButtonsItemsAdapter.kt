package ru.dromanryuk.ewalle.presentation.menu.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.dromanryuk.ewalle.databinding.ItemMenuBinding
import ru.dromanryuk.ewalle.databinding.ItemServiceBinding
import ru.dromanryuk.ewalle.domain.model.service.Service
import ru.dromanryuk.ewalle.presentation.menu.model.MenuButton

class NavigationButtonsItemsAdapter(
    private val navigateToSelectScreen: (buttonId: Int) -> Unit
) : RecyclerView.Adapter<NavigationButtonsItemsViewHolder>() {
    private var buttons: MutableList<MenuButton> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationButtonsItemsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemMenuBinding.inflate(inflater, parent, false)
        return NavigationButtonsItemsViewHolder(
            binding = binding,
            navigateToSelectScreen = { buttonId ->
                navigateToSelectScreen(buttonId)
            }
        )
    }

    override fun onBindViewHolder(holder: NavigationButtonsItemsViewHolder, position: Int) {
        val button = buttons[position]
        holder.bind(button)
    }

    override fun getItemCount(): Int = buttons.size

    fun setData(buttons: List<MenuButton>) {
        if (buttons.isNotEmpty()) {
            this.buttons = buttons as MutableList<MenuButton>
            notifyDataSetChanged()
        }
    }
}