import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("JUnit test practices")
public class PracticeTests {

    @BeforeAll
    static void setup() {
        System.out.println("Getting ready to run the tests");
    }

    private final boolean RUNNING = true;
    private Person testPerson, testPerson2, testPerson3;

    private final String FIRST_NAME = "Test";
    private final String LAST_NAME = "Person";
    private final String FIRST_NAME2 = "Dev";
    private final String LAST_NAME2 = "Tester";
    private final String FIRST_NAME3 = "Prod";
    private final String LAST_NAME3 = "Tester";

    private final Integer AGE = 22;
    private final Integer AGE2 = 18;
    private final Integer AGE3 = 18;
    private final Integer OLD_ENOUGH = 20;

    private List<Person> people;

    @BeforeEach
    void createPerson() {
        testPerson = new Person();
        testPerson2 = new Person();
        testPerson3 = new Person();
        people = new ArrayList<>();

        testPerson.setFirstName(FIRST_NAME);
        testPerson.setLastName(LAST_NAME);
        testPerson.setAge(AGE);

        testPerson2.setFirstName(FIRST_NAME2);
        testPerson2.setLastName(LAST_NAME2);
        testPerson2.setAge(AGE2);

        testPerson3.setFirstName(FIRST_NAME3);
        testPerson3.setLastName(LAST_NAME3);
        testPerson3.setAge(AGE3);

        people.add(testPerson);
        people.add(testPerson2);
    }

    @AfterAll
    static void finalStep() {
        System.out.println("Finished all tests");
    }

    @Nested
    @DisplayName("Test if person exist")
    class TestPerson {

        @Nested
        @DisplayName("Check person info")
        class ValidateUser {

            @Test
            @DisplayName("Check that person exist")
            void checkPersonInfo() {

                // Is it possible to assert two objects in one group assertion??
                assertThat(testPerson, allOf(
                        hasProperty("firstName", is(FIRST_NAME)),
                        hasProperty("lastName", is(LAST_NAME)),
                        hasProperty("age", is(AGE))
                ));
            }
        }

        @Nested
        @DisplayName("Is person old enough")
        class OldEnough {

            @Test
            @DisplayName("Person should be old enough")
            void shouldBeOldEnough() {
                assertThat(AGE, greaterThan(OLD_ENOUGH));
            }

            @Test
            @DisplayName("Person is not old enough")
            void shouldNotBeOldEnough() {
                assertThat(AGE2, lessThan(OLD_ENOUGH));
                System.out.println(testPerson2.getFirstName() + " is not old enough");
            }
        }

        @Nested
        @DisplayName("People are on the list")
        class PartOfPeopleList {

            @Test
            @DisplayName("List is not empty")
            void listIsNotEmpty() {
                assertThat(people, hasSize(2));
            }

            @Test
            @DisplayName("Check if test person is in people list")
            void isPartOfPeopleList() {
                assertThat(people, hasItem(testPerson));
            }

            @Test
            @DisplayName("Check if list contains both persons")
            void containsBothPersons() {
                assertThat(people, contains(testPerson, testPerson2));
            }

            @Test
            @DisplayName("Check that person 3 is not on the list")
            void notOnTheList() {
                assertThat(people, not(contains(testPerson3)));
                System.out.println(testPerson3.getFirstName() + " is not on the list.");
            }
        }
    }

}
