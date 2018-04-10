package trade4fun.service;

import trade4fun.utils.Result;

public interface DiscussService {

	public String vote(String type, Integer id);
	
	public Result add_comment(Integer aid, String content);
	
	public Result add_reply(Integer aid, Integer cid, String content);
	
}
