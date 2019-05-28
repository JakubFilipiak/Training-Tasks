package services.enums;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public enum MonthByIds {

    JANUARY(new ArrayList<>(Arrays.asList(81, 1, 21, 41, 61))),
    FEBRUARY(new ArrayList<>(Arrays.asList(82, 2, 22, 42, 62))),
    MARCH(new ArrayList<>(Arrays.asList(83, 3, 23, 43, 63))),
    APRIL(new ArrayList<>(Arrays.asList(84, 4, 24, 44, 64))),
    MAY(new ArrayList<>(Arrays.asList(85, 5, 25, 45, 65))),
    JUNE(new ArrayList<>(Arrays.asList(86, 6, 26, 46, 66))),
    JULY(new ArrayList<>(Arrays.asList(87, 7, 27, 47, 67))),
    AUGUST(new ArrayList<>(Arrays.asList(88, 8, 28, 48, 68))),
    SEPTEMBER(new ArrayList<>(Arrays.asList(89, 9, 29, 49, 69))),
    OCTOBER(new ArrayList<>(Arrays.asList(90, 10, 30, 50, 70))),
    NOVEMBER(new ArrayList<>(Arrays.asList(91, 11, 31, 51, 71))),
    DECEMBER(new ArrayList<>(Arrays.asList(92, 12, 32, 52, 72)));

    private List<Integer> ids;

    MonthByIds(List<Integer> ids) {
        this.ids = ids;
    }

    public static Month getMonthFromId(int id) {

        for (MonthByIds monthByIds : MonthByIds.values()) {
            if (monthByIds.ids.contains(id))
                return Month.valueOf(monthByIds.toString());
        }
        return null;
    }
}
