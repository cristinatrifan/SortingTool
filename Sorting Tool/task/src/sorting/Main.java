package sorting;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Collections;

public class Main {
    private dataType Type;
    private String filew;

    public String getFilew() {
        return filew;
    }

    public void setFilew(String filew) {
        this.filew = filew;
    }

    public dataType getIsType() {
        return Type;
    }

    public void setIsType(dataType isLIne) {
        this.Type = isLIne;
    }

    private enum dataType {
        NUMBER("long"),
        LINE("line"),
        WORD("word");

        private String str;

        dataType(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return str;
        }
    }

    //natural sorting
    public void processSortIntegers(ArrayList input) throws IOException{
        if (getFilew() != null) {
            FileWriter file = new FileWriter(getFilew());

            switch (this.getIsType()) {
                case NUMBER:
                    file.write("Total numbers: " + input.size());
                    break;
                case LINE:
                    file.write("Total lines: " + input.size());
                    break;
                case WORD:
                    file.write("Total words: " + input.size());
            }
            file.write("Sorted data: ");
            for (int i = 0; i < input.size(); i++) {
                file.write(input.get(i) + " ");
            }

        }
        else {

            switch (this.getIsType()) {
                case NUMBER:
                    System.out.println("Total numbers: " + input.size());
                    break;
                case LINE:
                    System.out.println("Total lines: " + input.size());
                    break;
                case WORD:
                    System.out.println("Total words: " + input.size());
            }
            System.out.print("Sorted data: ");
            for (int i = 0; i < input.size(); i++) {
                System.out.print(input.get(i) + " ");
            }
        }
    }

    //byCount sorting
    public void processInput(ArrayList input) throws IOException {
        long numCount = 0;
        double percent = 0;
        ArrayList finalInput = input;
        Map<Long, Integer> mapInt = new LinkedHashMap<>();
        Map<String, Integer> mapStr = new LinkedHashMap<>();
        Map<Long, Integer> mapInt1 = new LinkedHashMap<>();
        Map<String, Integer> mapStr1 = new LinkedHashMap<>();

        switch (this.getIsType()) {
            case NUMBER:
                for (int i = 0; i < input.size(); i++) {
                    if (mapInt.containsKey(input.get(i))) {
                        mapInt.put((long) input.get(i), mapInt.get(input.get(i)) + 1);
                    } else {
                        mapInt.put((long) input.get(i), 1);
                    }
                }

                mapInt.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEachOrdered(x -> mapInt1.put(x.getKey(), x.getValue()));

                if (getFilew() != null) {
                    FileWriter file = new FileWriter(getFilew());
                    file.write("Total numbers: " + input.size() + ".");
                    for (var i : mapInt1.entrySet()) {
                        file.write(i.getKey() +
                                ": " +
                                i.getValue() + " time(s), " + (i.getValue() * 100 / input.size()) + "%");
                    }

                } else {
                    System.out.println("Total numbers: " + input.size() + ".");
                    for (var i : mapInt1.entrySet()) {
                        System.out.println(i.getKey() +
                                ": " +
                                i.getValue() + " time(s), " + (i.getValue() * 100 / input.size()) + "%");
                    }
                }
                break;

            case WORD:

                for (int i = 0; i < input.size(); i++) {
                    if (mapStr.containsKey(input.get(i))) {
                        mapStr.put(input.get(i).toString(), mapStr.get(input.get(i)) + 1);
                    } else {
                        mapStr.put(input.get(i).toString(), 1);
                    }
                }

                mapStr.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEachOrdered(x -> mapStr1.put(x.getKey(), x.getValue()));

                if (getFilew() != null) {
                    FileWriter file = new FileWriter(getFilew());

                    file.write("Total words: " + input.size() + ".");
                    for (var i : mapStr1.entrySet()) {
                        file.write(i.getKey() +
                                ": " +
                                i.getValue() + " time(s), " + (i.getValue() * 100 / input.size()) + "%");
                    }
                }
                else {
                    System.out.println("Total words: " + input.size() + ".");
                    for (var i : mapStr1.entrySet()) {
                        System.out.println(i.getKey() +
                                ": " +
                                i.getValue() + " time(s), " + (i.getValue() * 100 / input.size()) + "%");
                    }
                }
                break;

            case LINE:

                for (int i = 0; i < input.size(); i++) {
                    if (mapStr.containsKey(input.get(i))) {
                        mapStr.put(input.get(i).toString(), mapStr.get(input.get(i)) + 1);
                    } else {
                        mapStr.put(input.get(i).toString(), 1);
                    }
                }

                mapStr.entrySet().stream()
                        .sorted(Map.Entry.comparingByValue())
                        .forEachOrdered(x -> mapStr1.put(x.getKey(), x.getValue()));

                if (getFilew() != null) {
                    FileWriter file = new FileWriter(getFilew());

                    file.write("Total lines: " + input.size() + ".");
                    for (var i : mapStr1.entrySet()) {
                        file.write(i.getKey() +
                                ": " +
                                i.getValue() + " time(s), " + (i.getValue() * 100 / input.size()) + "%");
                    }
                }
                else {
                    System.out.println("Total lines: " + input.size());
                    for (var i : mapStr1.entrySet()) {
                        System.out.println(i.getKey() +
                                ": " +
                                i.getValue() + " time(s), " + (i.getValue() * 100 / input.size()) + "%");
                    }
                }
        }
    }

