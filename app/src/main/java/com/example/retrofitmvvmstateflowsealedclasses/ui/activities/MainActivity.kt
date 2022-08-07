package com.example.retrofitmvvmstateflowsealedclasses.ui.activities

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.retrofitmvvmstateflowsealedclasses.databinding.ActivityMainBinding
import com.example.retrofitmvvmstateflowsealedclasses.utils.ApiResponse
import com.example.retrofitmvvmstateflowsealedclasses.viewmodels.MainViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var _binding: ActivityMainBinding? = null
    val binding get() = _binding
    private val mainViewModel: MainViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("application", "onCreate: Application Setup Successfully")

        mainViewModel.getUser()
        lifecycleScope.launch {
            mainViewModel.userStateFlow.collect{ aapiResponse ->
                when (aapiResponse) {

                    is ApiResponse.Loading -> {
                        Toast.makeText(this@MainActivity, "Data is Loading", Toast.LENGTH_SHORT).show()
                    }

                    is ApiResponse.Success -> {
                        Toast.makeText(this@MainActivity, "Data Loaded Successfull", Toast.LENGTH_SHORT).show()
                        Log.d("ApiData", "onCreate: ${aapiResponse.data?.body()}")
                    }

                    is ApiResponse.Failure -> {
                        Toast.makeText(this@MainActivity, "Data Loading Failed Error: ${aapiResponse.message}", Toast.LENGTH_SHORT).show()
                        Log.d("ApiData", "onCreate: ${aapiResponse.message}")
                    }
                    else -> {}
                }
            }

        }


        setContentView(binding?.root)
    }
}