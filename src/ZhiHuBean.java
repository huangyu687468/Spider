import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.spi.DirStateFactory.Result;
import javax.print.attribute.standard.RequestingUserName;
import javax.swing.border.MatteBorder;
import javax.swing.text.StyledEditorKit.BoldAction;

public class ZhiHuBean {
	String question;
	String questionDescription;
	String zhihuUrl;
	ArrayList<String> answer;
	public ZhiHuBean(String url)
	{
		question="";
		questionDescription="";
		zhihuUrl="";
		answer=new ArrayList<String>();
		if(getUrl(url))
		{
			System.out.println("正在抓取知乎链接："+zhihuUrl);
			String content=Connect.sendGet(zhihuUrl);
			Pattern pattern;
			Matcher matcher;
			//用正则表达式提取标题、描述、答案
			pattern = Pattern.compile("zh-question-title.+?<h2.+?>(.+?)</h2>");
			matcher = pattern.matcher(content);
			if(matcher.find())
			{
				question = matcher.group(1);
			}
			pattern = Pattern.compile("zh-question-detail.+?<div.+?>(.*?)</div>");
			matcher = pattern.matcher(content);
			if(matcher.find())
			{
				questionDescription=matcher.group(1);
			}
			pattern = Pattern.compile("/answer/content.+?<div.+?>(.*?)</div>");
			matcher = pattern.matcher(content);
			boolean isFind=matcher.find();
			while(isFind)
			{
				answer.add(matcher.group(1));
				isFind = matcher.find();
			}	
		}
	}
	@Override
	public String toString()
	{
		return "问题:" + "question" + "\n" + "描述" + questionDescription + "\n" + "链接：" + zhihuUrl + "\n回答：" 
				+ answer + "\n";
	}
	public String writeString()
	{
		String result = "";
		result += "问题：" + question +"\r\n\r\n";
		result += "描述：" + questionDescription + "\r\n\r\n";
		result += "链接：" + zhihuUrl + "\r\n\r\n";
		for(int i = 0;i < answer.size();i++)
		{
			result += "回答" + i + ":" + answer.get(i) + "\r\n\r\n\r\n";
		}
		result += "\r\n\r\n\r\n";
		result = result.replaceAll("<br>", "\r\n");
		result = result.replaceAll("<.*?>", "");
		return result;
	}
	//对url进行处理
	boolean getUrl(String url)
	{
		Pattern pattern = Pattern.compile("question/(.*?)/");
		Matcher matcher = pattern.matcher(url);
		if(matcher.find())
		{
			zhihuUrl = "http://www.zhihu.com/question/" + matcher.group(1);
		}
		else
		{
			return false;
		}
		return true;
	}
	
}
