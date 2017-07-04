package in.techmafiya.quotes.model;

/**
 * Created by ABCD on 21-Aug-16.
 */
public class Quotes {

    public String id;
    public String quote;
    public String author;
    public int likes;
    public int dislike;
    public int color;
    public int cat;
    public String language;


    public Quotes() //defaulut constroctor
    {
    }

    public Quotes(String id, String quote, String author, int likes, int dislike, int cat, String language) {
        this.id = id;
        this.quote = quote;
        this.author = author;
        this.likes = likes;
        this.dislike = dislike;
        this.cat = cat;
        this.language = language;

    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Quotes)) return false;

        Quotes quotes = (Quotes) o;

        return getId().equals(quotes.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
