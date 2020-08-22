package model;

import java.util.ArrayList;
import java.util.List;
import exceptions.LocalGovernmentException;
import exceptions.NotLegalAgeException;

public class Minimarket {

    // -----------------------------------------------------------------
    // Attributes
    // -----------------------------------------------------------------

    private String nameMinimarket;
    private int peopleWhoTried;

    // -----------------------------------------------------------------
    // Relations
    // -----------------------------------------------------------------

    private List<Person> people;

    // -----------------------------------------------------------------
    // Methods
    // -----------------------------------------------------------------

    /**
	 * Name: Minimarket
	 * Constructor method of a minimarket. <br>
	 * @param nameMinimarket - name of the minimarket - nameMinimarket = String, nameMinimarket != null, nameMinimarket != ""
	 * @param peopleWhoTried - number of people who have tried to enter the minimarket - peopleWhoTried = int
	*/
    public Minimarket(String nameMinimarket, int peopleWhoTried) {
        this.nameMinimarket = nameMinimarket;
        this.peopleWhoTried = peopleWhoTried;
        people = new ArrayList<Person>();
    }

    /**
	 * Name: getNameMinimarket
	 * Method used to get the name of the minimarket. <br>
	 * @return A String representing the name of the minimarket.
	*/
    public String getNameMinimarket() {
        return nameMinimarket;
    }

    /**
	 * Name: setNameMinimarket
	 * Method used to update the name of the minimarket.  <br>
	 * @param nameMinimarket - name of the minimarket - nameMinimarket = String, nameMinimarket != null, nameMinimarket != ""
	*/
    public void setNameMinimarket(String nameMinimarket) {
        this.nameMinimarket = nameMinimarket;
    }

    /**
	 * Name: getPeopleWhoTried
	 * Method used to get the number of people who have tried to enter the minimarket. <br>
	 * @return A String representing the number of people who have tried to enter the minimarket.
	*/
    public int getPeopleWhoTried() {
        return peopleWhoTried;
    }

    /**
	 * Name: setNameMinimarket
	 * Method used to update the number of people who have tried to enter the minimarket  <br>
	 * @param peopleWhoTried - number of people who have tried to enter the minimarket - peopleWhoTried = int
	*/
    public void setPeopleWhoTried(int peopleWhoTried) {
        this.peopleWhoTried = peopleWhoTried;
    }

    /**
	 * Name: getPeople
	 * Method used to get the list of people registered in the minimarket system. <br>
	 * @return A List of Person representing the list of people registered in the minimarket system.
	*/
    public List<Person> getPeople() {
        return people;
    }

    /**
	 * Name: setPeople
	 * Method used to update the list of people registered in the minimarket system. <br>
	 * @param people - list of people registered in the minimarket system - people = List of Person
	*/
    public void setPeople(List<Person> people) {
        this.people = people;
    }

    /**
     * Name: registPerson
     * Method used to register a person in the list of people registered of the minimarket system. <br>
     * <b>pre: </b> List of people already initialized. <br>
     * <b>post: </b> Registry process determined of the person in question in the list of people registered in the minimarket system. <br>
     * @param typeDocument - document type of the person - typeDocument = String, typeDocument != null, typeDocument != ""
     * @param id - ID of the person - id = String, id != null, id != ""
     * @param currentDay - current day of the month - currentDay = int, currentDay != 0, currentDay >= 1, currentDay <= 31
     * @return A String with a message about the successful adding process of the person to the list of people registered in the minimarket system; or, a NotLegalAgeException or a LocalGovernmentException.
    */
    public String registPerson(String typeDocument, String id, int currentDay) throws NotLegalAgeException, LocalGovernmentException {
        String message = "";
        Person objSearch = null;
        if (people.size() != 0) {
            objSearch = searchPerson(id);
        }
        if (objSearch != null) {
            setPeopleWhoTried(getPeopleWhoTried() + 1);
            message = "This person was already been registered today in the system with that ID, so he/she can enter.";
        } else {
            if (typeDocument.equals(Person.TYPE_DOCUMENT_TI)) {
                setPeopleWhoTried(getPeopleWhoTried() + 1);
                throw new NotLegalAgeException();
            } else {
                if (currentDay % 2 != 0) {
                    if (id.charAt(id.length() - 2) % 2 == 0) {
                        Person obj = new Person(typeDocument, id);
                        people.add(obj);
                        setPeopleWhoTried(getPeopleWhoTried() + 1);
                        message = "New person successfully registered.";
                    } else {
                        setPeopleWhoTried(getPeopleWhoTried() + 1);
                        message = "The penultimate number of this person's ID is not even, so according to the local government, this person can't enter the minimarket.\nCurrent day isn't even: " + currentDay;
                        throw new LocalGovernmentException(message);
                    }
                } else {
                    if (id.charAt(id.length() - 2) % 2 != 0) {
                        Person obj = new Person(typeDocument, id);
                        people.add(obj);
                        setPeopleWhoTried(getPeopleWhoTried() + 1);
                        message = "New person successfully registered.";
                    } else {
                        setPeopleWhoTried(getPeopleWhoTried() + 1);
                        message = "The penultimate number of this person's ID is not odd, so according to the local government, this person can't enter the minimarket.\nCurrent day isn't odd: " + currentDay;
                        throw new LocalGovernmentException(message);
                    }
                }
            }
        }
        return message;
    }

    /**
     * Name: searchPerson
     * Method used to search a person in the list of people registered in the minimarket system. <br>
     * <b>pre: </b> List of people already initialized. <br>
     * <b>post: </b> Searching process determined of the person in question in the list of people registered in the minimarket system. <br>
     * @param id - ID of a person - id = String, id != null, id != ""
     * @return An object Person different from null if the person in question was found in the system, or with null if not.
    */
    public Person searchPerson(String id) {
        Person objSearch = null;
        boolean findPerson = false;
        for (int i = 0; i < people.size() && !findPerson; i++) {
            if (people.get(i) != null) {
                if (people.get(i).getId().equals(id)) {
                    objSearch = people.get(i);
                    findPerson = true;
                }
            }
        }
        return objSearch;
    }
}
