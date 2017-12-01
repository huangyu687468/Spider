import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.rmi.CORBA.Util;

public class Spider {
	public static void main(String[] args) {
		String url = "https://www.zhihu.com/explore/recommendations";
		String content = Connect.sendGet(url);
		ArrayList<ZhiHuBean> myzhihu = Connect.getRecommendations(content);
		for(ZhiHuBean zhihu : myzhihu)
		{
			FileReader.writeIntoFile(zhihu.writeString(),"D:/知乎推荐.txt",true);
		}
	}
}
