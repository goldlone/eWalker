package cn.goldlone.car.controller;

import cn.goldlone.car.model.Result;
import cn.goldlone.car.utils.HealthUtils;
import cn.goldlone.car.utils.ResultUtils;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by CN on 2018/6/8 17:23 .
 */
@RestController
public class HealthController extends BaseController {

    /**
     *
     * @param username
     * @param highValue
     * @param lowValue
     * @param disease 现病史。枚举值逗号分隔 高血压1，糖尿病2，冠心病3，心肌梗死5，脑卒中6，糖尿病肾病18，糖尿病足19，糖尿病眼病20，肾功能衰竭22，急性肾炎23，慢性肾炎24，高血压眼病25，高脂血症29，心绞痛30，冠状动脉血运重建史（心脏支架或搭桥手术）31，缺血性脑卒中（脑梗死）32，脑出血（脑溢血）33，短暂性脑缺血发作（小中风）34，视网膜出血或渗出35，视网膜视乳头水肿36
     * @return
     */
    public Result receiveBloodPressure(String username,
                                       int highValue,
                                       int lowValue,
                                       String disease) {
        System.out.println("用户ID："+username);
        System.out.println("高压："+highValue);
        System.out.println("低压："+lowValue);
        System.out.println("病史："+disease);
        // 存储血压记录



        // 调用API分析
//        String brief = HealthUtils.analyzeBloodPressure(username, highValue, lowValue, disease);
        String brief = "{\n" +
                "  \"code\": \"0000\",\n" +
                "  \"msg\": \"操作成功\",\n" +
                "  \"data\": {\n" +
                "    \"result\": \"您好，您6月09日19:02检测血压113/96mmHg：血压偏高，需警惕。请养成并坚持健康的生活方式，经常监测血压，以预防高血压的发生。\",\n" +
                "    \"highValue\": 113,\n" +
                "    \"lowValue\": 96,\n" +
                "    \"level\": 2,\n" +
                "    \"testTime\": \"2018-06-09 19:02:43\",\n" +
                "    \"describe\": \"血压偏高，需警惕。\",\n" +
                "    \"suggest\": \"请养成并坚持健康的生活方式，经常监测血压，以预防高血压的发生。\"\n" +
                "  }\n" +
                "}";
        String res = null;
        if(brief == null) {
            res = "系统遇到故障，暂时无法提供检测服务";
        } else {
            // {"code":"0000","msg":"操作成功","data":{"result":"您好，您6月09日19:02检测血压113/96mmHg：血压偏高，需警惕。请养成并坚持健康的生活方式，经常监测血压，以预防高血压的发生。","highValue":113,"lowValue":96,"level":2,"testTime":"2018-06-09 19:02:43","describe":"血压偏高，需警惕。","suggest":"请养成并坚持健康的生活方式，经常监测血压，以预防高血压的发生。"}}
            JSONObject json = null;
            try {
                json = new JSONObject(brief);
            } catch (Exception e) {
                System.out.println("解析JSON失败");
                e.printStackTrace();
            }
            if (json == null)
                return ResultUtils.error(ResultUtils.JSON_ANALYZE_FAIL, "解析JSON失败");
            JSONObject rec = json.getJSONObject("data");
            res = rec.getString("result");
        }

        return ResultUtils.success(res, "请求成功");
    }

}
