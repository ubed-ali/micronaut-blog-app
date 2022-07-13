package db.blogs

import blogs.core.entity.Blog
import blogs.core.entity.User
import blogs.core.services.BlogRepository
import blogs.core.services.UserRepository
import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class BlogRepositoryImpl(
    @Inject private val dataSource: DataSource
) : BlogRepository {

  override fun getAllBlog(): List<Blog> {
    val query = dataSource.connection.prepareStatement("SELECT * FROM blogs")
    val result = query.executeQuery()
    val blogs = mutableListOf<Blog>()

    while (result.next()) {
      val blogId = result.getInt("id").toLong()
      val user_id = result.getInt("user_id").toLong()
      val title = result.getString("title")
      val content = result.getString("content")
      val published = result.getBoolean("published")
      val createdAt = result.getTimestamp("created_at")
      val updatedAt = result.getTimestamp("updated_at")
      blogs.add(Blog(blogId, user_id, title, content, published, createdAt, updatedAt))
    }
    return blogs
  }

  override fun getUserBlogs(user_id: Long, blogId: Long, title: String): List<Blog> {
    val fetchUserBlogs=dataSource.connection.prepareStatement("SELECT title FROM blogs WHERE user_id = ${user_id}")
    val result=fetchUserBlogs.executeQuery()
    val blogs= mutableListOf<Blog>()
    while(result.next()){
      val user_id = result.getInt("user_id").toLong()
      val blogId = result.getInt("id").toLong()
      val title = result.getString("title")
      blogs.add(Blog(user_id, blogId, title, "", true, Timestamp(System.currentTimeMillis()), Timestamp(System.currentTimeMillis())))
    }
    return blogs
  }

  override fun getBlogById(blogId: Long): List<Blog> {
    val query = dataSource.connection.prepareStatement("SELECT * FROM blogs where id=${blogId}")
    val result = query.executeQuery()
    val blogs = mutableListOf<Blog>()

    while (result.next()) {
      val blogId = result.getInt("id").toLong()
      val user_id = result.getInt("user_id").toLong()
      val title = result.getString("title") as String
      val content = result.getString("content") as String
      val published = result.getBoolean("published")
      val createdAt = result.getTimestamp("created_at")
      val updatedAt = result.getTimestamp("updated_at")
      blogs.add(Blog(blogId, user_id, title, content, published, createdAt, updatedAt))
    }
    return blogs
  }



  override fun createBlog(user_id: Long, title: String, content: String, published: Boolean): List<Blog> {
    val fetchUserQuery = dataSource.connection.prepareStatement("SELECT * FROM blogs_users where id=${user_id}")
    val result = fetchUserQuery.executeQuery()
    val blogList = mutableListOf<Blog>()
    if(result.next()){
      val insertQuery = dataSource.connection.prepareStatement("INSERT INTO blogs(user_id, title, content, published, created_at, published_at, updated_at) VALUES(?,?,?,?,?,?,?)")
      insertQuery.setLong(1,user_id)
      insertQuery.setString(2,title)
      insertQuery.setString(3, content)
      insertQuery.setBoolean(4, published)
      insertQuery.setTimestamp(5,Timestamp(System.currentTimeMillis()))
      insertQuery.setTimestamp(6,Timestamp(System.currentTimeMillis()))
      insertQuery.setTimestamp(7,Timestamp(System.currentTimeMillis()))
      insertQuery.executeUpdate()

      val getLastBlogQuery = dataSource.connection.prepareStatement("SELECT * FROM blogs ORDER BY id DESC LIMIT 1")
      val lastBlog = getLastBlogQuery.executeQuery()
      while (lastBlog.next()) {
        val blogId = lastBlog.getInt("id").toLong()
        val published = lastBlog.getBoolean("published")
        blogList.add(Blog(blogId, user_id, title, content, published, Timestamp(System.currentTimeMillis()), Timestamp(System.currentTimeMillis())))
      }
    }
    return blogList
  }

  override fun updateBlog(user_id: Long, blogId: Long, title: String, content: String): List<Blog>{
    // fetch data from db
    val fetchBlog=dataSource.connection.prepareStatement("SELECT * FROM blogs where user_id = $user_id and id = $blogId")
    val result=fetchBlog.executeQuery()
    val blogList= mutableListOf<Blog>()
    if(result.next()){
      val published = false
      val updateQuery=dataSource.connection.prepareStatement("UPDATE blogs SET title=?,content=?, published=?, updated_at=? where id=${blogId} and user_id=${user_id}")
      updateQuery.setString(1,title)
      updateQuery.setString(2,content)
      updateQuery.setBoolean(3,published)
      updateQuery.setTimestamp(4,Timestamp(System.currentTimeMillis()))

      val res = updateQuery.executeUpdate()
      return getBlogById(blogId)
    }
    return blogList
  }

  override fun deleteBlog(user_id: Long, blogId: Long):List<Blog> {
    val fetchBlogQuery = dataSource.connection.prepareStatement("SELECT * FROM blogs where id = $blogId and user_id = $user_id")
    val result=fetchBlogQuery.executeQuery()
    if(result.next()){
      val updateQuery = dataSource.connection.prepareStatement("DELETE FROM blogs where id=? and user_id=?")
      updateQuery.setLong(1,blogId)
      updateQuery.setLong(2,user_id)
      val res = updateQuery.executeUpdate()
      return getBlogById(blogId)
    } else{
      val blog = mutableListOf<Blog>()
      blog.add(Blog(0,0,"", "", false, Timestamp(System.currentTimeMillis()),Timestamp(System.currentTimeMillis())))
      return blog
    }
  }
}
