package trade4fun.event.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import trade4fun.event.EventModel;
import trade4fun.event.EventType;
import trade4fun.utils.MailUtil;

/**
 * 发送注册用户时邮箱验证码事件
 */
@Service
public class SendValidateMailHandler implements EventHandler {

    public void doHandler(EventModel model) {
        MailUtil.sendValidateMail(model.getExts("mail"), model.getExts("uid") ,model.getExts("code"));
    }

    public List<EventType> getSupportEvent() {
        return Arrays.asList(EventType.SEND_VALIDATE_EMAIL);
    }
}
