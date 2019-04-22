package com.ddw.demo.wechat.pay;

import com.github.wxpay.sdk.WXPay;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * All rights Reserved, Designed By 特斯联观翼
 * Copyright:    Copyright(C) 2016-2018
 * Company       特斯联观翼(北京)智能科技有限公司
 *
 * @author ddw
 * @version 1.0
 * @date 2018-12-21 22:46
 * @Description 微信支付
 */
@RestController
public class WXpayDemo {

    @GetMapping()
    public Map<String, Object> getPayPage(String orderNo, String totalFee,
                                          HttpServletRequest request,
                                          HttpServletResponse httpResponse) {
        Map<String, Object> result = new HashMap<>();
        String product_id = "1";
        HashMap<String, String> data = new HashMap<>(10);
        //商品描述
        data.put("body", "2018年11月份冷量账单");
        //商户订单号
        data.put("out_trade_no", orderNo);
        //终端设备号(商户自定义，如门店编号) 项目id
        data.put("device_info", "WEB");
        //货币类型 CNY:人民币
        data.put("fee_type", "CNY");
        //订单总金额，单位为分，只能为整数
        data.put("total_fee", totalFee);
        //调用微信支付API的机器IP
        data.put("spbill_create_ip",  request.getRemoteAddr());
        //异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数
//        data.put("notify_url", zJobProperties.getPay().getWx().getNotifyUrl());
        //交易类型JSAPI--公众号支付、NATIVE--原生扫码支付、APP--app支
        data.put("trade_type", "JSAPI");
        //trade_type=NATIVE时（即扫码支付），此参数必传。此参数为二维码中包含的商品ID，商户自行定义。
        data.put("openid", product_id);
        //商家数据包，原样返回
//        final UserIdAndCompanyIdDTO userIdAndCompanyId = userClient.getUserIdAndCompanyIdByToken().getData();
//        data.put("attach", "userId=" + userIdAndCompanyId.getUserId() + "&companyId=" + userIdAndCompanyId.getCompanyId());
        //订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。最短失效时间间隔必须大于5分钟
//        data.put("time_expire", "20170112104120");
        try {
            String appId = "wx1bdbf4c185fac093";
            String mchId = "1520095491";
            String key = "cc43ec98-f7b0-11e8-8eb2-f2801f1b9fd1";
            WXPayConfigService config = new WXPayConfigService(appId,mchId,key);
            WXPay wxpay = new WXPay(config);
            Map<String, String> r = wxpay.unifiedOrder(data);

            System.out.println("响应数据："+ r.toString());
            if ("SUCCESS".equals(r.get("return_code")) && "SUCCESS".equals(r.get("result_code"))
                    && StringUtils.isNotEmpty(r.get("code_url"))) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
