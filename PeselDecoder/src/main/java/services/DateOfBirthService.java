package services;

import services.enums.Century;
import services.enums.MonthByIds;

import java.time.Month;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public class DateOfBirthService {

    public int getDayOfBirthFromDigits(int digits) {

        return digits;
    }

    public Month getMonthOfBirthFromDigits(int digits) {

        return MonthByIds.getMonthFromId(digits);
    }

    public Century getCenturyOfBirthFromDigitsOfMonth(int digits) {

        return Century.fromDigitsOfMonth(digits);
    }

    public int getYearOfBirthFromCenturyAndDigits(Century century, int digits) {

        return century.getStartYear() + digits;
    }
}
