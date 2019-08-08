package calculator;

import calculator.objectDefinitions.Calculator;
import calculator.objectDefinitions.CalculationDurationRepository;
import calculator.operations.UnitMeasures;
import calculator.operations.ValidationException;
import org.junit.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.rules.ExpectedException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class testCalculator {

    private Calculator calculator;
    private CalculationDurationRepository<Calculator> calculationDurationRepository;
    private List<Duration> calculationDuration;

    @BeforeClass
    public static void beforeClass() {
        System.out.println("before class");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("after class");
    }

    @Before
    public void setup() {
        System.out.println("in setup");
        calculator = new Calculator("33m-12cm", UnitMeasures.M);
        calculationDurationRepository=new CalculationDurationRepository<>();
        calculationDuration=new ArrayList<>();
    }

    @After
    public void after() {
        System.out.println("after");
    }

    @Test
    public void testCalculateDistance() throws ValidationException {
        assertThat(calculator.calculateDistance(), is(32.88));
    }

    @Test(expected = ValidationException.class)
    public void testCalculateDistanceWhenResultNegative() throws ValidationException {
        calculator.setExpression("1m-200cm");
        calculator.calculateDistance();
        fail("resulting distance negative!");
    }

    @Test
    public void testCalculationDurationAdd()throws ValidationException{
        calculator.calculateDistance();
        calculationDurationRepository.addDuration(calculator.getDuration());
        assertThat(calculationDuration.size(),is(1));
    }

}
