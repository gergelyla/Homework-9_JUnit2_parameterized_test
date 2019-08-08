package calculator.objectDefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CalculationDurationRepository <T extends Calculator>{
    List<Duration> calculationDuration=new ArrayList<>();

    public void addDuration(Duration duration){
        calculationDuration.add(duration);
        for(Duration member:calculationDuration){
            System.out.println("Calculation duration: "+member);
        }
    }
}
