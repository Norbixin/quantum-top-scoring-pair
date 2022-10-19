package wi.pb.zio.tsp.simple;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.Reader;

public class Main {
    private final static int BIGGER_FACT = 0;
    private final static int SMALLER_FACT = 1;
    private static String[] classifierFacts = new String[2];

    public static void main(String[] args) throws Exception {
        String file_path;
        if (args.length == 1) {
            file_path = args[0];
        } else {
            throw new Exception("You have to pass file path as argument");
        }

        ClassificationData data = new ClassificationData();

        Reader in = new FileReader(file_path);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT
                .builder()
                .setDelimiter(";")
                .build()
                .parse(in);
        for (CSVRecord record : records) {
            data.addMeasure(record.toList());
        }

        double Pmax = 0;

        for (int i=0; i<data.getFactsSize() - 1; i++) {
            for (int j=i+1; j<data.getFactsSize(); j++) {
                int Psum = 0;
                for (int k=0; k<data.getObjectsSize(); k++) {
                    if (data.getMeasures().get(i).getClassifiedTrueList().get(k) >
                            data.getMeasures().get(j).getClassifiedTrueList().get(k)) {
                        Psum++;
                    }
                }
                double P1 = (double)Psum / data.getObjectsSize();

                Psum = 0;
                for (int k=0; k<data.getObjectsSize(); k++) {
                    if (data.getMeasures().get(i).getClassifiedFalseList().get(k) <=
                            data.getMeasures().get(j).getClassifiedFalseList().get(k)) {
                        Psum++;
                    }
                }
                double P2 = (double)Psum / data.getObjectsSize();

                if (Pmax < P1 * P2) {
                    Pmax = P1 * P2;
                    classifierFacts[BIGGER_FACT] = data.getMeasures().get(i).getFact();
                    classifierFacts[SMALLER_FACT] = data.getMeasures().get(j).getFact();
                }
                if (Pmax < (1.0-P1) * (1.0-P2)) {
                    Pmax = (1.0-P1) * (1.0-P2);
                    classifierFacts[SMALLER_FACT] = data.getMeasures().get(i).getFact();
                    classifierFacts[BIGGER_FACT] = data.getMeasures().get(j).getFact();
                }
            }
        }
        System.out.println("Classifier: " + classifierFacts[BIGGER_FACT] + " > " + classifierFacts[SMALLER_FACT] +
                ", P=" + Pmax);
    }
}
