package Users;

import Items.Book;
import Items.Item;
import Items.Magazine;

import java.util.ArrayList;
import java.util.List;

public class Lecturer extends User {
    public static final Integer MAX_ITEMS = 10;
    private List<Item> listOfRentedItems;

    public Lecturer(String firstName, String lastName) {
        super(firstName, lastName);
        this.listOfRentedItems = new ArrayList<>();
    }

    public String toString() {
        return super.toString() + "L";
    }

    public void printOfRentedItems() {
        for (Item item : listOfRentedItems) {
            if (item instanceof Book) {
                System.out.println(item.getTitle() + " " + ((Book) item).getAuthor());
            } else {
                System.out.println(item.getTitle() + " " + ((Magazine) item).getNumber());
            }
        }
    }

    public List<Item> getListOfRentedItems() {
        return listOfRentedItems;
    }

    public boolean addItemToListOfRentedItems(Item item) {
        this.listOfRentedItems.add(item);
        return true;
    }
}