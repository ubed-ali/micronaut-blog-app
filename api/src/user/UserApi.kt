package user

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller("/api/v1/user")
class GetAllUserApi @Inject constructor(
  private val userController: UserController
){
  @Get("/")
  fun getAllUser(): HttpResponse<Any> {
    val allUser = userController.getAllUser()
    return allUser.getHttpResponse()
  }
}

@Controller("/api/v1/user/{user_id}")
class GetUserApi @Inject constructor(
  private val userController: UserController
) {
  @Get("/")
  fun getUserById(user_id: Long): HttpResponse<Any> {
    val userData = userController.getUserById(user_id)
    return userData.getHttpResponse()
  }
}

@Controller("/api/v1/user")
class CreateUserApi @Inject constructor(
  private val userController: UserController
){
  @Post("/")
  fun createUser(firstName:String, lastName: String): HttpResponse<Any> {
    val createUser = userController.createUser(firstName, lastName)
    return createUser.getHttpResponse()
  }
}

@Controller("/api/v1/user/{user_id}")
class UpdateUserApi @Inject constructor(
  private val userController: UserController
){
  @Put("/")
  fun updateUser(user_id: Long, firstName:String, lastName: String): HttpResponse<Any> {
    val updatedUser = userController.updateUser(user_id, firstName, lastName)
    return updatedUser.getHttpResponse()
  }
}

@Controller("/api/v1/user/{user_id}")
class DeleteUserApi @Inject constructor(
  private val userController: UserController
){
  @Delete("/")
  fun deleteUser(user_id: Long): HttpResponse<Any> {
    val deleteUser = userController.deleteUser(user_id)
    return deleteUser.getHttpResponse()
  }
}

