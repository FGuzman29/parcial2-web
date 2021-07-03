package eitc.pucmm;



import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){


    }

    public static String codeGenerator() {
        int[] arr = {58, 59, 60, 61, 62, 63, 64, 91, 92, 93, 94, 95, 96};
        int leftLimit = 48; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            if (!IntStream.of(arr).anyMatch(n -> n == randomLimitedInt)) {
                buffer.append((char) randomLimitedInt);
            } else {
                i--;
            }
        }
        String generatedString = buffer.toString();

        return generatedString;
    }

}