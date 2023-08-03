package uz.artikov.mvvmrealartikov.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import uz.artikov.mvvmrealartikov.database.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun addUser(userEntity: UserEntity): Long

    /*    (onConflict = OnConflictStrategy.REPLACE)
        Bu ma'lumotlarni o'chirib qo'shgandan ,yo yangisini qo'shgan idni tartiblaydi*/

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUsers(list: List<UserEntity>)

    @Query("select * from userentity")
    fun getUsers(): List<UserEntity>

    @Query("select count(*) from userentity")
    fun getUserCount():Int


}