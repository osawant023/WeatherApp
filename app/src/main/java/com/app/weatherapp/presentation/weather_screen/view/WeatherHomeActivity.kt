package com.app.weatherapp.presentation.weather_screen.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.app.weatherapp.R
import com.app.weatherapp.databinding.ActivityMainBinding
import com.app.weatherapp.databinding.LayoutContainerWeatherDetailsBinding
import com.app.weatherapp.domain.model.DtoWeatherResponseModel
import com.app.weatherapp.domain.utill.ScreenState
import com.app.weatherapp.presentation.weather_screen.view_model.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WeatherHomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: WeatherViewModel by viewModels()
    private var isBackPressed: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        initBackPressedListener()
        initObserver()
        initSearch()
        initFields()
    }

    private fun initBackPressedListener() {
        onBackPressedDispatcher.addCallback {
            if (isBackPressed + 2000 > System.currentTimeMillis()) {
                finish()
            } else {
                Toast.makeText(this@WeatherHomeActivity, getString(R.string.label_back_press_msg), Toast.LENGTH_SHORT).show()
                isBackPressed = System.currentTimeMillis()
            }
        }
    }

    private fun initSearch() {
        binding.layoutSearch.edtSearch.setOnEditorActionListener { textView, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.query = binding.layoutSearch.edtSearch.text.toString()
                callWeatherDetails()
            }
            return@setOnEditorActionListener false
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.response.stateIn(lifecycleScope)
                .collectLatest {
                    viewModel.fetchUpdatedData = false
                    setData(it)
                }
        }
    }

    private fun setData(screenState: ScreenState) {
        when (screenState) {
            is ScreenState.Success<*> -> {
                showLoading(isLoading = false)
                (screenState.data as DtoWeatherResponseModel).let { data ->
                    binding.txtNotingFound.isVisible = data.error != null
                    binding.clMain.isVisible = !binding.txtNotingFound.isVisible
                    if (data.error == null) {
                        updateUi(data)
                    }
                }
            }

            is ScreenState.Error -> {
                showLoading(isLoading = false)
                binding.clMain.isVisible = false
                binding.txtNotingFound.isVisible = true
            }

            ScreenState.Loading -> {
                showLoading(isLoading = true)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.clMain.isVisible = false
        binding.lottieLoader.isVisible = isLoading && !binding.swipeRefresh.isRefreshing
        if (!isLoading) {
            binding.swipeRefresh.isRefreshing = false
        }
    }

    @SuppressLint("NewApi")
    private fun updateUi(data: DtoWeatherResponseModel) {
        binding.txtTemperature.text = buildString {
            append(data.current?.temp_c.toString())
            append(" °")
        }
        binding.txtLocationName.text = data.location?.name
        binding.layoutHumidity.init(getString(R.string.label_humidity), data.current?.humidity.toString())
        binding.layoutCloudy.init(getString(R.string.label_condition), data.current?.condition?.text ?: "")
        binding.layoutTime.init(getString(R.string.label_time), data.location?.getFormattedTime() ?: "")
    }

    private fun callWeatherDetails() {
        viewModel.getWeatherDetails()
    }

    private fun initFields() {
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchUpdatedData = true
            callWeatherDetails()
        }
        callWeatherDetails()
    }
}

private fun LayoutContainerWeatherDetailsBinding.init(label: String, value: String) {
    txtLabel.text = label
    txtValue.text = value
}
