package services.enums;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public enum Century {

    XIX(1800),
    XX(1900),
    XXI(2000),
    XXII(2100),
    XXIII(2200);

    private int startYear;

    Century(int startYear) {
        this.startYear = startYear;
    }

    public static Century fromDigitsOfMonth(int digits) {

        boolean isXIX = digits >= 81 && digits <= 92;
        boolean isXX = digits >= 1 && digits <= 12;
        boolean isXXI = digits >= 21 && digits <= 32;
        boolean isXXII = digits >= 41 && digits <= 52;
        boolean isXXIII = digits >= 61 && digits <= 72;

        if (isXIX)
            return XIX;
        if (isXX)
            return XX;
        if (isXXI)
            return XXI;
        if (isXXII)
            return XXII;
        if (isXXIII)
            return XXIII;
        return null;
    }

    public int getStartYear() {
        return startYear;
    }
}
