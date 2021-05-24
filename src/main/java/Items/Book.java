package Items;

import java.util.Objects;

public class Book extends Item {
    private String author;

    public Book(String author, String title) {
        super(title);
        this.author = author;
    }
    public String getAuthor() {
        return this.author;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getTitle().equals(((Book)o).getTitle())){
            if (this.getAuthor().equals(((Book)o).getAuthor())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(author,this.getTitle());
    }
}
