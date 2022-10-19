package wi.pb.zio.tsp.simple;

import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@ToString
public class ClassificationData {
    private final static int CLASSIFIED_TRUE = 0;
    private final static int CLASSIFIED_FALSE = 1;

    private int objectsSize;
    private int factsSize;
    private final List<Measure> measures = new ArrayList<>();

    public void addMeasure(List<String> measure) {
        this.factsSize++;
        if (this.objectsSize == 0) {
            this.objectsSize = (measure.size() - 1) / 2;
        }

        Measure newMeasure = new Measure(measure.get(0));
        List<List<String>> classifiedMeasurements = new ArrayList<>(measure.stream()
                .skip(1)
                .collect(Collectors.partitioningBy(s -> measure.indexOf(s) > objectsSize))
                .values());

        classifiedMeasurements.get(CLASSIFIED_TRUE)
                .stream()
                .mapToDouble(Double::parseDouble)
                .forEach(newMeasure::addClassifiedTrue);
        classifiedMeasurements.get(CLASSIFIED_FALSE)
                .stream()
                .mapToDouble(Double::parseDouble)
                .forEach(newMeasure::addClassifiedFalse);

        measures.add(newMeasure);
    }


    @Getter
    @ToString
    static class Measure {
        private final String fact;
        private final List<Double> classifiedTrueList = new ArrayList<>();
        private final List<Double> classifiedFalseList = new ArrayList<>();

        Measure(String fact) {
            this.fact = fact;
        }

        void addClassifiedTrue(Double val) {
            classifiedTrueList.add(val);
        }

        void addClassifiedFalse(Double val) {
            classifiedFalseList.add(val);
        }
    }
}
