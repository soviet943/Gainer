package trade4fun.event;

import trade4fun.event.handler.EventHandler;
import trade4fun.utils.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;


/**
 * 事件消费者，不断从redis中获取需要处理的event
 */
@Service
public class EventConsumer implements InitializingBean, ApplicationContextAware, DisposableBean {

    private final Logger logger = Logger.getLogger(this.getClass());

    private ApplicationContext applicationContext;

    @Autowired
    private RedisUtil<EventModel> redisUtil;

    private ThreadPoolExecutor threadPool;

    private Map<EventType, List<EventHandler>> handlers = new HashMap<EventType, List<EventHandler>>();

    public void afterPropertiesSet() throws Exception {
    	//1、处理事件之前先归类，事件种类EventType对应多个事件处理EventHandler,好比一个工件需要多个工序
        Map<String, EventHandler> beans = applicationContext.getBeansOfType(EventHandler.class);// 从上下文中获取所有的handler
        if (beans != null) {
            for (Map.Entry<String, EventHandler> entry : beans.entrySet()) {//遍历所有EventHandler
                List<EventType> types = entry.getValue().getSupportEvent(); //返回List<EventType>,每个EventHandler可能支持多个EventType
                for (EventType type : types) {								//遍历所有EventType,将EventType和EventHandler映射到handlers中
                    if (!handlers.containsKey(type)) {
                        handlers.put(type, new ArrayList<EventHandler>());
                    }
                    handlers.get(type).add(entry.getValue());
                }
            }
        }
        // 2、开始处理事件
        // 设置线程池的大小为 CPU 的核数 + 2
        threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 2);
        new Thread(new Runnable() {
			public void run() {
				while (true) {
					//不断从redis中获取待处理的事件模型
		            EventModel event = redisUtil.lpopObject(EventModel.EVENT_KEY, EventModel.class);
		            if (event == null) {
		            	continue;
		            }
		            if (!handlers.containsKey(event.getEventType())) {//发现事件类型列表中不包括此事件类型则跳过
		                logger.error("error event type");
		                continue;
		            }
		            for (EventHandler handler : handlers.get(event.getEventType())) {//得到事件类型对应的所有事件处理
		                threadPool.execute(new EventConsumerThread(handler, event));
		            }
		        }
			}
        }).start();
    }

    class EventConsumerThread implements Runnable {

        private EventHandler handler;

        private EventModel event;

        public EventConsumerThread(EventHandler handler, EventModel event) {
            this.handler = handler;
            this.event = event;
        }

        public void run() {
            handler.doHandler(event);//处理事件
        }
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void destroy() throws Exception {
        if (threadPool != null) {
            while (threadPool.getQueue().size() != 0 || threadPool.getActiveCount() != 0) {
                // 等待所有任务执行完毕
            }
            threadPool.shutdownNow();
        }
    }
}
