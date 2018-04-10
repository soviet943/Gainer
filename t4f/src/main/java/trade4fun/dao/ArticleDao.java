package trade4fun.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import trade4fun.pojo.bbs.Article;

@Repository
public interface ArticleDao {
	
	Article selectByAid(@Param(value = "aid") Integer aid);
	List<Article> selectByUid(@Param(value = "uid") Integer uid);
	
	//public
	List<Article> select_filter(
			@Param(value = "up") Integer up,
			@Param(value = "stat") Integer stat,
			@Param(value = "game_type") Integer game_type,
			@Param(value = "article_type") Integer article_type,
			@Param(value = "uid") Integer uid,
			@Param(value = "title") String title,
			@Param(value = "byclicked") String byclicked,
			@Param(value = "byvoted")  String byvoted
			);
	
	int insert(Article article);
	
	int deleteByAId(Integer aid);
	int deleteByAIds(List<Integer> aids);
	
	int updateById(Integer aid);
	int update_statistics(Map<Integer,List<Integer>> aid_maps);
	int comment_num(Integer aid);
	
	
}
