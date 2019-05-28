package services.enums;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public enum DataIndexes {

    YEAR_BEGIN(0),
    YEAR_END(2),
    MONTH_BEGIN(2),
    MONTH_END(4),
    DAY_BEGIN(4),
    DAY_END(6),
    GENDER_BEGIN(9),
    GENDER_END(10),
    CONTROL_DIGIT_BEGIN(10),
    CONTROL_DIGIT_END(11);

    private int index;

    DataIndexes(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
