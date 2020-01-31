import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/* This example demonstrates custom ArgumentConverter.
   ArgumentConverter's only responsibility is to convert one source to another source.
   When conversion fails it should throw a ArgumentConversionException.
   This example will convert String to Message object.
 */
@DisplayName("Pass converted Message object")
public class ArgumentConverterExample {

    @DisplayName("Should pass same messages as method parameters")
    @ParameterizedTest(name = "{index} => actual={0}, expected={1}")
    @CsvSource({
            "Hello, Hello",
            "Hi, Hi",
    })
    void shouldPassMessage(Message actual, Message expected) {
        assertEquals(expected.getMessage(), actual.getMessage());
    }

    @DisplayName("Should pass same messages as method parameters v2")
    @ParameterizedTest(name = "{index} => actual={0}, expected={1}")
    @CsvSource({
            "Hello, Hello",
            "Hi, Hi",
    })
    void shouldPassMessage2(@ConvertWith(MessageConverter.class) Message actual,
                            @ConvertWith(MessageConverter.class) Message expected) {
        assertEquals(expected.getMessage(), actual.getMessage());
    }
}
