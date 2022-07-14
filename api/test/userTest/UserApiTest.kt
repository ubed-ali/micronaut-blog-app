package userTest

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.annotation.MicronautTest
import user.UserControllerImpl
import utility.ResponseType

  @MicronautTest
  class UserApiTest (
    private val userControllerImpl: UserControllerImpl
  ) : BehaviorSpec({
    given("Getting all Users") {
      `when`("Calling '/api/v1/user' Api using '@Get' method" ) {
        val getAllUser = userControllerImpl.getAllUser()
        then("Should return SUCCESS") {
          getAllUser.status shouldBe ResponseType.SUCCESS
        }
      }
    }

    given("Getting User by id which does not exist") {
      `when`("Calling '/api/v1/user/{user_id}' Api using '@Get' request" ) {
        val getUserById = userControllerImpl.getUserById(23)
        then("Should Return 'NOT_FOUND'") {
          getUserById.status shouldBe ResponseType.NOT_FOUND
        }
      }
    }

    given("Getting User by id") {
      `when`("Calling '/api/v1/user/{user_id}' Api using '@Get' method" ) {
        val getUserById = userControllerImpl.getUserById(2)
        then("Should Return 'SUCCESS'") {
          getUserById.status shouldBe ResponseType.SUCCESS
        }
      }
    }

    given("Creating User") {
      `when`("Calling '/api/v1/user' Api using '@Post' method" ) {
        val creatingUser = userControllerImpl.createUser(
          firstName ="Mamata", lastName = "Joshi")
        then("Should return 'SUCCESS'") {
          creatingUser.status shouldBe ResponseType.SUCCESS
        }
      }
    }
    given("Updating User") {
      `when`("Calling '/api/v1/user/{user_id}' Api using @Put method" ) {
        val updatingUser = userControllerImpl.updateUser(
          3, "Mamata", "J.")
        then("Should return SUCCESS") {
          updatingUser.status shouldBe ResponseType.SUCCESS
        }
      }
    }
    given("Updating User which does not exist") {
      `when`("Calling '/api/v1/user/{user_id}' Api using @Put method" ) {
        val updatingUser = userControllerImpl.updateUser(4, "Ashish", "Mohite")
        then("Should return NOT_FOUND") {
          updatingUser.status shouldBe ResponseType.NOT_FOUND
        }
      }
    }

    given("Deleting Non-existent Blog") {
      `when`("Calling '/api/v1/user/{user_id}' Api using @Delete request" ){
        val deletingUser = userControllerImpl.deleteUser(10)
        then("Should return 'NOT_FOUND' as user does not exist!") {
          deletingUser.status shouldBe ResponseType.NOT_FOUND
        }
      }
    }

    given("Deleting User") {
      `when`("Calling '/api/v1/user/{user_id}' Api using @Delete request" ){
        val deletingUser = userControllerImpl.deleteUser(3)
        then("Should return 'SUCCESS'") {
          deletingUser.status shouldBe ResponseType.SUCCESS
        }
      }
    }
  })
