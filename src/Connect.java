import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.standard.RequestingUserName;

public class Connect {
	public static String sendGet(String url)
	{
		String result="";
		BufferedReader in = null;
		try
		{
			URL newUrl = new URL(url);
			URLConnection connection = newUrl.openConnection();
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line;
			while((line = in.readLine()) != null)
			{
				result += line;
			}
			
		} catch (Exception e) {
			System.out.println("get请求异常" + e);
			e.printStackTrace();
			// TODO: handle exception
		}
		//关闭输入流
		finally {
			try
			{
				if(in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				// TODO: handle exception
			}
			
		}
		return result;
	}
	//匹配url
	public static ArrayList<ZhiHuBean> getRecommendations (String content)
	{
		ArrayList<ZhiHuBean> result = new ArrayList<ZhiHuBean>();
		Pattern urlPattern = Pattern.compile("<h2>.+?question_link.+?href=\"(.+?)\".+?</h2>");
		Matcher urlmatcher = urlPattern.matcher(content);
		boolean isFind = urlmatcher.find();
		while(isFind)
		{
			ZhiHuBean zhihuTemp = new ZhiHuBean(urlmatcher.group(1));
			result.add(zhihuTemp);
			isFind = urlmatcher.find();
		}
		return result;
		
	}
	
}
