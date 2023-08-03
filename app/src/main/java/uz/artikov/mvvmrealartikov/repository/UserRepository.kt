package uz.artikov.mvvmrealartikov.repository

import kotlinx.coroutines.flow.flow
import uz.artikov.mvvmrealartikov.database.dao.UserDao
import uz.artikov.mvvmrealartikov.database.entity.UserEntity
import uz.artikov.mvvmrealartikov.networking.ApiService

class UserRepository(private val apiService: ApiService,private val userDao: UserDao) {

    fun getUsers() = apiService.getUsers()

    fun addUsers(list: List<UserEntity>) = flow { emit(userDao.addUsers(list)) }

    fun getDatabaseUsers()= userDao.getUsers()

    fun getUserCount() = userDao.getUserCount()

}