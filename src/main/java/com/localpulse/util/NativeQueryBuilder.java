package com.localpulse.util;

import com.localpulse.dto.SuggestionRequestParameters;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;

public class NativeQueryBuilder {

    public static NativeQuery toSuggestQuery(SuggestionRequestParameters parameters){
        var suggester = ElasticsearchUtil.buildCompletionSuggester(
                Constants.Suggestion.SUGGEST_NAME,
                Constants.Suggestion.SEARCH_TERM,
                parameters.prefix(),
                parameters.limit());

        return NativeQuery.builder()
                .withSuggester(suggester)
                .withMaxResults(0) // we do not want any results object
                .withSourceFilter(FetchSourceFilter.of(b->b.withExcludes("*"))) //disable fetching the source object
                .build();
    }
}
