package trade4fun.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import trade4fun.pojo.CSGOItem;
import trade4fun.pojo.CSGOItemType;


@Repository
public interface CSGOTypeDao {
	List<CSGOItemType> getAllTypes();
}
