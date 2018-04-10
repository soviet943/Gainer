package trade4fun.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteDao {

	Integer article_voted(@Param(value="user_id") Integer user_id,@Param(value="article_id") Integer article_id);
	Integer insert_article_vote(@Param(value="user_id") Integer user_id, @Param(value="article_id") Integer article_id);
	Integer delete_article_vote(@Param(value="user_id") Integer user_id, @Param(value="article_id") Integer article_id);
	
	Integer comment_voted(@Param(value="user_id") Integer user_id,@Param(value="comment_id") Integer comment_id);
	Integer insert_comment_vote(@Param(value="user_id") Integer user_id, @Param(value="comment_id") Integer comment_id);
	Integer delete_comment_vote(@Param(value="user_id") Integer user_id, @Param(value="comment_id") Integer comment_id);
	
	Integer reply_voted(@Param(value="user_id") Integer user_id,@Param(value="reply_id") Integer reply_id);
	Integer insert_reply_vote(@Param(value="user_id") Integer user_id, @Param(value="reply_id") Integer reply_id);
	Integer delete_reply_vote(@Param(value="user_id") Integer user_id, @Param(value="reply_id") Integer reply_id);
	
}
