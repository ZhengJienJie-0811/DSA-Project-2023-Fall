import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GoogleQuery 
{
	public String searchKeyword;
	public String url;
	public String content;
	public ArrayList<WebPage> pageList;
	public String title_x;
	public String webSite;
	
	public GoogleQuery(String searchKeyword)
	{
		this.searchKeyword = searchKeyword;
		try 
		{
			// This part has been specially handled for Chinese keyword processing. 
			// You can comment out the following two lines 
			// and use the line of code in the lower section. 
			// Also, consider why the results might be incorrect 
			// when entering Chinese keywords.
			String encodeKeyword=java.net.URLEncoder.encode(searchKeyword,"utf-8");
			this.url = "https://www.google.com/search?q="+encodeKeyword+"&oe=utf8&num=20";
			
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	private String fetchContent() throws IOException
	{
		String retVal = "";

		URL u = new URL(url);
		URLConnection conn = u.openConnection();
		//set HTTP header
		conn.setRequestProperty("User-agent", "Chrome/107.0.5304.107");
		InputStream in = conn.getInputStream();
		InputStreamReader inReader = new InputStreamReader(in, "utf-8");
		BufferedReader bufReader = new BufferedReader(inReader);
		String line = null;

		while((line = bufReader.readLine()) != null)
		{
			retVal += line;
		}
		//System.out.println(bufReader);
		return retVal;
	}
	
	public HashMap<String, String> query() throws IOException
	{
		if(content == null)
		{
			content = fetchContent();
		}
		

		HashMap<String, String> retVal = new HashMap<String, String>();

		/* 
		 * some Jsoup source
		 * https://jsoup.org/apidocs/org/jsoup/nodes/package-summary.html
		 * https://www.1ju.org/jsoup/jsoup-quick-start
 		 */
		
		//using Jsoup analyze html string
		Document doc = Jsoup.parse(content);
		
		//select particular element(tag) which you want 
		Elements lis = doc.select("div");
	//測試
//		Elements links = lis.select("a[href]");
//        for (Element link : links) {
//        	String edit_l = link.attr("href");
//        	if(edit_l.startsWith("/url?q=")) {
//        		edit_l = edit_l.replace("/url?q=", "");
//        		int indexOfChar = edit_l.indexOf("&");
//        		System.out.println("連結: " + edit_l.substring(0, indexOfChar));
//        	}
//        	System.out.println("一個網頁的子網頁結束了");
//            
//        }
		
	
        
		lis = lis.select(".kCrYT");
		
		pageList = new ArrayList<WebPage>();
		for(Element li : lis)
		{
			try 
			{
			//SHOW THE CONTENT-->li
//				System.out.println("SHOW THE WHOLE CONTENT-->li");
//				System.out.println(li);
				
				String citeUrl = li.select("a").get(0).attr("href").replace("/url?q=", "");
				String title = li.select("a").get(0).select(".vvjwJb").text();
				int theFirstChar = citeUrl.indexOf("&");
				String edit_citeUrl = citeUrl.substring(0, theFirstChar);
			
				//直接在這裡把從HTML中直接抓下來的網址decode
				String decoded_citeUrl = "";
				try {
					decoded_citeUrl = java.net.URLDecoder.decode(edit_citeUrl,"utf-8");
					System.out.println(decoded_citeUrl);
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				
				if(title.equals("")) 
				{
					continue;
				}
				
				
				System.out.println("Title: " + title + ", URL: " + decoded_citeUrl);
				
				
				
				
				
			//Test find child
	
//				Elements child_url = li.select("div");
//				System.out.println("This is one_child URL");
//				System.out.println(child_url);
//				child_url.select("BNeawe");
//				System.out.println("This is child URL");
//				System.out.println(child_url);
//				String edit_child_url = child_url.select("a").get(0).attr("href").replace("/url?q=", "");
//				System.out.println("This is edit_child URL");
//				System.out.println(edit_child_url);
//				String edit_child_url_2 = child_url.select("a").get(1).attr("href").replace("/url?q=", "");
//				System.out.println("This is edit_child URL_2");
//				System.out.println(edit_child_url_2);
				
				
				

				
				pageList.add(new WebPage(decoded_citeUrl, title));
				//put title and pair into HashMap
				retVal.put(title, citeUrl);

			} catch (IndexOutOfBoundsException e) 
			{
//				e.printStackTrace();
			}
		}
		
		//測試二-->這個Try-catch可以抓取網址中的所有子網頁
		try {
			// 連接到網站
			System.out.println("這是測試");
			String text_x = java.net.URLDecoder.decode(pageList.get(1).url,"utf-8");
			System.out.println("https://dictionary.cambridge.org/zht/%E8%A9%9E%E5%85%B8/%E8%8B%B1%E8%AA%9E-%E6%BC%A2%E8%AA%9E-%E7%B9%81%E9%AB%94/clothes");
			System.out.println(text_x);
			Document doc_1 = Jsoup.connect(text_x).get();

		    // 提取並打印所有連結
		    Elements links = doc_1.select("a[href]");
		    for (Element link : links) {
		    	System.out.println("連結: " + link.attr("abs:href"));
		    	}
		    } catch (Exception e) {
		            e.printStackTrace();
		    }
		
		return retVal;
	}
	
	public ArrayList getPagelist() {
		return pageList;
	}
	
	public String getURL() {
		return url;
	}
}