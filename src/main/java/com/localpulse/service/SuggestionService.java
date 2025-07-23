package com.localpulse.service;

import com.localpulse.dto.SuggestionRequestParameters;
import com.localpulse.util.Constants;
import com.localpulse.util.NativeQueryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuggestionService {

    private static final Logger log = LoggerFactory.getLogger(SuggestionService.class);
    private final ElasticsearchOperations elasticsearchOperations;


    public SuggestionService(ElasticsearchOperations elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public List<String> fetchSuggestions(SuggestionRequestParameters parameters){
        log.info("suggestion request: {}",parameters);
        var query = NativeQueryBuilder.toSuggestQuery(parameters);
        var searchHits  = this.elasticsearchOperations.search(query, Object.class, Constants.Index.SUGGESTION);
        return Optional.ofNullable(searchHits.getSuggest())
                .map(s->s.getSuggestion((Constants.Suggestion.SUGGEST_NAME)))
                .stream()
                .map(s->s.getEntries())
                .flatMap(l->l.stream())
                .map(e->e.getOptions())
                .flatMap(l->l.stream())
                .map(o->o.getText())
                .toList();
    }
}
