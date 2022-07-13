package user

import blogs.core.services.UserService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class UserControllerImpl(
  private val userService: UserService
) : UserController {
      override fun getAllUser(): Response<Any> {
        val response = userService.getAllUser()
        return Response(ResponseType.SUCCESS, body = response)
      }

      override fun getUserById(user_id: Long): Response<Any> {
        val response = userService.getUserById(user_id)
        return  Response(ResponseType.SUCCESS, body = response)
      }

      override fun createUser(firstName: String, lastName: String): Response<Any> {
        val response = userService.createUser(firstName, lastName)
        return Response(ResponseType.SUCCESS, body = response)
      }

      override fun updateUser(user_id: Long, firstName: String, lastName: String): Response<Any> {
        val response = userService.updateUser(user_id, firstName, lastName)
        if(response.isEmpty()) {
          return Response(ResponseType.NOT_FOUND, body = "{Error: User not found!}")
        }
        return Response(ResponseType.SUCCESS, body = response)
      }

      override fun deleteUser(user_id: Long): Response<Any> {
        val response = userService.deleteUser(user_id)
        if(response.isEmpty()) {
          return Response(ResponseType.SUCCESS, body = "{Success: User deleted successfully}")
        }
        return Response(ResponseType.NOT_FOUND, body = "{Error: User not found!}")
      }
  }
