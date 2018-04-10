package trade4fun.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import trade4fun.utils.RedisUtil;

/**
 * 事件生产者,负责把需要处理的事件以（事件类型  -  事件模型）映射到redis中，以供事件消费者处理
 */
@Component
public class EventProducer {

    @Autowired
    private RedisUtil redisUtil;

    public long product(EventModel model) {//将事件模型解析成字符串映射到redis中
        return redisUtil.rpushObject(EventModel.EVENT_KEY, EventModel.class, model);
    }

}
