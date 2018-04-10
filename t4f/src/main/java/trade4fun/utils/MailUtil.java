package trade4fun.utils;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 * Created by RojerAlone on 2017/6/2.
 * 邮件工具
 */
public class MailUtil {

    private final static String USERNAME = "validate@trade4fun.top";
    private final static String PASSWORD = "t4f_mail";           // 需要打开SMTP并使用此授权码登录 POP3/SMTP服务rpvbnuunxffzdbaa IMAP/SMTP服务ixqxoynqutzjcjbf
    private final static String ADDRESS = "smtp.mxhichina.com";        
    private final static int PORT = 25; 
    

    /**
     * 发送邮件给指定人，需要主题和内容
     * @param user
     * @param title
     * @param content
     */
    public static void sendMail(String user, String title, String content) {
        SimpleEmail email = new SimpleEmail();
        email.setCharset("UTF8");
        email.setHostName(ADDRESS);
        email.setSmtpPort(PORT);
        email.setAuthentication(USERNAME, PASSWORD);
        try {
            email.setFrom(USERNAME);
            email.addTo(user);
            email.setSubject(title);
            email.setMsg(content);
            email.send();
        } catch (EmailException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送验证邮件给用户
     * @param user
     * @param code
     */
    public static void sendValidateMail(String user, String uid, String code) {
        String title = "trade4fun账户激活邮件";
        String content = "您好！\n\n感谢您注册T4F!\n\n帐户需要激活才能使用，赶紧激活成为T4F正式的一员吧:) \n\n点击下面的链接立即激活帐户(或将网址复制到浏览器中打开)：\n" +
        		"http://127.0.0.1:8080/t4f/user/validate/"+ uid +"/" + code + "\n此链接有效期为 10分钟 (〃'▽'〃)。";

        sendMail(user, title, content);
    }

    /**
     * 忘记密码时发送验证邮件给用户
     * @param user
     * @param code
     */
    public static void sendFetchPwdMail(String user, String code) {

        String title = "T4F找回密码";
        String content = "这封邮件是在验证您的T4F邮箱，用来找回密码，如果不是您本人的操作，请忽略此邮件。\n您的验证码为 \n" + code + "\n请注意保存，此验证码有效期为 10分钟 。";

        sendMail(user, title, content);
    }
}
