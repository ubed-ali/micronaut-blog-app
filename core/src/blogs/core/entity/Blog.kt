package blogs.core.entity

import java.sql.Timestamp

data class Blog(
  val blogId: Long,
  val user_id: Long,
  val title: String,
  val content: String,
  val published: Boolean,
  val createdAt: Timestamp,
  val updatedAt: Timestamp
  )
