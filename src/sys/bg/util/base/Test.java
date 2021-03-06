package sys.bg.util.base;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;



import com.alibaba.fastjson.JSONObject;



public class Test
{
    
	/**

	    * 获得ACCESS_TOKEN

	    * 

	    * @Title: getAccess_token

	    * @Description: 获得ACCESS_TOKEN

	    * @param @return 设定文件

	    * @return String 返回类型

	    * @throws

	    */

	   public static String getAccess_token() {

	       String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx50c272f896964b49&secret=58206ff1ac39c7cd0f94bae408a5778e";

	       String accessToken = null;

	       try {

	           URL urlGet = new URL(url);

	           HttpURLConnection http = (HttpURLConnection) urlGet

	                   .openConnection();

	           http.setRequestMethod("GET"); // 必须是get方式请求

	           http.setRequestProperty("Content-Type",

	                   "application/x-www-form-urlencoded");

	           http.setDoOutput(true);

	           http.setDoInput(true);

	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

	           http.connect();

	           InputStream is = http.getInputStream();

	           int size = is.available();

	           byte[] jsonBytes = new byte[size];

	           is.read(jsonBytes);

	           String message = new String(jsonBytes, "UTF-8");

	           JSONObject demoJson = JSONObject.parseObject(message);

	           accessToken = demoJson.getString("access_token");

	           System.out.println(accessToken);

	           is.close();

	       } catch (Exception e) {

	           e.printStackTrace();

	       }

	       return accessToken;

	   }
	   
	   /**
	     * 获得分享链接的签名。
	     * @param ticket
	     * @param nonceStr
	     * @param timeStamp
	     * @param url
	     * @return
	     * @throws Exception
	     */
	    public static String getSignature(String ticket, String nonceStr, long timeStamp, String url) throws Exception {
	        String sKey = "jsapi_ticket=" + ticket
	                + "&noncestr=" + nonceStr + "×tamp=" + timeStamp
	                + "&url=" + url;
	        return getSignature(sKey);
	    }
	 
	 
	 
	 /**
	     * 验证签名。
	     * 
	     * @param signature
	     * @param timestamp
	     * @param nonce
	     * @return
	     */
	    public static String getSignature(String sKey) throws Exception {
	        String ciphertext = null;
	        MessageDigest md = MessageDigest.getInstance("SHA-1");
	        byte[] digest = md.digest(sKey.toString().getBytes());
	        ciphertext = byteToStr(digest);
	        return ciphertext.toLowerCase();
	    }
	    
	    public static String getJsApiTicket(String access_token) {
	        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";
	        String requestUrl = url.replace("ACCESS_TOKEN", access_token);
	        System.out.println(requestUrl);
		       String ticket = null;

		       try {

		           URL urlGet = new URL(requestUrl);

		           HttpURLConnection http = (HttpURLConnection) urlGet

		                   .openConnection();

		           http.setRequestMethod("GET"); // 必须是get方式请求

		           http.setRequestProperty("Content-Type",

		                   "application/x-www-form-urlencoded");

		           http.setDoOutput(true);

		           http.setDoInput(true);

		           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒

		           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒

		           http.connect();

		           InputStream is = http.getInputStream();

		           int size = is.available();

		           byte[] jsonBytes = new byte[size];

		           is.read(jsonBytes);

		           String message = new String(jsonBytes, "UTF-8");

		           JSONObject demoJson = JSONObject.parseObject(message);
                   System.out.println(message+"wo shi message");
		           ticket = demoJson.getString("ticket");

		           System.out.println(ticket);

		           is.close();

		       } catch (Exception e) {

		           e.printStackTrace();

		       }

		       return ticket;
	    }
	 
	 /** 
	     * 将字节数组转换为十六进制字符串 
	     *  
	     * @param byteArray 
	     * @return 
	     */ 
	    private static String byteToStr(byte[] byteArray) {  
	        String strDigest = "";  
	        for (int i = 0; i < byteArray.length; i++) {  
	            strDigest += byteToHexStr(byteArray[i]);  
	        }  
	        return strDigest;  
	    }  
	    
	  
	  /** 
	     * 将字节转换为十六进制字符串 
	     *  
	     * @param mByte 
	     * @return 
	     */ 
	    
	    private static String byteToHexStr(byte mByte) {  
	        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };  
	        char[] tempArr = new char[2];  
	        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];  
	        tempArr[1] = Digit[mByte & 0X0F];  
	   
	        String s = new String(tempArr);  
	        return s;  
	    }
	   public static void main(String[] args) {
		getAccess_token();
	}
}