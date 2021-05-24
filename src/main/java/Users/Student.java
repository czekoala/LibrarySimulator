package Users;

import Items.Book;
import Items.Item;
import Items.Magazine;

import java.util.ArrayList;
import java.util.List;

public class Student extends User {
    public static final Integer MAX_ITEMS = 4;
    private List<Item> listOfRentedItems;

    public Student(String firstName, String lastName) {
        super(firstName, lastName);
        this.listOfRentedItems = new ArrayList<>();
    }

    public String toString() {
        return super.toString() + "S";
    }

    public List<Item> getListOfRentedItems() {
        return listOfRentedItems;
    }

    public void printOfRentedBooks(){
        for (Item item: listOfRentedItems){
            if(item instanceof Book){
                System.out.println(item.getTitle()+ " " + ((Book) item).getAuthor());
            }else {
                System.out.println(item.getTitle()+ " " + ((Magazine) item).getNumber());
            }
        }
    }
    public boolean addItemToListOfRentedItems(Item item){
        this.listOfRentedItems.add(item);
        return true;
    }
}
