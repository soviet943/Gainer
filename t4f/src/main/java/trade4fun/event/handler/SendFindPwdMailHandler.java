package trade4fun.event.handler;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import trade4fun.event.EventModel;
import trade4fun.event.EventType;
import trade4fun.utils.MailUtil;

/**
 * 发送找回密码邮件事件
 */
@Service
public class SendFindPwdMailHandler implements EventHandler {

    public void doHandler(EventModel model) {
        MailUtil.sendFetchPwdMail(model.getExts("mail"), model.getExts("code"));
    }

    public List<EventType> getSupportEvent() {
        return Arrays.asList(EventType.SEND_FIND_PWD_EMAIL);
    }
}
