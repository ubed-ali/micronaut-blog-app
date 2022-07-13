package blogs.core.services

import blogs.core.entity.User
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class UserService @Inject constructor(
  private val userRepository: UserRepository
) {
  fun getAllUser(): List<User> = userRepository.getAllUser()
  fun getUserById(user_id: Long): List<User> = userRepository.getUserById(user_id)
  fun createUser(firstName: String, lastName: String): List<User> = userRepository.createUser(firstName, lastName)
  fun updateUser(user_id: Long, firstName: String, lastName: String): List<User> = userRepository.updateUser(user_id, firstName, lastName)
  fun deleteUser(user_id: Long): List<User> = userRepository.deleteUser(user_id)

}
