import Items.Book;
import Items.Item;
import Items.Magazine;
import Users.Lecturer;
import Users.Student;
import Users.User;

import java.io.*;
import java.util.*;

public class Library {
    private List<User> usersSet;
    private List<Item> itemsSet;
    private Map<Item, Integer> mapOfAllItems;
    private Map<Item, Integer> mapOfFreeItems;
    public HashMap<Integer, Integer> numberOfUsersItems;
    public HashMap<Integer, Integer> items;

    public Library() {
        this.usersSet = new ArrayList<>();
        this.itemsSet = new ArrayList<>();
        this.mapOfAllItems = new HashMap<>();
        this.mapOfFreeItems = new HashMap<>();
        this.numberOfUsersItems = new HashMap<>();
        this.items = new HashMap<>();
    }

    public void addUserToLibrary(User... users) {
        this.usersSet.addAll(Arrays.asList(users));
    }

    public void addItemToLibrary(Item... items) {
        for (Item item : items) {
            if (mapOfAllItems.containsKey(item)) {
                mapOfAllItems.put(item, mapOfAllItems.get(item) + 1);
                mapOfFreeItems.put(item, mapOfFreeItems.get(item) + 1);
            } else {

                mapOfAllItems.put(item, 1);
                mapOfFreeItems.put(item, 1);
            }
        }
    }

