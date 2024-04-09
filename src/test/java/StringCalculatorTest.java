import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new StringCalculator();
    }

    @Test
    public void testEmptyStringReturnsZero() {
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    public void testAddOneInteger() { Assertions.assertEquals(1, calculator.add("1"));}

    @Test
    public void testAddTwoIntegers() { Assertions.assertEquals(3,calculator.add("1,2")); }

    @Test
    public void testAddMultipleIntegers() { Assertions.assertEquals(5, calculator.add("1,2,1,1")); }

    @Test
    public void testAddWithSpace() { Assertions.assertEquals(8, calculator.add("1, 2, 3, 2")); }

    @Test
    public void testAddWithNewLineBetweenNumbers() {
        Assertions.assertEquals(6, calculator.add("1\n2\n3"));
    }

    @Test
    public void testWithMixedDelimiters() {
        Assertions.assertEquals(6, calculator.add("1\n2,3"));
    }
    @Test
    public void testAddWithCustomSingleCharacterDelimiter() { Assertions.assertEquals(3, calculator.add("//;\n1;2")); }

    @Test
    public void  testAddWithCustomDelimiterOfMultipleCharacters() {
        Assertions.assertEquals(6, calculator.add("//***,1***2***3"));
    }

    @Test
    public void testAddWithDifferentCustomDelimiters() {
        Assertions.assertEquals(10, calculator.add("//abc\n1abc2,3\n4"));
    }

    @Test
    public void testAddWithNegativeNumbers() {
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            calculator.add("1,-2,3,-4");
        });
        Assertions.assertTrue(exception.getMessage().contains("Negatives not allowed: [-2, -4]"));
    }
}