import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/* Hamcrest provides us a method assertThat(actualValue, A Matcher (expected value)
   You can also specify a optional error message for assertion fail.
 */
@DisplayName("Hamcrest assertions")
public class HamcrestExamples {

    @Nested
    @DisplayName("When boolean is true")
    class WhenBooleanIsTrue {

        @Test
        @DisplayName("Should be true")
        void shouldBeTrue() {
            // We expect our actual value to be a value in a matcher value
            assertThat(true, is(true));
        }
    }

    @Nested
    @DisplayName("When boolean is false")
    class WhenBooleanIsFalse {

        @Test
        @DisplayName("Should be false")
        void shouldBeFalse() {
            assertThat(false, is(false));
        }
    }


    @Nested
    @DisplayName("Object assertions with hamcrest")
    @Tag("objects")
    class ObjectAssertionsWithHamcrest {

        @Nested
        @DisplayName("Object is null or not null")
        class ObjectIsNullOrNotNull {

            @Test
            @DisplayName("Object is null")
            void shouldBeNull() {
                assertThat(null, nullValue());
            }

            @Test
            @DisplayName("Object is not null")
            void shouldNotBeNull() {
                assertThat(new Object(), notNullValue());
            }
        }

        // Use assert(a, is(a)) with arrays
        @Nested
        @DisplayName("Two objects are equal or not equal")
        class ObjectsAreEqualOrNotEqual {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 9;
            private final Integer NOTEXPECTED = 1;

            @Test
            @DisplayName("Objects are equal")
            void objectsAreEqual() {
                assertThat(ACTUAL, is(EXPECTED));
            }

            @Test
            @DisplayName("Objects are not equal")
            void objectsAreNotEqual() {
                assertThat(ACTUAL, not(NOTEXPECTED));
            }
        }

        // Compare two objects with Hamcrest
        @Nested
        @DisplayName("Two instances are equal or not equal")
        class InstancesAreEqualOrNotEqual {

            private final Object ACTUAL = new Object();
            private final Object EXPECTED = ACTUAL;
            private final Object NOTEXPECTED = new Object();

            @Test
            @DisplayName("Two instances are equal")
            void instancesAreEqual() {
                assertThat(ACTUAL, sameInstance(EXPECTED));
            }

            @Test
            @DisplayName("Two instances are not equal")
            void instancesAreNotEqual() {
                assertThat(ACTUAL, not(sameInstance(NOTEXPECTED)));
            }
        }
    }


    // Use assert(a, is(a)) for checking that lists contain same items
    @Nested
    @DisplayName("Collection assertion with hamcrest")
    class CollectionAssertion {

        @Nested
        @DisplayName("List assertions")
        @Tag("list")
        class ListAssertion {

            private Object first, second;
            private List<Object> list;

            @BeforeEach
            void createAndInitializeList() {
                first = new Object();
                second = new Object();

                list = Arrays.asList(first, second);
            }

            @Test
            @DisplayName("Should contain right amount of items")
            void shouldContainRightAmountOfItems() {
                assertThat(list, hasSize(2));
            }

            @Test
            @DisplayName("Should contain correct items")
            void shouldContainCorrectItems() {
                // also it's possible to use containsInAnyOrder()
                assertThat(list, contains(first, second));
            }

            @Test
            @DisplayName("Should contain correct item")
            void shouldContainCorrectItem() {
                // not(hasItem()) for testing it doesn't contain the item
                assertThat(list, hasItem(first));
            }

            @Test
            @DisplayName("Should contain a correct item (custom message)")
            void shouldContainCorrectItemWithCustomMessage() {
                assertThat(String.format(
                        "The list doesn't contain the expected object: %s", first)
                        , list, hasItem(first)
                        );
            }
        }

        @Nested
        @DisplayName("Map assertion")
        @Tag("map")
        class MapAssertion {

            private static final String INCORRECT_KEY = "incorrectKey";
            private static final String KEY = "key";
            private static final String VALUE = "value";

            private Map<String, String> map;

            @BeforeEach
            void createAndInitializeMap() {
                map = new HashMap<>();
                map.put(KEY, VALUE);
            }

            @Test
            @DisplayName("Map should contain the correct key")
            void shouldContainCorrectKey() {
                assertThat(map, hasKey(KEY));
            }

            @Test
            @DisplayName("Map shouldn't contain the incorrect key")
            void shouldNotContainIncorrectKey() {
                assertThat(map, not(hasKey(INCORRECT_KEY)));
            }

            @Test
            @DisplayName("Map should contain the correct value")
            void shouldContainCorrectValue() {
                assertThat(map, hasEntry(KEY, VALUE));
            }
        }
    }

    /* To combine multiple assertions with Hamcrest we us allOf() method inside assertThat()
       This returns matcher which expects that object matches with all matchers given as params.
       To check each param we use hasProperty() method which works similarly to assertThat by
       comparing actual and excepted values with is() method.
     */
    @Nested
    @DisplayName("Multi matchers with Hamcrest")
    class MultiMatchers {

        @Nested
        @DisplayName("Combine multiple assertions")
        class CombineAssertions {

            private static final String FIRST_NAME = "Jane";
            private static final String LAST_NAME = "Doe";

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
                assertThat(person, allOf(
                        hasProperty("firstName", is(FIRST_NAME)),
                        hasProperty("lastName", is(LAST_NAME))
                ));
            }
        }
    }
}
