package calculator;

import calculator.objectDefinitions.Calculator;
import calculator.operations.UnitMeasures;
import calculator.operations.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

@RunWith(Parameterized.class)
public class parameterizedTestCalculator {

    private static Calculator calculator /* = new Calculator()*/;

    private String expression;
    private UnitMeasures unitMeasures;
    private double expected;

    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[][]{
                {"33m-12cm", UnitMeasures.M, 32.88},
                {"10m+30dm-20cm+1km", UnitMeasures.M, 1012.8},
                {"2m-20cm+10dm", UnitMeasures.CM, 280}
        });
    }

    public parameterizedTestCalculator(String expression, UnitMeasures unitMeasures, double expected) {
        this.expression = expression;
        this.unitMeasures = unitMeasures;
        this.expected = expected;
    }

    @Test
    public void testDistanceCalculator() throws ValidationException {
        calculator = new Calculator(expression, unitMeasures);

        assertEquals(calculator.calculateDistance(), expected);
    }
}
