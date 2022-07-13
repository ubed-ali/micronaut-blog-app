package blogs

import blogs.core.services.BlogService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class BlogControllerImpl(
    private val blogService: BlogService
) : BlogController {

      override fun getAllBlog(): Response<Any> {
        val response = blogService.getAllBlog()
        return Response(ResponseType.SUCCESS, body = response)
      }

      override fun getUserBlogs(user_id: Long, blogId: Long, title: String): Response<Any> {
        val response = blogService.getUserBlogs(user_id, blogId, title)
        if(response.isEmpty()) {
          return Response(ResponseType.SUCCESS, body = response)
        }
        return Response(ResponseType.NOT_FOUND, body = "{No blogs found!}")
      }

      override fun getBlogById(blogId: Long): Response<Any> {
        val response = blogService.getBlogById(blogId)
        if(response.isEmpty()) {
          return Response(ResponseType.NOT_FOUND, body = "{Error: Blog id is incorrect!}")
        }
        return Response(ResponseType.SUCCESS, body = response)
      }


      override fun createBlog(user_id: Long, title: String, content: String, published: Boolean): Response<Any> {
        val response = blogService.createBlog(user_id, title, content, published)
        if (response.isNotEmpty()) {
          return Response(ResponseType.SUCCESS, body = "Blog created successfully! \n $response")
        }
        return Response(ResponseType.NOT_FOUND, body = "{\"Error\":\"User Not Found\"}")
      }

      override fun updateBlog(user_id: Long, blogId: Long, title: String, content: String): Response<Any> {
        val response = blogService.updateBlog(user_id, blogId, title, content)
        if (response.isEmpty()) {
          return Response(ResponseType.NOT_FOUND, body = "{\"Error\":\"No Data Found\"}")
        }
        return Response(ResponseType.SUCCESS, body = "{\"Success\":\"Blog Updated Successfully\"}")
      }

      override fun deleteBlog(user_id: Long, blogId: Long): Response<Any> {
        val response = blogService.deleteBlog(user_id, blogId)
        if (response.isEmpty()) {
          return Response(ResponseType.SUCCESS, body = "{\"Success\":\"Blog Deleted Successfully\"}")
        }
        return Response(ResponseType.NOT_FOUND, body = "{\"Error\":\"No Data Found\"}")
      }
  }

