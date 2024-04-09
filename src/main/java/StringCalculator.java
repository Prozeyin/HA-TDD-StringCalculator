import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StringCalculator {

    public int add(String input) {
        // return 0 if empty string
        if(input.isEmpty()) {
            return 0;
        }

        String delimiter = ",";
        String numbersPart = input;

        if (input.startsWith("//")) {

            int newlineIndex = input.indexOf('\n', 2);
            int commaIndex = input.indexOf(',', 2);
            int endOfDelimiterIndex;

            if (newlineIndex != -1 && (commaIndex == -1 || newlineIndex < commaIndex)) {
                endOfDelimiterIndex = newlineIndex;
            } else if (commaIndex != -1) {
                endOfDelimiterIndex = commaIndex;
            } else {
                // If neither is found
                endOfDelimiterIndex = input.length();
            }

            delimiter = input.substring(2, endOfDelimiterIndex);
            numbersPart = input.substring(endOfDelimiterIndex + 1);
        }

        // Replace all delimiters with a comma
        numbersPart = numbersPart.replace("\n", delimiter).replace(delimiter, ",");
        String[] numbers = numbersPart.split(",");

        List<Integer> negativeNumbers = new ArrayList<>();
        int sum = 0;

        for(String numberString: numbers) {
            int number = Integer.parseInt(numberString.trim());
            if(number < 0) {
                negativeNumbers.add(number);
            }
            sum += number;
        }
        if(!negativeNumbers.isEmpty()) {
            throw new IllegalArgumentException("Negatives not allowed: " + negativeNumbers);
        }
        return sum;
    }
}

