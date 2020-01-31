import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/* Parameterized tests makes it possible to run test multiple times with different arguments.
   You must declare at least one source each time you use parameterized test.
   Additional arguments are added with ParameterResolver.

   Declare 0 - * indexed arguments first.
   Declare 0 - * aggregators next.
   Declare 0 - * arguments with ParameterResolver last.
 */

@DisplayName("Learning parameterized testing")
public class ParameterizedTestExamples {

    /* Parameter in ValueSource can be String, int, long or double */
    @DisplayName("Should pass a non-null to our test method")
    @ParameterizedTest(name = "{index} => message=''{0}")
    @ValueSource(strings = {"Hello", "World"})
    void shouldPassNonNullMessage(String message) {
        assertNotNull(message);
    }

    /* EnumSource is used when we want to test from enum file
       First we set value to the enum class and then we can specify,
       what we want to get from that class, like CAT for example.
    */
    @DisplayName("Should pass non-null enum values")
    @ParameterizedTest(name = "{index} => pet=''{0}")
    @EnumSource(value = Pet.class, names = {"CAT"})
    void shouldPassNonNullEnum(Pet pet) {
        assertNotNull(pet);
    }

    /* For Csv we can use @CsvSource or @CsvFileSource.
       One String object must contain all params for one method invocation
       and different methods are separated with a comma.
       Values must be in same order as in the test method.
     */
    @DisplayName("Should calculate the correct sum from csv file")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
    @CsvFileSource(resources = "/test-data.csv")
    void shouldCalculateCorrectSum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    /* To use Factory Method we need to have a static class for our test.
       It must return Stream, Iterable, Iterator or an array of Arguments.
     */
    @DisplayName("Should calculate the correct sum from method source")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
    @MethodSource("sumProvider") // Using stream as a method source
    void methodSourceShouldCalculateCorrectSum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    // Must be static only if we use default lifecycle configuration
    private static Stream<Arguments> sumProvider() {
        return Stream.of(
                Arguments.of(1, 1, 2),
                Arguments.of(2, 3, 5)
        );
    }

    /* Another example of Method Parameters by using Argument Source.
       Arguments object must contain all method parameters for single test.
     */
    @DisplayName("Should calculate the correct sum from argument source")
    @ParameterizedTest(name = "{index} => a={0}, b={1}, c={2}")
    @ArgumentsSource(CustomArgumentProvider.class)
    void argumentSourceShouldCalculateCorrectSum(int a, int b, int sum) {
        assertEquals(sum, a + b);
    }

    /* If we already have ready code we want to test or code is complex one
       and we don't want to add it to the test class, we can use ArgumentProvider
     */
    static class CustomArgumentProvider implements ArgumentsProvider {

        @Override
        public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
            return Stream.of(
                    Arguments.of(1, 1, 2),
                    Arguments.of(2, 3, 5)
            );
        }
    }


}
