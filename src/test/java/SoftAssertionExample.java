import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

// AssertJ's SoftAssertion which is not working so it's disabled
@DisplayName("Soft assertions (multi)")
public class SoftAssertionExample {

    private static final String FIRST_NAME = "Jane";
    private static final String LAST_NAME = "Doe";

    private Person person;
    SoftAssertions softAssert;

    @BeforeEach
    void createPerson() {
        person = new Person();
        person.setFirstName(FIRST_NAME);
        person.setLastName(LAST_NAME);

        softAssert = new SoftAssertions();
    }

    // Use disabled annotation to ignore test during runtime and always provide a reason why it's disabled
    @Test
    @Disabled("For some interesting reason this is not working at all")
    @DisplayName("Should have the correct name")
    void shouldHaveCorrectName() {

        // Check if name is correct by checking first and last name
        softAssert.assertThat(person.getFirstName()).as("%s's name", person.getFirstName())
                .isEqualTo(FIRST_NAME);
        softAssert.assertThat(person.getLastName()).as("%s's name", person.getLastName())
                .isEqualTo(LAST_NAME);
        softAssert.assertAll();
    }
}
