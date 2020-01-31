import org.junit.jupiter.api.*;

/* We use annotations @ to tell our program that we are using JUnit test methods.
 Always use void method because test shouldn't return anything
 and it can't be private or static. Test classes can't be abstract.
 BeforeAll and afterAll should be static or you get compile error.
 If you want to specify executin order, use @Order(number) annotation
 */

/* Using display names makes our test more readable and easy to understand */
@DisplayName("JUnit 5 Example")
public class JUnit5Basics {

    /* Everything marked with before all will be executed before any other code.
       This could be useful when you need to to basic setup or init before tests
     */
    @BeforeAll
    static void beforeAll() {
        System.out.println("This will occur before all test methods.");
    }

    /* Before each will be executed before each test in our test file.
       It's useful with object creations when you want that object to return
       to start point each time you run a test.
     */
    @BeforeEach
    void beforeEach() {
        System.out.println("This will occur before each test method.");
    }

    /* After each will be executed after each test in our code. */
    @AfterEach
    void afterEach() {
        System.out.println("This will occur after each test method.");
    }

    /* After all will be executed after everything else, it's kind of ending method */
    @AfterAll
    static void afterAll() {
        System.out.println("This will occur after all test methods.");
    }

    /* Non-static inner classes. No limit with depth of hierarchy.
       It's possible to use each methods inside nested class, but not all methods.
       Nested grouping gives us more capabilities to express relationship among the tests
       inside nested group. For example those could be only for testing list methods.

       Using "root - inner class - conditional class" hierarchy will make our tests more effective.
     */
    @Nested
    @DisplayName("Tests for the method A")
    @Tag("objects") // Tags are used to filter tests
    class A {

        @BeforeEach
        void beforeEach() {
            System.out.println("This will occur before each test method of the A class.");
        }

        @AfterEach
        void afterEach() {
            System.out.println("This will occur after each test method of the A class.");
        }

        /* */
        @Test
        @DisplayName("Example test for method A")
        void testA() {
            System.out.println("Example test A");
        }

        @Nested
        @DisplayName("Tests for the method C")
        class C {

            @BeforeEach
            void beforeEach() {
                System.out.println("This will occur before each test method of the C class.");
            }

            @AfterEach
            void afterEach() {
                System.out.println("This will occur after each test method of the C class.");
            }

            @Test
            @DisplayName("Example test for method C")
            void testA() {
                System.out.println("Example test C");
            }

        }

    }

    @Test
    @DisplayName("Example test B outside of class A")
    void testB() {
        System.out.println("Example text B");
    }

    /*@Test
    @DisplayName("First test")
    void firstTest() {
        System.out.println("This is first test.");
    }

    @Test
    @DisplayName("Second Test")
    void secondTest() {
        System.out.println("This is second test");
    }*/

}
