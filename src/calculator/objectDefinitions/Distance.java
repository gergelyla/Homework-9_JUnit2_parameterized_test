package calculator.objectDefinitions;

import calculator.operations.UnitMeasures;

public class Distance {
    private double distanceValue;
    private UnitMeasures distanceUnitMeasure;

    public Distance() {
    }

    public Distance(double distanceValue, UnitMeasures distanceUnitMeasure) {
        this.distanceValue = distanceValue;
        this.distanceUnitMeasure = distanceUnitMeasure;
    }

    public double getDistanceValue() {
        return distanceValue;
    }

    public void setDistanceValue(double distanceValue) {
        this.distanceValue = distanceValue;
    }

    public UnitMeasures getDistanceUnitMeasure() {
        return distanceUnitMeasure;
    }

    public void setDistanceUnitMeasure(UnitMeasures distanceUnitMeasure) {
        this.distanceUnitMeasure = distanceUnitMeasure;
    }

    @Override
    public String toString() {
        return "Distance: " +
                distanceValue +
                " " + distanceUnitMeasure;
    }
}
