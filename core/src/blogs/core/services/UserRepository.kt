package blogs.core.services

import blogs.core.entity.User

interface UserRepository {
  fun getAllUser(): List<User>
  fun getUserById(user_id: Long): List<User>
  fun createUser(firstName: String, lastName: String):List<User>
  fun updateUser(user_id: Long, firstName: String, lastName: String): List<User>
  fun deleteUser(user_id: Long): List<User>

}


