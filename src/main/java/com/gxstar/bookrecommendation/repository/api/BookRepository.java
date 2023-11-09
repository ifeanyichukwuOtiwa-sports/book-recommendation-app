package com.gxstar.bookrecommendation.repository.api;

import com.gxstar.bookrecommendation.model.Book;
import com.gxstar.bookrecommendation.repository.api.dto.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    List<BookDto> findBooksByAuthorId(final Long authorId);

    Optional<BookDto> findBookByTitle(final String title);

    Optional<BookDto> findBookByIsbn(final String isbn);
}