    public void printListOfUsers() {
        Iterator<User> iterator = this.usersSet.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            String s = next.toString();
            System.out.println(s);
        }
    }

    public void printListOfMagazines() {
        for (Map.Entry<Item, Integer> entry : mapOfAllItems.entrySet()) {
            if (entry.getKey() instanceof Magazine) {
                System.out.println(entry.getKey().getTitle() + ";" + ((Magazine) entry.getKey()).getNumber()
                        + ";" + mapOfAllItems.get(entry.getKey()) + ";" + mapOfFreeItems.get(entry.getKey()));
            }
        }
    }

    public void printListOfBooks() {
        for (Map.Entry<Item, Integer> entry : mapOfAllItems.entrySet()) {
            if (entry.getKey() instanceof Book) {
                System.out.println(entry.getKey().getTitle() + ";" + ((Book) entry.getKey()).getAuthor()
                        + ";" + mapOfAllItems.get(entry.getKey()) + ";" + mapOfFreeItems.get(entry.getKey()));
            }
        }

    }


    public Map<Item, Integer> getMapOfAllItems() {
        return mapOfAllItems;
    }

    public Map<Item, Integer> getMapOfFreeItems() {
        return mapOfFreeItems;
    }

    public void importItemsFromFile(String csvFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(csvFile));
        String currentLine = "";
        StringBuilder stringBuilder = new StringBuilder();
        do {
            currentLine = reader.readLine();
            if (currentLine != null) {
                stringBuilder.append(currentLine);
                stringBuilder.append("\n");
            }
        } while (currentLine != null);
        reader.close();

        String data = stringBuilder.toString();

        for (String item : data.split("\n")) {
            String[] itemInArray = item.split(";");

            if (itemInArray[3].equals("B")) {
                Book book = new Book(itemInArray[1], itemInArray[0]);
                if (mapOfAllItems.containsKey(book)) {
                    mapOfAllItems.put(book, mapOfAllItems.get(book) + Integer.valueOf(itemInArray[2]));
                    mapOfFreeItems.put(book, mapOfFreeItems.get(book) + Integer.valueOf(itemInArray[2]));
                } else {
                    mapOfAllItems.put(book, Integer.valueOf(itemInArray[2]));
                    mapOfFreeItems.put(book, Integer.valueOf(itemInArray[2]));
                }
            } else {
                Magazine magazine = new Magazine(itemInArray[1], itemInArray[0]);
                if (mapOfAllItems.containsKey(magazine)) {
                    mapOfAllItems.put(magazine, mapOfAllItems.get(magazine) + Integer.valueOf(itemInArray[2]));
                    mapOfFreeItems.put(magazine, mapOfFreeItems.get(magazine) + Integer.valueOf(itemInArray[2]));
                } else {
                    mapOfAllItems.put(magazine, Integer.valueOf(itemInArray[2]));
                    mapOfFreeItems.put(magazine, Integer.valueOf(itemInArray[2]));
                }
            }
        }
    }

    public void exportUsersWithItemsToFile(String csvFile) throws IOException {
        StringBuilder dataToSave = new StringBuilder();
        for (User user : this.usersSet) {

            if (user instanceof Lecturer) {
                if (((Lecturer) user).getListOfRentedItems().size() == 0) {
                    continue;
                }
            } else {
                if (((Student) user).getListOfRentedItems().size() == 0) {
                    continue;
                }
            }

            dataToSave.append(user.getLibraryNumber());
            dataToSave.append("[");
            if (user instanceof Lecturer) {
                for (Item listOfRentedItem : ((Lecturer) user).getListOfRentedItems()) {
                    if (listOfRentedItem instanceof Book) {
                        dataToSave.append(listOfRentedItem.getTitle());
                        dataToSave.append("-");
                        dataToSave.append(((Book) listOfRentedItem).getAuthor());
                        dataToSave.append(";");
                    } else {
                        dataToSave.append(listOfRentedItem.getTitle());
                        dataToSave.append("-");
                        dataToSave.append(((Magazine) listOfRentedItem).getNumber());
                        dataToSave.append(";");
                    }
                }
                dataToSave.deleteCharAt(dataToSave.length() - 1);
            } else {
                for (Item listOfRentedItem : ((Student) user).getListOfRentedItems()) {
                    if (listOfRentedItem instanceof Book) {
                        dataToSave.append(listOfRentedItem.getTitle());
                        dataToSave.append("-");
                        dataToSave.append(((Book) listOfRentedItem).getAuthor());
                        dataToSave.append(";");
                    } else {
                        dataToSave.append(listOfRentedItem.getTitle());
                        dataToSave.append("-");
                        dataToSave.append(((Magazine) listOfRentedItem).getNumber());
                        dataToSave.append(";");
                    }
                }
                dataToSave.deleteCharAt(dataToSave.length() - 1);
            }
            dataToSave.append("]");
            dataToSave.append("\n");
        }
        System.out.println(dataToSave.toString());

        BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile));
        writer.write(dataToSave.toString());
        writer.close();
    }

    public boolean rentItemToUser(Item item, User user) {
        boolean isInLibrary = false;
        for (User user1 : usersSet) {
            if (user1.getLibraryNumber()== user.getLibraryNumber()) {
                isInLibrary = true;
                break;
            }
        }
        if (isInLibrary) {
            if (user instanceof Student) {
                Student student = (Student) user;
                if (student.getListOfRentedItems().size() < student.MAX_ITEMS) {

                    if (this.mapOfFreeItems.containsKey(item)) {

                        if (this.mapOfFreeItems.get(item) > 0) {

                            student.addItemToListOfRentedItems(item);
                            this.mapOfFreeItems.put(item, this.mapOfFreeItems.get(item) - 1);
                            return true;
                        } else {
                            System.out.println("You can not borrow this book because it is not availible.");
                            return false;
                        }
                    } else {
                        System.out.println("You can not borrow this book because does not exist " + item.getTitle() + ".");
                        return false;
                    }
                } else {
                    System.out.println(("You have limit the maximum of rented items. Please return some items to borrow new."));
                    return false;
                }
            } else {
                Lecturer lecturer = (Lecturer) user;
                if (lecturer.getListOfRentedItems().size() < lecturer.MAX_ITEMS) {

                    if (this.mapOfFreeItems.containsKey(item)) {

                        if (this.mapOfFreeItems.get(item) > 0) {

                            lecturer.addItemToListOfRentedItems(item);
                            this.mapOfFreeItems.put(item, this.mapOfFreeItems.get(item) - 1);
                            return true;
                        } else {
                            System.out.println("You can not borrow this book because it is not availible.");
                            return false;
                        }
                    } else {
                        System.out.println("You can not borrow this book because does not exist " + item.getTitle() + ".");
                        return false;
                    }
                } else {
                    System.out.println("You have limit the maximum of rented items. Please return some items to borrow new.");
                    return false;
                }
            }
        } else {
            System.out.println("You are not register in Library");
            return false;
        }
    }
}

