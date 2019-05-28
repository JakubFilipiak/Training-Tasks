package services;

import services.enums.DataIndexes;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public class PeselService {

    private static final Logger LOGGER = Logger.getLogger(PeselService.class.getName());

    public boolean isFormatCorrect(String pesel) {

        boolean formatCorrect = pesel.matches("[0-9]{11}");
        if (!formatCorrect)
            LOGGER.log(Level.WARNING, "Incorrect format!");
        return formatCorrect;
    }

    public boolean isControlDigitCorrect(String pesel) {

        int controlDigit = getControlDigit(pesel);
        int expectedControlDigit = calculateControlDigit(pesel);
        boolean controlDigitCorrect = controlDigit == expectedControlDigit;
        if (!controlDigitCorrect)
            LOGGER.log(Level.WARNING, "Incorrect control digit! \n"
                + "current: " + controlDigit + ", correct: " + expectedControlDigit);
        return controlDigitCorrect;
    }

    private int calculateControlDigit(String pesel) {

        int[] multiplication = new int[] {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int[] digitBase = new int[multiplication.length];
        int[] products = new int[multiplication.length];
        int sumOfUnitParts = 0;

        for (int i = 0; i < multiplication.length; i++) {
            digitBase[i] = Integer.parseInt(pesel.substring(i, i + 1));
        }

        for (int i = 0; i < multiplication.length; i++) {
            int product = digitBase[i] * multiplication[i];
            products[i] = leaveOnlyUnitPart(product);
        }

        for (int product : products) {
            sumOfUnitParts += product;
        }

        return 10 - leaveOnlyUnitPart(sumOfUnitParts);
    }

    private int leaveOnlyUnitPart(int number) {

        return number % 10;
    }

    // Get raw data from pesel

    public int getLastTwoDigitsOfYear(String pesel) {
        return Integer.parseInt(pesel.substring(DataIndexes.YEAR_BEGIN.getIndex(),
                DataIndexes.YEAR_END.getIndex()));
    }

    public int getDigitsOfMonth(String pesel) {
        return Integer.parseInt(pesel.substring(DataIndexes.MONTH_BEGIN.getIndex()
                , DataIndexes.MONTH_END.getIndex()));
    }

    public int getDigitsOfDay(String pesel) {
        return Integer.parseInt(pesel.substring(DataIndexes.DAY_BEGIN.getIndex(),
                DataIndexes.DAY_END.getIndex()));
    }

    public int getDigitOfGender(String pesel) {
        return Integer.parseInt(pesel.substring(DataIndexes.GENDER_BEGIN.getIndex(), DataIndexes.GENDER_END.getIndex()));
    }

    private int getControlDigit(String pesel) {
        return Integer.parseInt(pesel.substring(DataIndexes.CONTROL_DIGIT_BEGIN.getIndex(), DataIndexes.CONTROL_DIGIT_END.getIndex()));
    }
}
