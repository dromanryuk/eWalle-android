package ru.dromanryuk.ewalle.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dromanryuk.ewalle.databinding.FragmentHomeBinding
import ru.dromanryuk.ewalle.navigation.navigateToLoginScreen
import ru.dromanryuk.ewalle.navigation.navigateToMenuScreen
import ru.dromanryuk.ewalle.presentation.home.components.ContactsItemsAdapter
import ru.dromanryuk.ewalle.presentation.home.components.ServicesItemsAdapter
import ru.dromanryuk.ewalle.presentation.home.model.HomeEditingEvent
import ru.dromanryuk.ewalle.presentation.home.model.HomeState
import ru.dromanryuk.ewalle.presentation.menu.model.MenuEditingEvent
import ru.dromanryuk.ewalle.presentation.util.observeWhileStarted

class HomeFragment : Fragment() {
    private val viewModel by viewModel<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val navigateToLoginCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.sendEvent(HomeEditingEvent.NavigateToLoginScreen)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToLoginCallback.isEnabled = false
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            navigateToLoginCallback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sendEvent = viewModel::sendEvent

        val contactsAdapter = ContactsItemsAdapter()
        val servicesAdapter = ServicesItemsAdapter()
        binding.contactsRecyclerView.adapter = contactsAdapter
        binding.servicesRecyclerView.adapter = servicesAdapter

        this.observeWhileStarted(viewModel.state) { state ->
            setButtonEvents(state, sendEvent)
            state.user?.let { user ->
                binding.balanceTextView.text = user.balance
            }
            contactsAdapter.setData(state.contacts)
            servicesAdapter.setData(state.services)
        }
    }

    override fun onResume() {
        super.onResume()
        navigateToLoginCallback.isEnabled = true
    }

    private fun setButtonEvents(state: HomeState, sendEvent: (event: HomeEditingEvent) -> Unit) {
        if (state.navigateToMenuScreen) {
            navigateToMenuScreen(findNavController())
        }
        if (state.navigateToLoginScreen) {
            navigateToLoginScreen(findNavController())
        }
        binding.MenuImageButton.setOnClickListener {
            sendEvent(HomeEditingEvent.NavigateToMenuScreen)
        }
    }
}
