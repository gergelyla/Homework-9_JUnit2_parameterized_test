package calculator.objectDefinitions;

import calculator.operations.ValidationException;

public interface ICalculator {
    double calculateDistance() throws ValidationException;
}
