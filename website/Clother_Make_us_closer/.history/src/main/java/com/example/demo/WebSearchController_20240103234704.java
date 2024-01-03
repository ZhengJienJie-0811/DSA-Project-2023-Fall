package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class WebSearchController {

    // private List<String> searchResults;

    // @GetMapping("/")
    // public String index() {
    // return "DS";
    // }

    @PostMapping("/search")
    public String search(@RequestParam String item, @RequestParam String detail1,
            @RequestParam String detail2, @RequestParam String detail3, Model model) {

        String result = "Result for: " + item + ", " + detail1 + ", " + detail2 + ", " + detail3;
        Main.target = item;
        // Main.k1 = detail1;
        // Main.k2 = detail2;
        // Main.k3 = detail3;
        Main.setKeywords(item, detail1, detail2, detail3);
        // model.addAttribute("result", result);

        return ResponseEntity.ok()
                .body("Search initiated with parameters: " + target + ", " + k1 + ", " + k2 + ", " + k3);
    }

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

        return Main.childList;

    }

}