package blogs.core.services

import blogs.core.entity.Blog

interface BlogRepository {
    fun getAllBlog(): List<Blog>
    fun getUserBlogs(user_id: Long, blogId: Long, title: String): List<Blog>
    fun getBlogById(blogId: Long): List<Blog>
    fun createBlog(user_id:Long,title:String,content:String, published: Boolean): List<Blog>
    fun updateBlog(user_id: Long, blogId: Long, title: String, content: String): List<Blog>
    fun deleteBlog(user_id: Long,blog: Long): List<Blog>

}
