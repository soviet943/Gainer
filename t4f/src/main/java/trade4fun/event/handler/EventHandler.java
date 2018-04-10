package trade4fun.event.handler;


import java.util.List;

import trade4fun.event.EventModel;
import trade4fun.event.EventType;

/**
 * 事件处理接口，包含了事件处理以及关心的所有事件
 */
public interface EventHandler {

    /**
     * 处理事件
     */
    void doHandler(EventModel eventModel);

    /**
     * 获取支持的事件类型
     */
    List<EventType> getSupportEvent();

}
