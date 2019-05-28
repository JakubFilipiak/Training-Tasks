package services;

import models.Gender;

/**
 * Created by Jakub Filipiak on 28.05.2019.
 */
public class GenderService {

    public Gender getGenderFromDigit(int digit) {

        boolean parity = digit % 2 == 0;
        return Gender.fromParity(parity);
    }
}
