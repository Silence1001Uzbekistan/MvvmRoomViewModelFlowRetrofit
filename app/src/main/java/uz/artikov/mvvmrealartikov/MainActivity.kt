package uz.artikov.mvvmrealartikov

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkingpractise.utils.NetWorkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import uz.artikov.mvvmrealartikov.database.AppDatabase
import uz.artikov.mvvmrealartikov.databinding.ActivityMainBinding
import uz.artikov.mvvmrealartikov.networking.ApiClient
import uz.artikov.mvvmrealartikov.vm.Resource
import uz.artikov.mvvmrealartikov.vm.UserViewModel
import uz.artikov.mvvmrealartikov.vm.UserViewModelFactory
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    //permission internet

    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this, UserViewModelFactory(
                AppDatabase.getInstance(this), ApiClient.apiService,
                NetWorkHelper(this)
            )
        )[UserViewModel::class.java]



        launch {

            viewModel.getStateFlowUser().collect {

                when (it) {
                    is Resource.Failure -> {

                        binding.txtId.text = "Failure"

                    }

                    is Resource.Loading -> {

                        binding.txtId.text = "Loading"

                    }

                    is Resource.Success -> {

                        binding.txtId.text = it.data.toString()

                    }
                }

            }

        }


    }

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

}