    public void handleExc(String[] args) {
        List<String> list = Arrays.asList(args);
        Integer index = list.indexOf("-sortingType");

        if (index != -1){
            if (list.size() > index + 1){
                if (!list.get(index + 1).equals("natural")
                        && !list.get(index + 1).equals("byCount")) {
                    System.out.println("No sorting type defined!");
                }
            } else {
                System.out.println("No sorting type defined!");
            }
        }

        index = list.indexOf("-dataType");
        if (index != -1){
            if (list.size() > index + 1){
                if (!list.get(index + 1).equals(dataType.NUMBER.toString())
                        && !list.get(index + 1).equals(dataType.LINE.toString())
                        && !list.get(index + 1).equals(dataType.WORD.toString())) {
                    System.out.println("No data type defined!");
                }
            } else {
                System.out.println("No data type defined!");
            }
        }

        if (list.size() > 8) {
            for (int i = 8; i < list.size(); i++){
                System.out.println(list.get(i) + "isn't a valid parameter. It's skipped.");
            }
        }
    }

    public static void main(final String[] args) {

        ArrayList<Long> numbers = new ArrayList<Long>();
        ArrayList<String> chars = new ArrayList<String>();
        Main myObj = new Main();

        Scanner scanner = createScanner(args);

        myObj.handleExc(args);

        try {
            if (Arrays.asList(args).contains("-outputFile")) {
                String file = Arrays.asList(args)
                                .get(Arrays.asList(args)
                                .indexOf("-outputFile") + 1);
                myObj.setFilew(file);
            }

        if (Arrays.asList(args).contains(dataType.NUMBER.toString())) {

            while (scanner.hasNext()) {
                String elem = scanner.next();
                try {
                    Long number = Long.parseLong(elem);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.out.println(elem + " isn't a long. It's skipped.");
                }

            }
            myObj.setIsType(dataType.NUMBER);
            Collections.sort(numbers);

            if (Arrays.asList(args).contains("byCount")) {
                myObj.processInput(numbers);
            } else {
                myObj.processSortIntegers(numbers);
            }

        } else if (Arrays.asList(args).contains(dataType.LINE.toString())) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                chars.add(line);
            }
            myObj.setIsType(dataType.LINE);
            chars = chars.stream().sorted((o1, o2) -> {
                return o1.toString().compareTo(o2.toString());
            }).collect(Collectors.toCollection(ArrayList::new));

            if (Arrays.asList(args).contains("byCount")) {
                myObj.processInput(chars);
            } else {
                myObj.processSortIntegers(chars);
            }

//        } else if (Arrays.asList(args).contains(dataType.WORD.toString())) {
        } else {

            while (scanner.hasNext()) {
                String line = scanner.next();

                chars.add(line);
            }
            myObj.setIsType(dataType.WORD);
            chars = chars.stream().sorted((o1, o2) -> {
                return o1.toString().compareTo(o2.toString());
            }).collect(Collectors.toCollection(ArrayList::new));

            if (Arrays.asList(args).contains("byCount")) {
                myObj.processInput(chars);
            } else {
                myObj.processSortIntegers(chars);
            }

        }
    }
        catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }

    private static Scanner createScanner(String[] args) {
        if ((Arrays.asList(args).contains("-inputFile"))) {
            Scanner scanner = new Scanner(Arrays.asList(args)
                            .get(Arrays.asList(args)
                            .indexOf("-inputFile")) + 1);
            return scanner;
        }
        Scanner scanner = new Scanner(System.in);
        return scanner;

    }
}


