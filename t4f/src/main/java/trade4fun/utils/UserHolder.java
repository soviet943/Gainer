package trade4fun.utils;

import org.springframework.stereotype.Component;

import trade4fun.pojo.User;

/**
 * 每次请求时存放请求者的信息
 */
@Component
public class UserHolder {

    private static ThreadLocal<User> users = new ThreadLocal<User>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void remove() {
        users.remove();
    }

}
