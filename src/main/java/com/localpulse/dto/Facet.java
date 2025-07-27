package com.localpulse.dto;

import java.util.List;

public record Facet(String name,
                    List<FacetItem> items) {
}
