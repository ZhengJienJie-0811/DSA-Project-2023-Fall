import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Main{
	
	// testing!
	public static void main(String[] args) {
	
		// 讀取輸入的3個Keyword，並建立Keyword物件設定權重分別為5,3,1
		Scanner input = new Scanner(System.in);	
		String k1 = input.next();
		String k2 = input.next();
		String k3 = input.next();
		ArrayList<Keyword> keywords = new ArrayList<Keyword>();
		Keyword keyword_1 = new Keyword(k1, 100);
		Keyword keyword_2 = new Keyword(k2, 10);
		Keyword keyword_3 = new Keyword(k3, 1);
		keywords.add(keyword_1);
		keywords.add(keyword_2);
		keywords.add(keyword_3);
		
		//利用googleQuery找尋有關主題-->Clothes的網頁
		GoogleQuery ClothesQuery = new GoogleQuery("Clothes");
		WebPage root = new WebPage("http://soslab.nccu.edu.tw/Welcome.html", "searchResult");
		WebTree tree = new WebTree(root);
		
		try {
				ClothesQuery.query();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		ArrayList<WebPage> pageArr = ClothesQuery.getPagelist();
		
//		for(WebPage wp:pageArr) {		
//			String decode_url = "";
//			try {
//				decode_url = java.net.URLDecoder.decode(wp.url,"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
//			tree.root.addChild(new WebNode(new WebPage(decode_url, wp.name)));
//		}
		
		for(int i = 0 ; i < 4 ; i++) {		
//			String decode_url = "";
//			try {
//				decode_url = java.net.URLDecoder.decode(pageArr.get(i).url,"utf-8");
//				System.out.println(pageArr.get(i).url);
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}	
			tree.root.addChild(new WebNode(new WebPage(pageArr.get(i).url, pageArr.get(i).name)));
		}
		
		
		try {
			tree.setPostOrderScore(keywords);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tree.eularPrintTree();
		System.out.println("FINISHED");
		
	}
}