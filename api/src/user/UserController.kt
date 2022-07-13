package user

import utility.Response

interface UserController {
  fun getAllUser(): Response<Any>
  fun getUserById(user_id: Long): Response<Any>
  fun createUser(firstName: String, lastName: String): Response<Any>
  fun updateUser(user_id: Long, firstName: String, lastName: String): Response<Any>
  fun deleteUser(user_id: Long): Response<Any>
}
