package uz.artikov.mvvmrealartikov.mapper

import uz.artikov.mvvmrealartikov.database.entity.UserEntity
import uz.artikov.mvvmrealartikov.models.UserData

fun UserData.mapToEntity(userData: UserData):UserEntity{

    return UserEntity(userData.id?:0,userData.login?:"",userData.avatar_url?:"")


}