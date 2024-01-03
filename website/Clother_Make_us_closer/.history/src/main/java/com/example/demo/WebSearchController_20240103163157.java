package com.example.demo;

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

        model.addAttribute("result", result);

        return "page2";
    }

    @GetMapping("/results")
    public String getSearchResults(Model model) {
        List<String> searchResults; // obtain search results from your logic
        model.addAttribute("results", searchResults);
        return "page2";
    }

}