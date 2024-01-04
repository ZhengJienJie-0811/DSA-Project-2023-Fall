package com.example.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    public static String target;
    public static String k1;
    public static String k2;
    public static String k3;
    public static List<WebPage> childList;

    // testing!

    public static void initiateSearch() throws FileNotFoundException {

        ArrayList<Keyword> keywords = new ArrayList<Keyword>();
        Keyword keyword_1 = new Keyword(k1, 50);
        Keyword keyword_2 = new Keyword(k2, 30);
        Keyword keyword_3 = new Keyword(k3, 10);
        Keyword keyword4 = new Keyword("字典", -200);
        Keyword keyword5 = new Keyword("翻譯", -200);
        keywords.add(keyword_1);
        keywords.add(keyword_2);
        keywords.add(keyword_3);
        keywords.add(keyword4);
        keywords.add(keyword5);

        // 內建keyword
        File file = new File("src/main/java/com/example/demo/input.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            keywords.add(new Keyword(sc.next(), sc.nextDouble()));
        }
        sc.close();

        // 檢視全部Keyword
        System.out.println("檢查全部keyword");
        for (int m = 0; m < keywords.size(); m++) {
            System.out.println(keywords.get(m).name + keywords.get(m).weight);
        }

        // 利用googleQuery找尋有關主題-->Clothes的網頁
        GoogleQuery ClothesQuery = new GoogleQuery(target);

        WebPage root = new WebPage("https://www.java.com/zh-TW/", "searchResult");
        WebTree tree = new WebTree(root);

        try {
            ClothesQuery.query();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<WebPage> pageArr = ClothesQuery.getPagelist();

        pageArr.remove(4); // 該網頁有問題

        System.out.println(pageArr.size());
        for (int i = 0; i < 14; i++) {
            // H&M的網頁有設授權機制
            if (pageArr.get(i).url.contentEquals("https://www2.hm.com/")) {
                pageArr.remove(i);
            }
            tree.root.addChild(new WebNode(new WebPage(pageArr.get(i).url, pageArr.get(i).name)));
            ArrayList<WebNode> root_child_child = new ArrayList<WebNode>();
            root_child_child = ClothesQuery.findChildUrl(pageArr.get(i).url);
            for (int j = 0; j < root_child_child.size(); j++) {
                tree.root.children.get(i).children.add(root_child_child.get(j));
            }
        }

        try {
            tree.setPostOrderScore(keywords);

        } catch (IOException e) {
            e.printStackTrace();
        }

        tree.eularPrintTree();
        childList = tree.getSortedChildrenNames();

    }

    public static void setKeywords(String target, String k1, String k2, String k3) {

        Main.target = target != null ? target : "";
        Main.k1 = k1 != null ? k1 : "";
        Main.k2 = k2 != null ? k2 : "";
        Main.k3 = k3 != null ? k3 : "";

    }

    public static void main(String[] args) throws FileNotFoundException {

        SpringApplication.run(Main.class, args);

    }
}