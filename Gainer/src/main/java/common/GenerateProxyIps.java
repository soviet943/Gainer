package common;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GenerateProxyIps {

    public static String http = "http://www.xicidaili.com/wt/";    //国内http代理
    public static String https = "http://www.xicidaili.com/wn/";   //国内https代理
    public static String common = "http://www.xicidaili.com/nt/";  //国内普通代理
    public static String anonymous = "http://www.xicidaili.com/nn/";  //国内高匿代理


    public List<String> get(Integer pages, String type) {
        List<String> result = new ArrayList<String>();
        UserAgents userAgents = new UserAgents();
        for(int page=1; page<=pages; page++) {
            Connection conn = Jsoup.connect(type + page);
            //获取随机User-Agent
            conn.header("User-Agent", userAgents.getRandomUserAgent());
            Document doc = null;
            try {
                doc = conn.get();
            } catch (Exception e) {
                e.printStackTrace();
            }
            //获取所有行
            Elements ip_list = doc.getElementById("ip_list").tagName("tbody").getElementsByTag("tr");
            for(int i=1; i<ip_list.size(); i++) {
                //速度和连接时间
                Element tr = ip_list.get(i);
                String velocity_str = tr.child(6).child(0).attr("title");
                String connectTime_str = tr.child(7).child(0).attr("title");
                Double velocity = Double.parseDouble(velocity_str.substring(0, velocity_str.length()-2));
                Double connectTime = Double.parseDouble(connectTime_str.substring(0, connectTime_str.length()-2));
                //如果速度和连接时间都大于0.5秒则跳过
                if(velocity > 0.5 && connectTime > 0.5) continue;
                String ip = tr.child(1).text();
                result.add('"'+ip+'"');
            }
        }
        return result;
    }

    @Test
    public void proxyIpsTest() {
        List<String> list = get(5, GenerateProxyIps.http);
        System.out.println(list.toString().substring(1,list.toString().length()-2));
        System.out.println("共获得IP数"+ list.size());
    }

}
