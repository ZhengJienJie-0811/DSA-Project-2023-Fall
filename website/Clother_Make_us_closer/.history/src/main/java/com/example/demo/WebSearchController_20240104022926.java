package com.example.demo;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

@Controller
public class WebSearchController {

    @PostMapping("/search")
    public void search(@RequestBody Map<String, String> searchData) throws FileNotFoundException {
        String item = searchData.get("search");
        String detail1 = searchData.get("condition1");
        String detail2 = searchData.get("condition2");
        String detail3 = searchData.get("condition3");

        Main.setKeywords(item, detail1, detail2, detail3);
        Main.initiateSearch();
        // No need to add anything to the model here if using a separate GET method for
        // results
    }

    @GetMapping("/results")
    public ResponseEntity<List<String>> getResults() {

        return ResponseEntity.ok(Main.childList); // Ensure childList is properly populated with results

    }

}