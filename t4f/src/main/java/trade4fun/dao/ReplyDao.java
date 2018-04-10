package trade4fun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import trade4fun.pojo.bbs.Reply;


@Repository
public interface ReplyDao {
	
	Reply selectByRid(@Param(value = "rid") Integer rid);
	List<Reply> selectByAid(@Param(value = "aid") Integer aid);
	List<Reply> selectByCid(
			@Param(value = "cid") Integer cid,
			@Param(value = "startPos") Integer startPos,
			@Param(value = "size") Integer size);
	List<Reply> selectByUid(@Param(value = "uid") Integer uid);
	
	int insert(Reply reply);
	
	int deleteByAId(@Param(value = "aid") Integer aid);
	int deleteByCId(@Param(value = "cid") Integer cid);
	int deleteByRId(@Param(value = "rid") Integer rid);
	int deleteByUId(@Param(value = "uid") Integer uid);
	
	int update_voted(@Param(value = "votes") Map<Integer, Integer> votes);
}
