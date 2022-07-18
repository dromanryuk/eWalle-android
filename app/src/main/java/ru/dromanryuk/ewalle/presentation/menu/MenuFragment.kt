package ru.dromanryuk.ewalle.presentation.menu

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dromanryuk.ewalle.BuildConfig
import ru.dromanryuk.ewalle.R
import ru.dromanryuk.ewalle.databinding.FragmentMenuBinding
import ru.dromanryuk.ewalle.navigation.navigateToHomeScreen
import ru.dromanryuk.ewalle.navigation.navigateToLoginScreen
import ru.dromanryuk.ewalle.presentation.home.HomeFragment
import ru.dromanryuk.ewalle.presentation.menu.components.NavigationButtonsItemsAdapter
import ru.dromanryuk.ewalle.presentation.menu.model.MenuEditingEvent
import ru.dromanryuk.ewalle.presentation.menu.model.MenuState
import ru.dromanryuk.ewalle.presentation.util.observeWhileStarted

class MenuFragment : Fragment() {
    private val viewModel by viewModel<MenuViewModel>()

    private var _binding: FragmentMenuBinding? = null
    private val binding get() = _binding!!

    private val navigateToHomeCallback by lazy {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.sendEvent(MenuEditingEvent.NavigateToHomeScreen)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        navigateToHomeCallback.isEnabled = false
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            navigateToHomeCallback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sendEvent = viewModel::sendEvent

        val homeFragment = HomeFragment()
        loadFragment(homeFragment)

        val buttonsAdapter = initCategoriesItemAdapter(sendEvent)
        binding.navigationButtonsRecyclerView.adapter = buttonsAdapter

        binding.appVersion.text = "Version " + BuildConfig.VERSION_CODE.toString()

        this.observeWhileStarted(viewModel.state) { state ->
            setButtonEvents(state, sendEvent)
            state.user?.let { user ->
                binding.userName.text = user.name
                binding.userCity.text = user.city
                binding.userImageView.img = user.img
            }
            buttonsAdapter.setData(state.navigationButtons)
        }
    }

    override fun onResume() {
        super.onResume()
        navigateToHomeCallback.isEnabled = true
    }

    private fun loadFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.innerFragmentOverview, fragment)
            .setMaxLifecycle(fragment, Lifecycle.State.STARTED)
            .disallowAddToBackStack()
            .commit()
    }

    private fun initCategoriesItemAdapter(sendEvent: (event: MenuEditingEvent) -> Unit): NavigationButtonsItemsAdapter {
        return NavigationButtonsItemsAdapter(
            navigateToSelectScreen = { buttonId ->
                sendEvent(MenuEditingEvent.SelectNavigationButton(buttonId))
            }
        )
    }

    private fun setButtonEvents(state: MenuState, sendEvent: (event: MenuEditingEvent) -> Unit) {
        if (state.navigateToHomeScreen) {
            navigateToHomeScreen(findNavController())
        }
        if (state.navigateToLoginScreen) {
            navigateToLoginScreen(findNavController())
        }
        binding.exitButton.setOnClickListener {
            sendEvent(MenuEditingEvent.NavigateToHomeScreen)
        }
        binding.logoutButton.setOnClickListener {
            sendEvent(MenuEditingEvent.NavigateToLoginScreen)
        }
    }
}