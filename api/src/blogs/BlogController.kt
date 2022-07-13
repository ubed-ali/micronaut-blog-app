package blogs

import utility.Response

interface BlogController {
    fun getAllBlog(): Response<Any>
    fun getUserBlogs(user_id: Long, blogId: Long, title: String): Response<Any>
    fun getBlogById(blogId: Long): Response<Any>
    fun createBlog(user_id: Long, title: String, content: String, published: Boolean):Response<Any>
    fun updateBlog(user_id: Long, blogId: Long, title: String, content: String): Response<Any>
    fun deleteBlog(user_id: Long, blogId: Long):Response<Any>
}


