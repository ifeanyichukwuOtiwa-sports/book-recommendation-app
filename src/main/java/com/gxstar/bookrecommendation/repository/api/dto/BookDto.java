package com.gxstar.bookrecommendation.repository.api.dto;

import java.math.BigDecimal;

public record BookDto(
        Long id,
        String title,
        String isbn,
        String reviewCount,
        BigDecimal rating,
        String authorName
) {
    public static BookDto of(Long id, String title, String isbn, String reviewCount, BigDecimal rating, String authorName) {
        return new BookDto(id, title, isbn, reviewCount, rating, authorName);
    }
}
