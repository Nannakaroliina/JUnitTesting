import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/* When we want to write assertions we use JUnit5API for that.
   To compare two values, we use assert(expected value, actual value) order.
   We can use specific error message if test fails by passing this message
   as a last param of the method.
 */
@DisplayName("Object assertions")
public class ObjectAssertionExample {

    final boolean testing = true;

    @Nested
    @DisplayName("When boolean is true")
    class WhenBooleanIsTrue {

        @Test
        @DisplayName("Should be true")
        void shouldBeTrue() {
            assertTrue(testing, "The boolean is not true");
        }
    }

    @Nested
    @DisplayName("When boolean is false")
    class WhenBooleanIsFalse {

        @Test
        @DisplayName("Should be false")
        void shouldBeFalse() {
            assertFalse(testing, "The boolean is not false");
        }
    }

    @Nested
    @DisplayName("When object is null")
    class WhenObjectIsNull {

        @Test
        @DisplayName("Should be null")
        void shouldBeNull() {
            assertNull(null);
        }
    }

    @Nested
    @DisplayName("When object is not null")
    class WhenObjectIsNotNull {

        @Test
        @DisplayName("Should not be null")
        void shouldNotBeNull() {
            assertNotNull(new Object());
        }
    }

    /* To compare to values we use Equals method.
       When we want to compare two objects we can also use assertSame()
     */
    @Nested
    @DisplayName("When two objects are equal or not equal")
    @Tag("objects")
    class WhenTwoObjectsAreEqualOrNotEqual {

        @Nested
        @DisplayName("When objects are integers")
        class WhenObjectsAreIntegers {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 9;
            private final Integer NOTEXPECTED = 10;

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertEquals(EXPECTED, ACTUAL);
            }

            @Test
            @DisplayName("Should not be equal")
            void shouldNotBeEqual() {
                assertNotEquals(NOTEXPECTED, ACTUAL);
            }
        }

        @Nested
        @DisplayName("When objects are strings")
        class WhenObjectsAreStrings {

            private final String ACTUAL = "Hello";
            private final String EXPECTED = "Hello";
            private final String NOTEXPECTED = "Hi";

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertEquals(EXPECTED, ACTUAL);
            }

            @Test
            @DisplayName("Should not be equal")
            void shouldNotBeEqual() {
                assertNotEquals(NOTEXPECTED, ACTUAL);
            }
        }

        @Nested
        @DisplayName("When objects are objects")
        class WhenObjectsAreObjects {

            private final Object ACTUAL = new Object();
            private final Object EXPECTED = ACTUAL;
            private final Object NOTEXPECTED = new Object();

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertEquals(EXPECTED, ACTUAL);
            }

            @Test
            @DisplayName("Should not be equal")
            void shouldNotBeEqual() {
                assertNotEquals(NOTEXPECTED, ACTUAL);
            }
        }
    }

    /* To compare two arrays we use assertArrayEquals()
       and to compare to lists we use assetIterableEquals()
     */
    @Nested
    @DisplayName("When collections are equal")
    class WhenArraysAreEqualOrNotEqual {

        @Nested
        @DisplayName("When arrays contain integers")
        @Tag("array")
        class WhenArraysContainIntegers {

            final int[] ACTUAL = new int[]{2, 5, 7};
            final int[] EXPECTED = new int[]{2, 5, 7};

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertArrayEquals(EXPECTED, ACTUAL);
            }
        }

        @Nested
        @DisplayName("When list contain integers")
        @Tag("list")
        class WhenListContainIntegers {

            private final List<Integer> ACTUAL = Arrays.asList(2, 5, 7);
            private final List<Integer> EXPECTED = Arrays.asList(2, 5, 7);

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertIterableEquals(EXPECTED, ACTUAL);
            }
        }

        @Nested
        @DisplayName("Map collection")
        @Tag("map")
        class MapAssertion {

            private static final String KEY = "key";
            private static final String VALUE = "value";
            private Map<String, String> map;

            @BeforeEach
            void createAndInitializeMap() {
                map = new HashMap<>();
                map.put(KEY, VALUE);
            }

            @Test
            @DisplayName("Should contain the correct key")
            void shouldContainCorrectKey() {
                assertTrue(
                        map.containsKey(KEY),
                        () -> String.format("The map doesn't contain the key: %s", KEY)
                );
            }
        }
    }
}
