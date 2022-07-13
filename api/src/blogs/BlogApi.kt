package blogs

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller("/api/v1/blog")
class GetAllBlogApi @Inject constructor(
    private val blogController: BlogController
) {
  @Get("/")
  fun getAllBlog(): HttpResponse<Any> {
    val allBlog = blogController.getAllBlog()
    return allBlog.getHttpResponse()
  }

}

@Controller("/api/v1/user/{user_id}/blog")
class GetUserBlogsApi @Inject constructor(
  private val blogController: BlogController
) {
  @Get("/")
  fun getUserBlogs(user_id: Long, blogId: Long, title: String): HttpResponse<Any> {
    val blog = blogController.getUserBlogs(user_id, blogId, title)
    return blog.getHttpResponse()
  }
}


@Controller("/api/v1/blog/{blog_id}")
class GetBlogByIdApi @Inject constructor(
  private val blogController: BlogController
) {
  @Get("/")
  fun getBlogById(blogId: Long): HttpResponse<Any> {
    val blog = blogController.getBlogById(blogId)
    return blog.getHttpResponse()
  }
}


@Controller("/api/v1/user/{user_id}/blog")
class CreateBlogApi @Inject constructor(
  private val blogController: BlogController
){
  @Post("/")
  fun createBlog(user_id:Long,title:String,content:String, published:Boolean): HttpResponse<Any> {
    val blog = blogController.createBlog(user_id,title,content, published)
    return blog.getHttpResponse()
  }

}


//UPDATE BLOG
@Controller("/api/v1/user/{user_id}/blog/{blog_id}")
class UpdateBlogApi @Inject constructor(
  private val blogController: BlogController
){
  @Put("/")
  fun updateBlog(user_id: Long, blogId: Long, title: String, content: String): HttpResponse<Any> {
    val blog = blogController.updateBlog(user_id, blogId, title, content)
    return blog.getHttpResponse()
  }
}

//DELETE BLOG
@Controller("/api/v1/user/{user_id}/blog/{blogId}")
class DeleteBlogApi @Inject constructor(
  private val blogController: BlogController
){
  @Delete("/")
  fun deleteBlog(user_id: Long, blogId: Long): HttpResponse<Any> {
    val blog = blogController.deleteBlog(user_id, blogId)
    return blog.getHttpResponse()
  }
}



