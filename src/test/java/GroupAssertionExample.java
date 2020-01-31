import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

/* If we have a state that requires more than one assertion
   we can group them by using assertAll() method.
   We can use optional heading at beginning to identify the group.
   Then we just pass array, collection or stream of executable objects to group.
   To verify that person has a right name we can use group assertion to verify that
   person's first and last name are correct.
 */
@DisplayName("Group multiple assertions")
public class GroupAssertionExample {

    private static final String FIRST_NAME = "Test";
    private static final String LAST_NAME = "Person";

    private Person person;

    @BeforeEach
    void createPerson() {
        person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);
    }

    @Test
    @DisplayName("Should have the correct name")
    void shouldHaveCorrectName() {
        assertAll("name",
                () -> assertEquals(FIRST_NAME, person.getFirstName(), "The first name is incorrect"),
                () -> assertEquals(LAST_NAME, person.getLastName(), "The last name is incorrect")
        );
    }
}
