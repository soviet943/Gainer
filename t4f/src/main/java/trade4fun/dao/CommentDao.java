package trade4fun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import trade4fun.pojo.bbs.Comment;


@Repository
public interface CommentDao {

	Comment selectByCid(@Param(value = "cid") Integer cid);
	List<Comment> selectByAid(
			@Param(value = "aid") Integer aid,
			@Param(value = "startPos") Integer startPos,
			@Param(value = "size") Integer size);
	List<Comment> selectByUid(@Param(value = "uid") Integer uid);
	
	int insert(Comment comment);
	
	int deleteByAId(@Param(value = "aid") Integer aid);
	int deleteByCId(@Param(value = "cid") Integer cid);
	int deleteByUId(@Param(value = "uid") Integer uid);
	
	int update_voted(@Param(value = "votes") Map<Integer, Integer> votes);
	int reply_num(Integer cid);
	
	
}
