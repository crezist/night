package com.xm.web.xm;

import java.io.FileWriter;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlipayConfig{
		 // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
		 public static String app_id = "2016101500689419";
		  
		 // 商户私钥，您的PKCS8格式RSA2私钥
		 public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCqTPnRPhWpNKxjtn5D1o/Nq7Y9jLArviZH2uaROHFwhb1TebaGMNHkNTMyBbSdFTBhijbhucPpf08aRItYl6eFZQznYOt08Jffg04DoMYycdk0fTyYo2pLkIs3mYIYacd/Sq7RAB0QtajXipSi9FLzljAyg8kARrCNTOx0j8Cl4QRIws1SN8deKr6+2sdWypC4GGXIe2/IKwpfPwd+086yXzz/6ahjkwTrT6BilCM93/mP6oPmHd7llEqom4ZaOoin6fkxayR3oQC2Gf+Z/pFgozp1AOnmzhyubA4Dcq1CMQ/t1uaAoWB+ZksK9h3GxT3rgq8WltKDTkaKtomldd5ZAgMBAAECggEAL+wdWH5KBiVmZWDTQy+7o7qwO729TXHiv9GflDn34hwgXL8hV72hMsCtuWPVoWZ3CNul+fsWYDyM++1R8Qor/+Ohr/KcD3osop8Gcwro+wUChDzBsoo5vS3dnzW5YDlUBEP10aZgh3U3IABFN13dgeI/idd/1m4N/JUq7NoO8h5cXMSV7c7TF2MQYEGIgavNnoKzkVZpYNVr99ykcsa7EHHXMzl2cBbsT5leMHfNTtDVXts5juuX5lVfGNodtz318VLdZfaUeWwKNIhhcKwk2DnhGOnCJP3NZH/xvSOrjh7jrkqDPpr8+yv8td/9CVrCThRRaqcNZU+LReyzoON7AQKBgQDRdcBUcHJHLa3bblafP1Or/67upng9s5VmRBWqwYTwB2tFfOfLU63oz4pgZ997OAKctlKVkytY1TtRH0Dy+c9jNEvFq96EnHgSL39+nZ6bz99cBZFDf8e2vO7XGKPNoGXVGvrZU6g5DpBBRX/43iYV5EBYM1vHEKEB4GOWgFPQMQKBgQDQI9BSs+WjMfwgtRL1GkOgJGBw/yup6i4E+uurUOf0o7jdbYJ+LONa7aIiDarxR7dFCKu4UtIAjdFAMIYnv98KCDFchAXNeF99m1ymHiPcEv1uLfzPCWHQxSItYSsz5Q6HhALCVvJ/KjGZ6tIGryfdXN7mfZzTB3G1hzRcibrOqQKBgC1DNsaIbgl3k4RBebJ76sbWP6UOkBk1P2mT+OKnYuTDlai0bxp/AKu4fXyLtb1Ywye+NmZgIRPXMDQ9frmCxg/38w/KLhGWBlRXs0NXKDkfWHL4OQXPDQcuGKaeinca3CaK5/4il82MmhuguyC6UJyWcFke8Aw6SbCwHpUdMf9hAoGAcrEZbUkmI/w6M4ez743Ob+M7pWhmlhWt0B64WEmX142UI6fhfUec7JD/g4ZGiPf8vDSf80zn3VhIUveqJSMscym6kYFKdUN8UwmWTw9SsUVhkyISKb/Av7qxL5JYrRruG6yZnJ8utvX4KRllLDu+WtN/HEFhdkOm2+Gp78gRQSkCgYBEVV9bPlKnZBVu+E/1e4opQvLpLWUVpktW1SfnVHF97z0yPrAR7ooqc91NbWJCZ3tQ0MAx4vpfUaH8MY5aTUMeuw9W1A+kZ6U5UioFrSCcao/jG9D+VGVvu9Oiey3+KjCUJ7cqi9weDC1PQzzrQEunC6WH64QFJMW18AclzNsfnw==";
		 // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
		 public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj4Anu1nr6T9x44i7sgdWm/KkpVTYJvAKqEukoZgUr7dLpYFasgGUgOcoXo4L+iw6h3sILz9/QhQ62ymukeRmEm81LHHEKb8sjx1Pkufv9k53U3HzPHoXhhmuuFksF6mIvGeyu06FvP0naJL6dGnnP6i1mJoW/PiYPurKp9cC0DFvG1LZxck/5awCUS9B8BAxNWUdVE83C6KjQ4JYdj3ZqKbakE+7Wmx1SAS2YP6hJx+yn2fWGISuglAT4xYAp3FZ3+DPTmEtwte9lW9oM+K5qLKtnzydVNhbcKp0i4L0/wOXt2sdUi9fbq7UYNkhx9YzQDr0yJnyT82oOoGeg0CzqwIDAQAB";
		 // 服务器异步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		 public static String notify_url = "http://工程公网访问地址";
		  
		 // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
		 public static String return_url = "http://127.0.0.1:8809/xm/paydone";
		  
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


