package blogsTest

import blogs.BlogControllerImpl
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.annotation.MicronautTest
import utility.ResponseType

@MicronautTest
class BlogApiTest(
  private val blogControllerImpl: BlogControllerImpl
) : BehaviorSpec({
    given("Getting all Blogs") {
      `when`("Calling '/api/v1/blog' Api using @Get method" ) {
        val getAllBlog = blogControllerImpl.getAllBlog()
        then("Should return SUCCESS") { getAllBlog.status shouldBe ResponseType.SUCCESS
        }
      }
    }

  given("Getting User's All Blogs") {
    `when`("Calling '/api/v1/user/{user_id}/blog' Api using @Get request" ) {
      val getUserBlogs = blogControllerImpl.getUserBlogs(1, 2, "test blog")
      then("Should Return 'SUCCESS'") { getUserBlogs.status shouldBe ResponseType.SUCCESS
      }
    }
  }

  given("Creating Blog of Non-Existing User") {
    `when`("Calling '/api/v1/user/{user_id}/blog' Api using @POST method" ) {
      val createBlog = blogControllerImpl.createBlog(5, "APIs","How REST APIs works", true)
      then("Should return 'NOT_FOUND' as user does not exist") {
        createBlog.status shouldBe ResponseType.NOT_FOUND
      }
    }
  }

  given("Creating Blog") {
    `when`("Calling '/api/v1/user/{user_id}/blog' Api using @POST method" ) {
      val createBlog = blogControllerImpl.createBlog(2,"DBMS",
        "In this blog we'll learn about database management system.", true)
      then("Should return SUCCESS as user exists") { createBlog.status shouldBe ResponseType.SUCCESS
      }
    }
  }
  given("Updating Blog") {
    `when`("Calling '/api/v1/user/{user_id}/blog/{blog_id}' Api using @Put request" ) {
      val updateBlog = blogControllerImpl.updateBlog(
        2, 3,
        "Django Setup for Enterprise Applications",
        "Django is a very popular Python Web framework that encourages rapid development and clean," +
                " pragmatic design. Its free and Open Source. Some of the busiest sites on the " +
                "Web leverage Django’s ability to quickly and flexibly scale.")
      then("Should return SUCCESS") {
        updateBlog.status shouldBe ResponseType.SUCCESS
      }
    }
  }

  given("Deleting Non-exsitent Blog") {
    `when`("Calling '/api/v1/user/{user_id}/blog/{blog_id}' Api using @Delete request" ){
      val deletingBlog = blogControllerImpl.deleteBlog(1,3)
      then("Should return 'NOT_FOUND' as blog does not exist!") {
        deletingBlog.status shouldBe ResponseType.NOT_FOUND
      }
    }
  }

  given("Deleting Blog") {
    `when`("Calling '/api/v1/user/{user_id}/blog/{blog_id}' Api using @Delete request" ){
      val deletingBlog = blogControllerImpl.deleteBlog(1,4)
      then("Should return 'SUCCESS'") {
        deletingBlog.status shouldBe ResponseType.SUCCESS
      }
    }
  }
})
