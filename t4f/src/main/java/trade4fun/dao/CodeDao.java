package trade4fun.dao;

import trade4fun.pojo.Code;

public interface CodeDao {
	
	int insert(Code record);

    Code selectById(Integer id);

    int update(Code record);
}
