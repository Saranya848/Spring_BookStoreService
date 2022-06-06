package com.example.bookstore.service.books;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.BookData;

import java.util.List;

public interface IBookService {
    BookData addBook(BookDTO bookDTO);

    List<BookData> getBookList();

    BookData getBookById(int bookId);

    List<BookData> sortBookAscendingOrder();

    List<BookData> sortBookDescendingOrder();

    BookData updateBookById(int bookId, BookDTO bookDTO);

    BookData updateBookQuantity(int bookId, int bookQuantity);

    void deleteBookById(int bookId);

    List<BookData>getBookByAuthor(String bookAuthor);

//    BookData getBookById(String bookId);
}


