package uz.artikov.mvvmrealartikov.vm

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.networkingpractise.utils.NetWorkHelper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.launch
import uz.artikov.mvvmrealartikov.database.AppDatabase
import uz.artikov.mvvmrealartikov.database.entity.UserEntity
import uz.artikov.mvvmrealartikov.mapper.mapToEntity
import uz.artikov.mvvmrealartikov.networking.ApiClient
import uz.artikov.mvvmrealartikov.networking.ApiService
import uz.artikov.mvvmrealartikov.repository.UserRepository

class UserViewModel(
    appDatabase: AppDatabase,
    apiService: ApiService,
    private val netWorkHelper: NetWorkHelper
) :
    ViewModel() {

    private val userRepository = UserRepository(apiService, appDatabase.userDao())
    private val stateFlow = MutableStateFlow<Resource<List<UserEntity>>>(Resource.Loading())

    init {
        fetchUsers()
    }

    private fun fetchUsers() {


        viewModelScope.launch {

            if (netWorkHelper.isNetWorkConnected()) {

                userRepository.getUsers().catch {

                    stateFlow.emit(Resource.Failure(it))

                }.flatMapConcat {

                    val list = ArrayList<UserEntity>()

                    it.forEach {

                        list.add(it.mapToEntity(it))

                    }

                    userRepository.addUsers(list)

                }.collect {

                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))

                }


            } else {

                if (userRepository.getUserCount() > 0) {

                    stateFlow.emit(Resource.Success(userRepository.getDatabaseUsers()))

                } else {

                    stateFlow.emit(Resource.Failure(Throwable("Internet not connection")))

                }


            }


        }

    }

    fun getStateFlowUser(): StateFlow<Resource<List<UserEntity>>> {

        return stateFlow

    }

}