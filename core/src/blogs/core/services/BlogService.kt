package blogs.core.services

import blogs.core.entity.Blog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogService @Inject constructor(
    private val blogRepository: BlogRepository
) {
    fun getAllBlog(): List<Blog> = blogRepository.getAllBlog()
    fun getBlogById(blogId: Long): List<Blog> = blogRepository.getBlogById(blogId)
    fun getUserBlogs(blogId: Long, user_id: Long, title: String): List<Blog> = blogRepository.getUserBlogs(blogId, user_id, title)
    fun createBlog(user_id: Long,title: String,content: String, published: Boolean): List<Blog> = blogRepository.createBlog(user_id, title, content, published)
    fun updateBlog(user_id: Long, blogId: Long, title: String, content: String): List<Blog> = blogRepository.updateBlog(user_id, blogId, title, content)
    fun deleteBlog(user_id: Long,blogId: Long):List<Blog> = blogRepository.deleteBlog(user_id,blogId)
}


