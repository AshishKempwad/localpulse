package com.localpulse.dto;

import java.util.List;

public record SearchResponse(List<Business>results,
                             List<Facet> facets,
                             Pagination pagination,
                             long timeTaken) {
}
