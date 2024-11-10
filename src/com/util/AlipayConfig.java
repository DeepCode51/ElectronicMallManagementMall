package com.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101200667460";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQC3mCTQTIn5oZpMb499FtVlZc6bOcHx8mb+2OKziC7Ef8WNGsHFXYSdvQ/pA9SvtI9n2pNb0aOpLZG3ZxnDB7u5A3c1Zs7IELZkVEb/jZ1lgkfQ05dGD9Jkfi+iHxvOhGvYEoalHuWi0y9OR7pdV468M2LbIph4aNMKRZy2IaGljxaG8qw+BudESrW/USmTmt8g8Sgiyj66f+myawSR1zZhhBaw0i8loeK2tr5SPwz0HQ8j3oou5tIHges3Wu2cxlw7WmbD3bBcVSXxx5b7e43XVFMns0Ee/jIwDxhkeGsWl8c3NVYToNi5T6UvaHvRuZTQe74XOAfckvj3IItwpGCdAgMBAAECggEBALRiSPFb8kiRze+BwQKPV7WblKaQ1gypNSb546uvhvvL6kacXnxTSQROiP5RpDvKdQf+CsCxNlL3ke8X3ojv5TJ/xDV0wIBzYzEqWOOnuVjN60+zRTMyQm44SQbJ9+Et3GPct+wuckNLqXQJJ4Okjno9JsjVsebEX239PV75pj/pru1oK96iFHhVs6wNeVVcEnzvLilG40FekGlXZkcQ7Py28elutu+FBJtMQl7S4EdgImR0gNrcPM+bu9lzXwGdYDiuf4JpcK10VIL7kdxxSiGtvvFxsRZ3s05VO6UgWGzkKlAepbHsM3JJak2TJFSwDV5jcXj81j/3IaFXNEPaQAECgYEA5XoPFfTFm1rtQOdkeG0NkhHyKHb7FkZsvw17Wt2HQl4mnSNDzLPBZnUMRl7lRgv5W9Y6Rv9yK3YaTOJuwgd6DhDsSHCbijQntX1qgXt6asSU5LxSubhUqZAlcN0P/85V52gr+ilreMwpzRZXTIQuvckForqF2xaVj/gKDn9h0AECgYEAzNB6Sgx3dhGAzvQ0BtHjOIAJ7nlvz3bMh+9JZW58cPB6D8keKB7G2nzQJc7fYFkt4AspBKVf+GaRexw0RJgtzu1G4aPs/zYXqx1rIHcI3hMsdXCWMeYUpOtu5HqoZudwPwKN0YvDDTrJO0izy7M5PgOjMSa4c5wDR6d82Sin0J0CgYBY3+sTz9bwkkitCHErCuFknzqwXVRljiTz4Z7S1FVcaOZ/mXbawwu2SMLWRk550I1k+hVcBfcLyhPpjxDiaM7mv7sjWymCXyy557bmKxKAY15fMGjBsRhpC4DUs2lM43b1xqyxmHUdNcVCvtJUuiT4L/DU03SgI6ENPz112/PgAQKBgQCg1kKCLeIOBNa7LkhA5sfhkaK69AAa0nbxdLaVgbsVUbL4/H3DEdAkuYkXd6ow1jdRTlJKxLdlsN4j6FUfXMX0Tp6wCPPOwqK5jGOY3gFHq5TpMJ2x17V0gTHXGH9wN+UMhivqVy+4DucBuCkBGOAl0+NucLK+jtCAj4VaXzPRfQKBgA5poSga252Dj6beI+CYbV9MLiAST/QB0D4h0OFA/133LOEFsVtHKh3cFauV5L6ASjwyFgohK/MNSvr3dAUZ30PrtSqJNE/IW+IhZ0wEKe5fTtdGaxqED1j2THPUTeguogprE0K7DKcTdmoHNfW8omwK/A+Rss2WtQAwglHAYIAU";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnZPOhFaunc8UcVeYistmdPVmOuY1d6ZZXaNzpEXF5/vU21cLxYei+fNZ0YKbo33QiC8nEL5NLSmNcxGC9TP2+W7f6OC5GqujEPpIRdvF7MVKtd7CcrZ5OwzX5OsaU1mmQP97OSnN2KKY3/glGE/mfLY/vXLKbvo7hYofB0ZKOGuVq8oZJumI1UKMSRmk2w6jbSscWpukx14n/5LEyuYMH7Xzur7QURRw3JOoc/FaBHNEJAxnIOE3Iq7artJedfPgja5aZcVXYG6yAG3HMmgLqhm0pZsPMsV4QrUFAyzgxhxyqLfT701d9m4GPxzHizJ0eNB3XDQeSYE8nlXJ1I6KywIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://127.0.0.1:8080/alipay/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://127.0.0.1:8080/onlineshopssm/orderPay.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
        	System.out.println("aaaaaaaaaaaaaaaa");
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

