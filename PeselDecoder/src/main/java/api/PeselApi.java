package api;

import models.Gender;
import models.Person;
import services.enums.Century;
import services.DateOfBirthService;
import services.GenderService;
import services.PeselService;

import java.time.LocalDate;
import java.time.Month;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public class PeselApi {

    private PeselService peselService = new PeselService();
    private DateOfBirthService dateOfBirthService = new DateOfBirthService();
    private GenderService genderService = new GenderService();

    public boolean isPeselCorrect(String pesel) {
        return peselService.isFormatCorrect(pesel) && peselService.isControlDigitCorrect(pesel);
    }


    public Person createPersonFromPesel(String pesel) {

        int digitsOfDay = peselService.getDigitsOfDay(pesel);
        int digitsOfMonth = peselService.getDigitsOfMonth(pesel);
        int lastTwoDigitsOfYear = peselService.getLastTwoDigitsOfYear(pesel);
        int digitOfGender = peselService.getDigitOfGender(pesel);

        Century centuryOfBirth =
                dateOfBirthService.getCenturyOfBirthFromDigitsOfMonth(digitsOfMonth);

        int dayOfBirth = dateOfBirthService.getDayOfBirthFromDigits(digitsOfDay);
        Month monthOfBirth =
                dateOfBirthService.getMonthOfBirthFromDigits(digitsOfMonth);
        int yearOfBirth =
                dateOfBirthService.getYearOfBirthFromCenturyAndDigits(centuryOfBirth, lastTwoDigitsOfYear);
        Gender gender = genderService.getGenderFromDigit(digitOfGender);

        return new Person.PersonBuilder()
                .setPesel(pesel)
                .setDateOfBirth(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth))
                .setGender(gender)
                .build();
    }

}
