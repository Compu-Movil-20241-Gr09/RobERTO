package co.edu.udea.compumovil.gr09_20241.roberto.database.user

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Upsert //mix between @Insert and @Update
    suspend fun upsertUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)
    @Query("SELECT * FROM user")
    fun getAllUsers(): Flow<List<User>>
    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): User?
}