package calculator.objectDefinitions;

import calculator.operations.UnitMeasures;
import calculator.operations.ValidationException;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;


public class Calculator implements ICalculator {
    private String expression;
    private UnitMeasures resultUnitMeasure;
    private Distance distance;
    private Duration duration;

    public Calculator(String expression, UnitMeasures resultUnitMeasure) {
        this.expression = expression;
        this.resultUnitMeasure = resultUnitMeasure;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public UnitMeasures getResultUnitMeasure() {
        return resultUnitMeasure;
    }

    public void setResultUnitMeasure(UnitMeasures resultUnitMeasure) {
        this.resultUnitMeasure = resultUnitMeasure;
    }

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    CalculationDurationRepository<Calculator> calculationDuration;

    public double calculateDistance() throws ValidationException {
        CalculationDurationRepository calculationDuration=new CalculationDurationRepository<>();
        Instant start=Instant.now();
        double calculatedDistance = 0;
        List<Distance> listOfDistances = new ArrayList<>();
        listOfDistances = breakUpExpressionInDistanceData(listOfDistances);
        calculatedDistance += listOfDistances.get(0).getDistanceValue();
        List<String> stringOperators = getOperators(expression);
        for (int i = 0; i < stringOperators.size(); i++) {
            switch (stringOperators.get(i)) {
                case "+":
                    calculatedDistance += listOfDistances.get(i + 1).getDistanceValue();
                    break;
                case "-":
                    calculatedDistance -= listOfDistances.get(i + 1).getDistanceValue();
                    break;
            }
        }
        if (calculatedDistance < 0) {
            throw new ValidationException("Resulting distance negative!");
        }
        Instant stop=Instant.now();
        Duration durationOfCalculation= Duration.between(start,stop);
        setDuration(durationOfCalculation);
        calculationDuration.addDuration(getDuration());
        return calculatedDistance;
    }

    private double transformDistancesToResultUM(String unitMeasureToBeTransformed, double doubleDistance) throws ValidationException {
        switch (resultUnitMeasure) {
            case MM:
                doubleDistance = transformIntoMm(unitMeasureToBeTransformed, doubleDistance);
                break;
            case CM:
                doubleDistance = transformIntoCm(unitMeasureToBeTransformed, doubleDistance);
                break;
            case DM:
                doubleDistance = transformIntoDm(unitMeasureToBeTransformed, doubleDistance);
                break;
            case M:
                doubleDistance = transformIntoM(unitMeasureToBeTransformed, doubleDistance);
                break;
            case KM:
                doubleDistance = transformIntoKM(unitMeasureToBeTransformed, doubleDistance);
                break;
            default:
                throw new ValidationException("UnitMeasure not valid!");

        }
        return doubleDistance;
    }

    private double transformIntoKM(String unitMeasureToBeTransformed, double doubleDistance) throws ValidationException {
        switch (unitMeasureToBeTransformed) {
            case "mm":
                doubleDistance /= 1000000;
                break;
            case "cm":
                doubleDistance /= 100000;
                break;
            case "dm":
                doubleDistance /= 10000;
                break;
            case "m":
                doubleDistance /= 1000;
                break;
            case "km":
                break;
            default:
                throw new ValidationException("Unit measure not valid format (mm,cm,dm,m,km");
        }
        return doubleDistance;
    }

    private double transformIntoM(String unitMeasureToBeTransformed, double doubleDistance) throws ValidationException {
        switch (unitMeasureToBeTransformed) {
            case "mm":
                doubleDistance /= 1000;
                break;
            case "cm":
                doubleDistance /= 100;
                break;
            case "dm":
                doubleDistance /= 10;
                break;
            case "m":
                break;
            case "km":
                doubleDistance *= 1000;
                break;
            default:
                throw new ValidationException("Unit measure not valid format (mm,cm,dm,m,km");
        }
        return doubleDistance;
    }

    private double transformIntoDm(String unitMeasureToBeTransformed, double doubleDistance) throws ValidationException {
        switch (unitMeasureToBeTransformed) {
            case "mm":
                doubleDistance /= 100;
                break;
            case "cm":
                doubleDistance /= 10;
                break;
            case "dm":
                break;
            case "m":
                doubleDistance *= 10;
                break;
            case "km":
                doubleDistance *= 10000;
                break;
            default:
                throw new ValidationException("Unit measure not valid format (mm,cm,dm,m,km");
        }
        return doubleDistance;
    }

    private double transformIntoCm(String unitMeasureToBeTransformed, double doubleDistance) throws ValidationException {
        switch (unitMeasureToBeTransformed) {
            case "mm":
                doubleDistance /= 10;
                break;
            case "cm":
                break;
            case "dm":
                doubleDistance *= 10;
                break;
            case "m":
                doubleDistance *= 100;
                break;
            case "km":
                doubleDistance *= 1000000;
                break;
            default:
                throw new ValidationException("Unit measure not valid format (mm,cm,dm,m,km");
        }
        return doubleDistance;
    }

    private double transformIntoMm(String unitMeasureToBeTransformed, double doubleDistance) throws ValidationException {
        switch (unitMeasureToBeTransformed) {
            case "mm":
                break;
            case "cm":
                doubleDistance *= 10;
                break;
            case "dm":
                doubleDistance *= 100;
                break;
            case "m":
                doubleDistance *= 1000;
                break;
            case "km":
                doubleDistance *= 1000000;
                break;
            default:
                throw new ValidationException("Unit measure not valid format (mm,cm,dm,m,km");
        }
        return doubleDistance;
    }


    private List<Distance> breakUpExpressionInDistanceData(List<Distance> listOfDistances) throws ValidationException {
        List<Double> doubleDistance = getDistances(expression);
        List<String> stringUnitMeasure = getUnitMeasures(expression);
        System.out.println("-----------------------------------------------------------");
        System.out.println("The introduced distances are: ");
        for (int i = 0; i < doubleDistance.size(); i++) {
            System.out.print(doubleDistance.get(i) + " ");
            System.out.println(stringUnitMeasure.get(i));
        }
        System.out.println("-----------------------------------------------------------");
        for (int i = 0; i < doubleDistance.size(); i++) {
            distance = new Distance(transformDistancesToResultUM(stringUnitMeasure.get(i), doubleDistance.get(i)), resultUnitMeasure);
            listOfDistances.add(distance);
        }
        return listOfDistances;
    }

    private List<Double> getDistances(String expression) throws ValidationException {
        List<Double> doubleDistance = new ArrayList<>();
        String separator = "[cmk+\\-]+";
        String[] stringDistances = expression.split(separator);
        for (int i = 0; i < stringDistances.length; i++) {
            if (!stringDistances[i].equals("")) {
                doubleDistance.add(Double.parseDouble(stringDistances[i]));
            }
        }
        return doubleDistance;
    }

    private List<String> getUnitMeasures(String expression) {
        List<String> stringUnitMeasures = new ArrayList<>();
        String separator = "[1234567890+\\-]+";
        String[] string = expression.split(separator);
        for (int i = 0; i < string.length; i++) {
            if (!string[i].equals("")) {
                stringUnitMeasures.add(string[i]);
            }
            ;
        }
        return stringUnitMeasures;
    }

    private List<String> getOperators(String expression) {
        List<String> stringOperators = new ArrayList<>();
        String separator = "[1234567890mcdk]+";
        String[] string = expression.split(separator);
        for (int i = 0; i < string.length; i++) {
            if (!string[i].equals("")) {
                stringOperators.add(string[i]);
            }
            ;
        }
        return stringOperators;
    }
}
