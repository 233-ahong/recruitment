import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;

/**
 * @author 26471
 * @date 2023/4/7 23:02
 * @description
 */
public class T1 {

    public static void main(String[] args) {
        String s = "{username=admin, password=admin123}";
        JSONObject jsonObject = JSON.parseObject(s);
    }

}
