import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/* To write assertions for Exceptions we need to specify the type of
   expected exception.
   You can provide custom error message.
 */
@DisplayName("Assertions for system and errors")
public class ErrorAssertionExample {

    @Nested
    @DisplayName("Assertions for exceptions")
    class Exceptions {

        @Nested
        @DisplayName("Should throw an exception")
        class ShouldThrowAnException {

            @Test
            @DisplayName("Should throw the correct exception")
            void shouldThrowCorrectException() {
                assertThrows(
                        NullPointerException.class,
                        () -> { throw new NullPointerException(); }
                );
            }

            @Test
            @DisplayName("Should throw the correct exception with correct message")
            void shouldThrowCorrectExceptionWithCorrectMessage() {
                final NullPointerException thrown = assertThrows(
                        NullPointerException.class,
                        () -> { throw new NullPointerException("There was an exception"); }
                );
                assertEquals("There was an exception", thrown.getMessage());
            }
        }

        @Nested
        @DisplayName("Should not throw an exception")
        class ShouldNotThrowAnException {

            @Test
            @DisplayName("Should not throw an exception")
            void shouldNotThrowException() {
                assertDoesNotThrow(() -> {});
            }

            @Test
            @DisplayName("Should not throw an exception with message")
            void shouldNotThrowExceptionWithMessage() {
                String message = assertDoesNotThrow(() -> { return "There was an exception"; });
                assertEquals("There was an exception", message);
            }
        }

        /* If we want to make sure that execution of the system under test is completed
           before a specified timeout we can use assertTimeout() and
           assertTimeoutPreemptively() methods. You need to specify timout with Duration object,
           Executable/ThrowingSupplier to invoke the system and optional error message.
           Difference between these methods are that preemtively will abort execution if timeout exceeds.
         */
        @Nested
        @DisplayName("Should return message before timeout")
        class ShouldReturnMessageBeforeTimeout {

            @Test
            @DisplayName("Should return the correct message before timeout")
            void shouldReturnCorrectMessageBeforeTimeout() {
                final String message = assertTimeout(Duration.ofMillis(50), () -> {
                    Thread.sleep(20);
                    return "Just in time!";
                });
                assertEquals("Just in time!", message);
            }

            @Test
            @DisplayName("Should exceed before timeout")
            void shouldExceedBeforeTimeout() {
                final String message = assertTimeoutPreemptively(Duration.ofMillis(50), () -> {
                    Thread.sleep(20);
                    return "Exceeding before timeout";
                });
                assertEquals("Exceeding before timeout", message);
            }
        }
    }



}
