package com.legends.process.engine;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class HttpTest {

    public static void main(String[] args) {
        String url="/api/rtsmboradod/api/wisdomSite/people/group/unit";

        String authorization="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuZWVkQmluZERldmljZSI6ZmFsc2UsInNpZ25pblR5cGUiOiIyMDEiLCJpc2NfdXNlcl9pZCI6Im51bGwiLCJ1c2VyX25hbWUiOiJQSldNNElZY2x2ZjJnMHF3RnNqeWdmck94a2xHNmR0SEtNTFpzU1BJSHY4TWlROHlHbldSQ1gybmJ5WGtGem1GSVNqZnpleGVkb0t5NjR1N0V3bWl2S2paSFZNWWYzbW9LOGRzZDNTV0h6UlRLYnNCaUQ2eEd6MFdKVmNzVG5JazRaSHUyQ2hHV0hmWUVDeWo2VXFHU2YvbXhDWm1jOGQxd2VGYmc0S2RlTjhpamhIa0wrRnNsTHIyZG1HYWN1Rmk2cC9sWHJySUsxT0dsN2MxTGV0eXYva0VFWVFnaG13WkFYTXllQ3hmcXdXZUp2RDBGb1FaamZtcnFHTjNOcXZhU25SMngzeXVsODROUS9YaU9RaGoxMXBudHg1bXJ2eTBFZnlYQlJ0bHQyUnF1dDA5MUtjV0tMY014bTFYaVRIQWdzVnBpUzhmcm5ZbUkzbGtWQnpndWc9PSIsIm1vYmlsZSI6bnVsbCwiY2xpZW50X2lkIjoiYmpuc2NfY2RwX3dlYiIsImNoYW5nZVBhc3N3b3JkIjpmYWxzZSwicHJvdmlkZXIiOiJqZXJyeWNoaXIiLCJ1c2VyX2lkIjoiNzY4NzY4OTY4NzY3ODY4Iiwic2NvcGUiOlsiYWxsIl0sIm5hbWUiOiLnlLPmlofkvJ8iLCJleHAiOjE2MDc3MzU0NDksImp0aSI6ImRkZjgwMTkxLWJkZDUtNDY3NC04MDg3LWYzYjkwZWM1NjBkZSJ9.TNx4gInxeDqHHl_6yn2rvSPZECn8gBeSoQW-TviEqBw";
//        String onceStr= "e404c105-5d7f-4a29-88b9-439d3861b55c";
//        long timestamp=1606822425908l;
        String onceStr= IdUtil.randomUUID();
        long timestamp=System.currentTimeMillis();

        
        RestTemplate restTemplate=new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization",authorization);
        headers.add("onceStr",onceStr);


        JSONObject jsonObject=new JSONObject(true);
        jsonObject.put("Authorization",authorization);
        jsonObject.put("onceStr",onceStr);
        jsonObject.put("timestamp",timestamp);
        jsonObject.put("url",url);



        String md5Str = SecureUtil.md5(jsonObject.toJSONString());
        System.out.println(md5Str);
        headers.add("timestamp", String.valueOf(timestamp));
        headers.add("signature",md5Str);
//        headers.add("signature","243d7975b11c67f0c8511c1c1a33f523");
        HttpEntity entity = new HttpEntity<>(null, headers);
//        ResponseEntity response = restTemplate.postForEntity("http://47.107.187.159"+url, entity, JSONObject.class);
        ResponseEntity<String> result = restTemplate.exchange("http://47.107.187.159"+url, HttpMethod.GET, entity, String.class);
        System.out.println(result.getBody());
    }
}
