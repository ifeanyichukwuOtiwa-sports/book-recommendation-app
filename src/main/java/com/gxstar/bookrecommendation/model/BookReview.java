package com.gxstar.bookrecommendation.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "book_review")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookReview {
    @Id
    @GeneratedValue
    private Long id;
    private String user;
    private String reviews;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private Book book;
}
