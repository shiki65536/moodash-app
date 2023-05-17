package com.example.moodapp.retrofit;

public class QuoteBean {

    /**
     * quote : The will of man is his happiness.
     * author : Friedrich Schiller
     * category : happiness
     */

    private String quote;
    private String author;
    private String category;

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "QuoteBean{" +
                "quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
