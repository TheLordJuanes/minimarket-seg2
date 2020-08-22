/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Subject: Algorithms and Programming II
 * Seguimiento 2 - Week 2
 * Student Code: A00365977
 * @Author: Juan Esteban Caicedo A.
 * @Date: August, 22th 2020
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
*/
package ui;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;
import exceptions.LocalGovernmentException;
import exceptions.NotLegalAgeException;
import model.Minimarket;
import model.Person;

public class Main {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    public Scanner scanner = new Scanner(System.in);

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private Minimarket minimarket;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
     * Name: Main
     * Main constructor method. <br>
    */
    public Main() {
        String nameMinimarket = "Mi Barrio Te Quiere®";
        int peopleWhoTried = 0;
        minimarket = new Minimarket(nameMinimarket, peopleWhoTried);
    }

    /**
     * Name: main
     * Main method. <br>
    */
    public static void main(String[] args) throws NotLegalAgeException, LocalGovernmentException {
        Main main = new Main();
        main.showMenu();
    }

    /**
     * Name: showMenu
     * Method used to show the program menu. <br>
     * @throws NotLegalAgeException
     * @throws LocalGovernmentException
    */
    public void showMenu() throws NotLegalAgeException, LocalGovernmentException {
        boolean menu = true;
        System.out.println("\n--------------------");
        System.out.println(minimarket.getNameMinimarket());
        System.out.println("--------------------");
        while (menu) {
            System.out.println("\n**********");
            System.out.println("Start Menu");
            System.out.println("**********");
            System.out.println("\nType 1 to register the entry of a new person.\nType 2 to check the number of people who have tried to enter the mini-market.\nType 3 to end the program.\n");
            try {
                int opt;
                opt = scanner.nextInt();
                scanner.nextLine();
                switch (opt) {
                    case 1:
                        registerPerson();
                        break;
                    case 2:
                        consultPeople();
                        break;
                    case 3:
                        menu = false;
                        System.out.print("\nLeaving the menu...\n\nEnd of menu.\nGoodbye!\n\n");
                        break;
                    default:
                        System.out.println("Option not available ");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("\nError! You must enter a whole number. Try again.");
                scanner.next();
            }
        }
    }

    /**
     * Name: registerPerson
     * Method used to register a person, invoking the registPerson() method of the Minimarket class. <br>
    */
    public void registerPerson() {
        try {
            System.out.print("\nTI - Tarjeta de Identidad -\nCC - Cédula de Ciudadanía -\nPP - Pasaporte -\nCE - Cédula de Extranjería -\n\nDocument type of the person (2 LETTERS): ");
            String typeDocument = scanner.nextLine();
            while (!(typeDocument.equals(Person.TYPE_DOCUMENT_TI)) && !(typeDocument.equals(Person.TYPE_DOCUMENT_CC)) && !(typeDocument.equals(Person.TYPE_DOCUMENT_PP)) && !(typeDocument.equals(Person.TYPE_DOCUMENT_CE))) {
                System.out.println("\nError. Option not available. Try again.");
                System.out.print("\nTI - Tarjeta de Identidad -\nCC - Cédula de Ciudadanía -\nPP - Pasaporte -\nCE - Cédula de Extranjería -\n\nDocument type of the person (2 LETTERS): ");
                typeDocument = scanner.nextLine();
                System.out.println();
            }
            System.out.print("ID of the person: ");
            String id = scanner.nextLine();
            int currentDay = LocalDate.now().getDayOfMonth();
            String message = minimarket.registPerson(typeDocument, id, currentDay);
            System.out.println();
            System.out.println(message);
            System.out.println();
        } catch (NotLegalAgeException lae) {
            System.out.println();
            System.out.println("Exception in thread 'main'");
            lae.printStackTrace();
        } catch (LocalGovernmentException lge) {
            System.out.println();
            System.out.println("Exception in thread 'main'");
            lge.printStackTrace();
        }
    }

    /**
     * Name: consultPeople
     * Method used to consult the total number of people who have tried to enter the mini-market. <br>
    */
    public void consultPeople() {
        int totalPeople = minimarket.getPeopleWhoTried();
        System.out.println("The total quantity of people who have tried to enter the mini-market is " + totalPeople + ".");
    }
}