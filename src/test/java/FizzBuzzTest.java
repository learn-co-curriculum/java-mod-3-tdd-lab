import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FizzBuzzTest {

    FizzBuzz fizzBuzz;

    @BeforeEach
    void setUp() {
        fizzBuzz = new FizzBuzz();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testElse() {
        assertEquals("7", fizzBuzz.fizzBuzzString(7));
    }

    @Test
    void testFizz() {
        assertEquals("Fizz", fizzBuzz.fizzBuzzString(3));
    }

    @Test
    void testBuzz() {
        assertEquals("Buzz", fizzBuzz.fizzBuzzString(5));
    }

    @Test
    void testFizzBuzz() {
        assertEquals("FizzBuzz", fizzBuzz.fizzBuzzString(15));
    }
}