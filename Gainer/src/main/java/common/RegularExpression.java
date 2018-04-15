package common;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpression {

    public String compile(String str, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(str);
        if(matcher.matches()) {
            return matcher.group(1);
        } else {
            return "None";
        }

    }

    @Test
    public void junitTest() {
        String str = compile("fuck2018","(.*?)\\d+");
        System.out.println(str);
    }

}
