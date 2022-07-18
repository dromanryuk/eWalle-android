package ru.dromanryuk.ewalle.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.toJavaLocalDateTime
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.dromanryuk.ewalle.databinding.FragmentLoginBinding
import ru.dromanryuk.ewalle.domain.model.location.Weather
import ru.dromanryuk.ewalle.navigation.navigateToHomeScreen
import ru.dromanryuk.ewalle.presentation.login.model.LoginEditingEvent
import ru.dromanryuk.ewalle.presentation.util.observeWhileStarted
import java.time.format.DateTimeFormatter
import java.util.*

class LoginFragment : Fragment()  {
    private val viewModel by viewModel<LoginViewModel>()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val sendEvent = viewModel::sendEvent
        this.observeWhileStarted(viewModel.state) { state ->
            if (state.navigateToHomeScreen) {
                navigateToHomeScreen(findNavController())
            }
            setCurrentWeather(state.weather)
            setCurrentDateTime(state.currentDate, state.currentTime)
        }

        binding.LoginButton.setOnClickListener {
            sendEvent(LoginEditingEvent.NavigateToHomeScreen)
        }
    }

    private fun setCurrentDateTime(currentDate: String, currentTime: String) {
        binding.TodayTextView.text = currentDate
        binding.TimeTextView.text = currentTime
    }

    private fun setCurrentWeather(weather: Weather) {
        binding.WeatherImageView.setBackgroundResource(weather.img)
        binding.TemperatureTextView.text = weather.temperature.toString() + "°C"
    }
}