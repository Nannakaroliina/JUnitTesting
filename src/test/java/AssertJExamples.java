import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/* AssertJ provides us another version of assertThat()
   In this version assertThat(param) takes a actual value or object as a method param.
   We can write our assertions by using returned object which enables
   us to make a chain of assertions by simply invoking another assertion method.
 */
@DisplayName("Assertions with AssertJ")
public class AssertJExamples {


    @Nested
    @DisplayName("Boolean assertions")
    class BooleanAssertions {

        @Test
        @DisplayName("When boolean is true")
        void shouldBeTrue() {
            // .isTrue is assertion method which checks if our boolean is true
            assertThat(true).isTrue();
        }

        @Test
        @DisplayName("When boolean is false")
        void shouldBeFalse() {
            /* Use describedAs() to show a custom error message when test fails
               and overridingErrorMessage() to override entire error message.
               Last one basically changes the format of the error message and makes it shorter. */
            assertThat(true).overridingErrorMessage("The boolean is not false").isFalse();
        }
    }

    @Nested
    @DisplayName("Null or not null assertions")
    class NullOrNotNullAssertions {

        private final Object NULL = null;
        private final Object NOTNULL = new Object();

        @Test
        @DisplayName("When object is null")
        void shouldBeNull() {
            // This takes a object param so we can't just write a null to param
            assertThat(NULL).isNull();
        }

        @Test
        @DisplayName("When object is not null")
        void shouldNotBeNull() {
            // it's also possible to create object inside the param, but that's not good coding principle
            assertThat(NOTNULL).isNotNull();
        }
    }

    @Nested
    @DisplayName("Equal or not equal assertions")
    class EqualOrNotEqualAssertions {

        // With integers we use isEqualByComparingTo()
        @Nested
        @DisplayName("When objects are integers")
        class IntegerObjects {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 9;
            private final Integer NOTEXPECTED = 11;

            @Test
            @DisplayName("Should be equal integers")
            void shouldBeEqualInts() {
                assertThat(ACTUAL).isEqualByComparingTo(EXPECTED);
            }

            @Test
            @DisplayName("Should not be equal integers")
            void shouldNotBeEqualInts() {
                assertThat(ACTUAL).isNotEqualByComparingTo(NOTEXPECTED);
            }
        }

        // With objects we use isSameAs()
        @Nested
        @DisplayName("When comparing two objects")
        @Tag("objects")
        class TwoObjects {

            private final Object ACTUAL = new Object();
            private final Object EXPECTED = ACTUAL;
            private final Object NOTEXPECTED = new Object();

            @Test
            @DisplayName("Should be equal objects")
            void shouldBeEqualObjects() {
                assertThat(ACTUAL).isSameAs(EXPECTED);
            }

            @Test
            @DisplayName("Should not be equal objects")
            void shouldNotBeEqualObjects() {
                assertThat(ACTUAL).isNotSameAs(NOTEXPECTED);
            }
        }

        // When comparing arrays we use isEqualTo()
        @Nested
        @DisplayName("When comparing two arrays")
        @Tag("array")
        class TwoArrays {

            final int[] ACTUAL = new int[]{2, 3, 4};
            final int[] EXPECTED = new int[]{2, 3, 4};
            final int[] NOTEXPECTED = new int[]{2, 3, 5};

            @Test
            @DisplayName("Should contain same items")
            void shouldContainSameItems() {
                assertThat(ACTUAL).isEqualTo(EXPECTED);
            }

            @Test
            @DisplayName("Should not contain same items")
            void shouldNotContainSameItems() {
                assertThat(ACTUAL).isNotEqualTo(NOTEXPECTED);
            }
        }
    }

    @Nested
    @DisplayName("Testing iterables")
    @Tag("list")
    class TwoIterables {

        private Object first, second, third;
        private List<Object> list, mutualList;

        @BeforeEach
        void createAndInitializeList() {
            first = new Object();
            second = new Object();
            // third item won't be added to list because we use it with doesNotContain() method
            third = new Object();

            list = Arrays.asList(first, second);
            // We copy the list to compare iterables with isEqualTo() method
            mutualList = list;
        }

        @Test
        @DisplayName("Should contain two elements")
        void shouldContainTwoElements() {
            assertThat(list).hasSize(2);
        }

        @Test
        @DisplayName("Contains elements in given order")
        void shouldContainElementsInGivenOrder() {
            /* This test will fail if elements are in different order
               To check that it contains elements in any order we should use
               containsExactlyInAnyOrder() method. */
            assertThat(list).containsExactly(first, second);
        }

        @Test
        @DisplayName("Should contain the correct element only once")
        void shouldContainCorrectElementOnce() {
            assertThat(list).containsOnlyOnce(first);
        }

        @Test
        @DisplayName("Should not contain incorrect element")
        void shouldNotContainIncorrectElement() {
            assertThat(list).doesNotContain(third);
        }

        // This method is to verify that two iterables are 100% same
        @Test
        @DisplayName("Should contain same elements")
        void shouldContainSameElements() {
            // To check not equal use isNotEqualTo
            assertThat(list).isEqualTo(mutualList);
        }
    }

    @Nested
    @DisplayName("Assertions for Maps")
    @Tag("map")
    class MapAssertions {

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
        @DisplayName("Should contain the correct key")
        void shouldContainCorrectKey() {
            assertThat(map).containsKey(KEY);
        }

        @Test
        @DisplayName("Should not contain the incorrect key")
        void shouldNotContainIncorrectKey() {
            assertThat(map).doesNotContainKey(INCORRECT_KEY);
        }

        @Test
        @DisplayName("Should contain the given entry")
        void shouldContainGivenEntry() {
            assertThat(map).containsEntry(KEY, VALUE);
        }

        @Test
        @DisplayName("Should not contain the given entry")
        void shouldNotContainGivenEntry() {
            assertThat(map).doesNotContainEntry(INCORRECT_KEY, VALUE);
        }
    }

    @Nested
    @DisplayName("Assertion for Exceptions")
    class ExceptionAssertions {

        // To verify that thrown exception is the one we expected
        @Test
        @DisplayName("Should throw the correct exception")
        void shouldThrowCorrectException() {
            /* We can also use final Throwable to catch throwable and save it to object with catchThrowable()
               That way we can use assertThat() instead of assertThatThrownBy()
               just write assertThat(thrown).isExactlyInstanceOf(NullPointerException.class) */
            assertThatThrownBy(() -> { throw new NullPointerException(); })
                    .isExactlyInstanceOf(NullPointerException.class);
        }


        // To throw exception with expected message
        @Test
        @DisplayName("Should throw an exception that has the correct message")
        void shouldThrowExceptionWithCorrectMessage() {
            /* In this case you could also use Throwable object with catchThrowable()
               Then we use assertThat(thrown.getMessage()).isEqualTo()
             */
            assertThatThrownBy(() -> {
                throw new NullPointerException("This is exception.");
            }).hasMessage("This is exception.");
        }
    }

    @Nested
    @DisplayName("Assertion for Optionals")
    class OptionalAssertion {

        private final Object OBJECT = new Object();

        @Test
        @DisplayName("Should be empty")
        void shouldBeEmpty() {
            assertThat(Optional.empty()).isEmpty();
        }

        @Test
        @DisplayName("Should not be empty")
        void shouldNotBeEmpty() {
            assertThat(Optional.of(OBJECT)).isNotEmpty();
        }

        @Test
        @DisplayName("Should contain correct object")
        void shouldContainCorrectObject() {
            assertThat(Optional.of(OBJECT)).contains(OBJECT);
        }
    }

}
