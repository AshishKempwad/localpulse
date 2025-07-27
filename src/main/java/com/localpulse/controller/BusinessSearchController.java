package com.localpulse.controller;

import com.localpulse.dto.SearchRequestParameters;
import com.localpulse.dto.SearchResponse;
import com.localpulse.dto.SuggestionRequestParameters;
import com.localpulse.service.SearchService;
import com.localpulse.service.SuggestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BusinessSearchController {

    private final SuggestionService suggestionService;
    private final SearchService searchService;


    public BusinessSearchController(SuggestionService suggestionService, SearchService searchService) {
        this.suggestionService = suggestionService;
        this.searchService = searchService;
    }

    @GetMapping("/api/suggestions")
    public List<String> suggest(SuggestionRequestParameters parameters){
        return this.suggestionService.fetchSuggestions(parameters);
    }

    @GetMapping("/api/search")
    public SearchResponse search(SearchRequestParameters parameters){
        return this.searchService.search(parameters);
    }

}
