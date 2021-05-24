package Items;

import java.util.Objects;

public class Magazine extends Item {
    private String number;

    public Magazine(String number, String title) {
        super(title);
        this.number = number;
    }
    public String getNumber() {
        return this.number;
    }

    @Override
    public boolean equals(Object o) {
        if (this.getTitle().equals(((Magazine)o).getTitle())){
            if (this.getNumber().equals(((Magazine)o).getNumber())){
                return true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, this.getTitle());
    }
}
