package com.example.pageable.api.view;

public class Views {

    public static class AuthorPublic {
    }

    public static class AuthorWithBooks extends AuthorPublic {
    }

    public static class BookPublic {
    }

    public static class BookWithAuthor extends BookPublic {
    }

    public static class BookDetails extends  BookWithAuthor {
    }

    public static class AuthorDetails extends AuthorWithBooks {

    }
}
