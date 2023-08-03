package uz.artikov.mvvmrealartikov.networking

import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import uz.artikov.mvvmrealartikov.models.UserData

interface ApiService {

    @GET("users")
    fun getUsers():Flow<List<UserData>>

}