package model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import exceptions.LocalGovernmentException;
import exceptions.NotLegalAgeException;
import java.time.LocalDate;

class MinimarketTest {

	private String typeDocument;
	private String id;
	private int currentDay;
	private Minimarket newMinimarket;

	public void setupStage1() {
		newMinimarket = new Minimarket("Mi Barrio Te Quiere®", 0);
	}

	public void setupStage2() {
		newMinimarket = new Minimarket("Mi Barrio Te Quiere®", 0);
		try {
			newMinimarket.registPerson("PP", "613265789", 25);
		} catch (NotLegalAgeException nlae) {
			nlae.printStackTrace();
		} catch (LocalGovernmentException lge) {
			lge.printStackTrace();
		}
	}

	@Test
	public void testRegisterPerson() {

		// The person cannot register because he/she is a minor:

		setupStage1();
		typeDocument = "TI";
		id = "987654321";
		currentDay = LocalDate.now().getDayOfMonth();
		try {
			newMinimarket.registPerson(typeDocument, id, currentDay);
			fail("NotLegalAgeException is expected.");
		} catch (NotLegalAgeException nlae) {
			assertEquals(0, newMinimarket.getPeople().size(), "The person was registered even tho he/she was a minor.");
			assertEquals(1, newMinimarket.getPeopleWhoTried(), "The attempt was not registered.");
			nlae.printStackTrace();
		} catch (LocalGovernmentException lge) {
			fail("LocalGovernmentException not expected.");
		}

		/*-----------------------------------------------------------------------------------------------------------*/

		// The person can be successfully registered having an even ID number (penultimate) and being an odd day:

		setupStage1();
		typeDocument = "CC";
		id = "123456789";
		currentDay = 13;
		try {
			newMinimarket.registPerson(typeDocument, id, currentDay);
			assertEquals(1, newMinimarket.getPeople().size(), "The person was not registered correctly.");
			assertEquals(1, newMinimarket.getPeopleWhoTried(), "The attempt was not registered.");
		} catch (NotLegalAgeException nlae) {
			fail("NotLegalAgeException not expected.");
		} catch (LocalGovernmentException lge) {
			fail("LocalGovernmentException not expected.");
		}

		/*-----------------------------------------------------------------------------------------------------------*/

		// The person can be successfully registered having an odd ID number (penultimate) and being an even day:

		setupStage1();
		typeDocument = "CC";
		id = "281937456";
		currentDay = 4;
		try {
			newMinimarket.registPerson(typeDocument, id, currentDay);
			assertEquals(1, newMinimarket.getPeople().size(), "The person was not registered correctly.");
			assertEquals(1, newMinimarket.getPeopleWhoTried(), "The attempt was not registered.");
		} catch (NotLegalAgeException nlae) {
			fail("NotLegalAgeException not expected.");
		} catch (LocalGovernmentException lge) {
			fail("LocalGovernmentException not expected.");
		}

		/*-----------------------------------------------------------------------------------------------------------*/

		// The person can't be correctly registered because he/she has an even ID number (penultimate) and it's an even day:

		setupStage1();
		typeDocument = "CE";
		id = "737608165";
		currentDay = 4;
		try {
			newMinimarket.registPerson(typeDocument, id, currentDay);
			fail("LocalGovernmentException is expected.");
		} catch (NotLegalAgeException nlae) {
			fail("NotLegalAgeException not expected.");
		} catch (LocalGovernmentException lge) {
			assertEquals(0, newMinimarket.getPeople().size(), "The person was registered even if the penultimate number of his/her ID and the day of the month didn't match.");
			assertEquals(1, newMinimarket.getPeopleWhoTried(), "The attempt was not registered.");
			lge.printStackTrace();
		}

		/*-----------------------------------------------------------------------------------------------------------*/

		// The person can't be correctly registered because he/she has an odd ID number (penultimate) and it's an odd day:

		setupStage1();
		typeDocument = "CE";
		id = "469074512";
		currentDay = 19;
		try {
			newMinimarket.registPerson(typeDocument, id, currentDay);
			fail("LocalGovernmentException is expected.");
		} catch (NotLegalAgeException nlae) {
			fail("NotLegalAgeException not expected.");
		} catch (LocalGovernmentException lge) {
			assertEquals(0, newMinimarket.getPeople().size(), "The person was registered even if the penultimate number of his/her ID and the day of the month didn't match.");
			assertEquals(1, newMinimarket.getPeopleWhoTried(), "The attempt was not registered.");
			lge.printStackTrace();
		}
	}

	@Test
	public void testSearchPerson() {

		setupStage2();
		id = "613265789";
		Person objsearch = newMinimarket.searchPerson(id);
		assertNotNull(objsearch);

		/*--------------------------------------------------*/

		setupStage2();
		id = "551233598";
		Person obj = newMinimarket.searchPerson(id);
		assertNull(obj);
	}
}
