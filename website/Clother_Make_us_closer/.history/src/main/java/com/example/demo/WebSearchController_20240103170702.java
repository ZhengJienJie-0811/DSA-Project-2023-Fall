package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class WebSearchController {

    private List<String> searchResults;

    @GetMapping("/")
    public String index() {
        return "DS";
    }

    @PostMapping("/search")
    public String search(@RequestParam String item, @RequestParam String detail1,
            @RequestParam String detail2, @RequestParam String detail3, Model model) {

        String result = "Result for: " + item + ", " + detail1 + ", " + detail2 + ", " + detail3;
        Main.target = item;
        Main.k1 = detail1;
        Main.k2 = detail2;
        Main.k3 = detail3;

        model.addAttribute("result", result);

        return "page2";
    }

    /*
     * @GetMapping("/results")
     * public String getSearchResults(Model model) {
     * List<String> searchResults; // obtain search results from your logic
     * model.addAttribute("results", searchResults);
     * return "page2";
     * }
     */

    @GetMapping("/results")
    public List<String> getResults() {
        // 假設你有一個名為resultList的ArrayList
        List<String> resultList = new ArrayList<>();
        resultList.add("Result 1");
        resultList.add("Result 2");
        resultList.add("Result 3");
        // 返回ArrayList的JSON表示形式
        return resultList;
    }

}