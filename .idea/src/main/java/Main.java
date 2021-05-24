import Items.Book;
import Items.Magazine;
import Users.Lecturer;
import Users.Student;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello");
        Student student1 = new Student("Alicja", "Czernecka");
        Student student2 = new Student("Paweł", "Czernecki");
        Student student3 = new Student("Piotr", "Wawrzeń");
        Student student4 = new Student("Anna", "Marcinkowska");
        Lecturer lecturer1 = new Lecturer("Aleksandra", "Oleksy");
        Lecturer lecturer2 = new Lecturer("Paweł", "Skowroński");
        Lecturer lecturer3 = new Lecturer("Krzysztof", "Rychlik");
        Lecturer lecturer4 = new Lecturer("Zuzanna", "Krzysztofik");

        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());
        System.out.println(student4.toString());
        System.out.println(lecturer1.toString());
        System.out.println(lecturer2.toString());
        System.out.println(lecturer3.toString());
        System.out.println(lecturer4.toString());

        Library myLibrary = new Library();
        myLibrary.addUserToLibrary(student1, student2, student3, student4, lecturer1, lecturer2, lecturer3, lecturer4);
        myLibrary.printListOfUsers();

        Book book1 = new Book("Henryk Sienkiewicz", "Ogniem i mieczem");
        Book book6 = new Book("Henryk Sienkiewicz", "Ogniem i mieczem");
        Book book2 = new Book("R Ludlum", "Tożsamość Bourne'a");
        Book book3 = new Book("J.K. Rowling", "Harry Potter i Komnata Tajemnic");
        Book book4 = new Book("J.K. Rowling", "Harry Potter i Kamień Filozoficzny");
        Book book5 = new Book("Adam Mickiewicz", "Pan Tadeusz");
        Magazine magazine1 = new Magazine("01/2016", "National Geographic");
        Magazine magazine2 = new Magazine("03/2020", "Traveler");
        Magazine magazine3 = new Magazine("03/2020", "Traveler");
        Magazine magazine4 = new Magazine("05/2021", "Góry");
        Magazine magazine5 = new Magazine("05/2021", "Góry");

        myLibrary.addItemToLibrary(book1, book2, book3, book4, book5,book6, magazine1, magazine2, magazine3, magazine4, magazine5);
        myLibrary.printListOfMagazines();
        myLibrary.printListOfBooks();
        System.out.println();
        System.out.println();
        System.out.println();

//        student1.addItemToListOfRentedBooks(myLibrary.getMapOfFreeItems(),book1, book2, book3, magazine1);
        myLibrary.rentItemToUser(book1, student1);
        student1.printOfRentedBooks();
        System.out.println();
        myLibrary.printListOfBooks();
        System.out.println();
        myLibrary.printListOfMagazines();
        System.out.println();

        myLibrary.rentItemToUser(book5, lecturer1);
        lecturer1.printOfRentedItems();
        System.out.println();
        myLibrary.printListOfBooks();
        System.out.println();
        myLibrary.printListOfMagazines();
        System.out.println();

        try {
            myLibrary.importItemsFromFile("src/main/java/data.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        myLibrary.printListOfBooks();
        System.out.println();
        myLibrary.printListOfMagazines();
        System.out.println();


        System.out.println();
        try {
            myLibrary.exportUsersWithItemsToFile("src/main/java/export-data.csv"+ System.currentTimeMillis() + ".csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
