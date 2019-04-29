package entity;

import java.util.HashMap;

import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;

public class ocr {

	//设置APPID/AK/SK
    
    public static String ocr_plateLicense(byte[] file) {
    	String APP_ID = "15720099";
        String API_KEY = "hKHNhMOylAKsjUQ7VQXb74EN";
        String SECRET_KEY = "vnfiX3IG7ROhaDeOGMDonP5GYgvsM8Zi";
    	AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
        // 传入可选参数调用接口
        HashMap<String, String> options = new HashMap<String, String>();
        options.put("multi_detect", "false");
        options.put("detect_direction", "true");
        // 参数为二进制数组
        JSONObject res = client.plateLicense(file, options);
        System.out.println(res.toString(2));
        return res.toString();
    }
}
