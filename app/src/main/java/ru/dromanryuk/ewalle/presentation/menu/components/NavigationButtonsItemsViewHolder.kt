package ru.dromanryuk.ewalle.presentation.menu.components

import android.graphics.Typeface
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import ru.dromanryuk.ewalle.databinding.ItemMenuBinding
import ru.dromanryuk.ewalle.presentation.menu.model.MenuButton

class NavigationButtonsItemsViewHolder(
    private val binding: ItemMenuBinding,
    private val navigateToSelectScreen: (buttonId: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(menuButton: MenuButton) = binding.run {
        this.button.text = menuButton.text
        if (menuButton.isEnabled) {
            this.button.typeface = Typeface.DEFAULT_BOLD
            this.currentIndicator.visibility = LinearLayout.VISIBLE
        } else {
            this.button.typeface = Typeface.DEFAULT
            this.currentIndicator.visibility = LinearLayout.INVISIBLE
        }

        this.button.setOnClickListener {
            navigateToSelectScreen(menuButton.id)
        }
    }
}