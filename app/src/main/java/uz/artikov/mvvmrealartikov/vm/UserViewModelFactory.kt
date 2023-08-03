package uz.artikov.mvvmrealartikov.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.networkingpractise.utils.NetWorkHelper
import uz.artikov.mvvmrealartikov.database.AppDatabase
import uz.artikov.mvvmrealartikov.networking.ApiService

class UserViewModelFactory(
    private val appDatabase: AppDatabase,
    private val apiService: ApiService,
    private val netWorkHelper: NetWorkHelper
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {

            return UserViewModel(appDatabase, apiService, netWorkHelper) as T

        }

        return throw Exception("Error")
    }
}