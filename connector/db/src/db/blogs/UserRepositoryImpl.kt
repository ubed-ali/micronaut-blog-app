package db.blogs


import javax.inject.Singleton
import blogs.core.entity.User
import blogs.core.services.UserRepository
import javax.inject.Inject
import javax.sql.DataSource

@Singleton
class UserRepositoryImpl(
  @Inject private val dataSource: DataSource
) : UserRepository {

  override fun getAllUser(): List<User> {
    val allUserQuery = dataSource.connection.prepareStatement("SELECT * FROM blogs_users")
    val result = allUserQuery.executeQuery()
    val user = mutableListOf<User>()
    while(result.next()){
      val user_id = result.getInt("id").toLong()
      val firstName = result.getString("firstName") as String
      val lastName = result.getString("lastName") as String


      user.add(User(user_id, firstName, lastName))

    }
    return user
  }

  override fun getUserById(user_id: Long): List<User> {
    val query = dataSource.connection.prepareStatement("SELECT * FROM blogs_users where id = ${user_id}")
    val result = query.executeQuery()
    val users = mutableListOf<User>()

    while (result.next()) {
      val user_id = result.getInt("id").toLong()
      val firstName = result.getString("firstName")
      val lastName = result.getString("lastName")
      users.add(User(user_id, firstName, lastName))
    }
    return users
  }

  override fun createUser(firstName: String, lastName: String):List<User>{
    val insertQuery = dataSource.connection.prepareStatement(
      "INSERT INTO blogs_users(firstName, lastName) VALUES (?, ?)")

    insertQuery.setString(1, firstName)
    insertQuery.setString(2, lastName)

    insertQuery.executeUpdate()
    //Fetching Last Insert Data
    val fetchInsertedData= dataSource.connection.prepareStatement("SELECT * FROM blogs_users ORDER BY id DESC LIMIT 1")
    val result = fetchInsertedData.executeQuery()
    val userData = mutableListOf<User>()

    while (result.next()) {
      val user_id = result.getInt("id").toLong()
      val firstName = result.getString("firstName") as String
      val lastName = result.getString("lastName") as String
      userData.add(User(user_id, firstName, lastName))
    }
    return userData
  }


  override fun updateUser(user_id: Long, firstName: String, lastName: String): List<User> {
    val fetchUser = dataSource.connection.prepareStatement("SELECT * FROM blogs_users where id = ${user_id}")
    val result = fetchUser.executeQuery()
    val user = mutableListOf<User>()

    if(result.next()) {
      val updateQuery = dataSource.connection.prepareStatement("UPDATE blogs_users SET firstName=?, lastName=? where id = ${user_id}")
      updateQuery.setString(1,firstName)
      updateQuery.setString(2, lastName)

      val res = updateQuery.executeUpdate()
      return getUserById(user_id)
    }
    return user
  }


  //Change return values
  override fun deleteUser(user_id: Long): List<User> {
    val fetchUserQuery = dataSource.connection.prepareStatement("SELECT * FROM blogs_users where id = ${user_id}")
    val result = fetchUserQuery.executeQuery()
    if(result.next()) {
      val deleteUserQuery = dataSource.connection.prepareStatement("DELETE FROM blogs_users where id = ${user_id}")
      deleteUserQuery.executeUpdate()
      println("User deleted successfully")
      return getAllUser()
    } else {
      println("User not found")
      return getAllUser()
    }
  }




}
