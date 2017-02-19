package sys.bg.util.base;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 日期工具类
 * 
 * @author admin
 * 
 */
public class DateUtil {

	public static void main(String[] args) {

	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式
	 * @return
	 */
	public static String dateToString(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return "";
	}

	/**
	 * 日期转字符串
	 * 
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date) {
		return dateToString(date, "yyyy-MM-dd hh:mm:ss");
	}

	public static String getRandom(int count){

		StringBuffer sb = new StringBuffer();

		String str = "0123456789";

		Random r = new Random();

		for(int i=0;i<count;i++){

			int num = r.nextInt(str.length());

			sb.append(str.charAt(num));

			str = str.replace((str.charAt(num)+""), "");

		}

		return sb.toString();

	}
}
