package trade4fun.utils;

import redis.clients.jedis.Jedis;

public class test_1 {
    
	public static void main(String[] args) {
		RedisUtil redisUtil = new RedisUtil();
		for(int i=0; i<20; i++) {
			System.out.println(redisUtil.get("vc30", 3));
		}
	}
}
