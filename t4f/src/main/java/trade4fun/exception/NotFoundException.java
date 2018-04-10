package trade4fun.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import trade4fun.utils.MsgCenter;

/**
 * 自定义异常
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super(MsgCenter.NOT_FOUND);
    }
}
