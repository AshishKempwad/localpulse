package com.localpulse.dto;

public record Pagination(int page,
                         int size,
                         long totalElements,
                         int totalPages) {
}
