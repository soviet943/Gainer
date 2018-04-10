package trade4fun.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import trade4fun.pojo.Token;


public interface TokenDao {
	
	int insert(Token record);

    Token selectByToken(String token); 

    Token selectByTokenAndStat(@Param(value = "token") String token, @Param(value = "stat") Integer stat);

    List<Token> selectByUid(Integer user_id);

    int updateStatByDate(@Param(value = "date") Date date, @Param(value = "stat") Integer stat);

    int updateStatByToken(@Param(value = "stat") Integer stat, @Param(value = "token") String token);
}
