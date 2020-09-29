package de.neuefische.hh2020ji.passwordvalidation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {

    @ParameterizedTest(name = "check length of password {0} should validate to {1}")
    @CsvSource({
            "123456789, false",
            "0123456789, true"
    })
    void checkPasswordLength(String given, boolean expected) {
        boolean actual = SecurityUtils.checkPasswordLength(given);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "check contains numbers of password {0} should give {1}")
    @CsvSource({
            "password, false",
            "password123, true",
            "pass123word, true"
    })
    void checkContainsNumbers(String given, boolean expected){
        boolean actual = SecurityUtils.containsNumbers(given);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "check contains lowercase letters of password {0} should give {1}")
    @CsvSource({
            "PASSWORD123, false",
            "PassWord123, true"
    })
    void checkContainsLowercaseLetters(String given, boolean expected){
        boolean actual = SecurityUtils.containsLowercaseLetters(given);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "check contains special characters of password {0} should give {1}")
    @CsvSource({
            "passwort123@, true",
            "passwort123, false",
            "passwort123-, true",
            "passwort[123, true"
    })
    void checkContainsSpecialCharacters(String givenPassword, boolean expected){
        boolean actual = SecurityUtils.containsSpecialCharacters(givenPassword);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "check contains special characters of password {0} should give {1}")
    @CsvSource({
            "pass123@, false",
            "passwort, false",
            "passwort123, false",
            "passwort[123, true"
    })
    void checkisValidPassword(String givenPassword, boolean expected){
        boolean actual = SecurityUtils.isValidPassword(givenPassword);

        assertEquals(expected, actual);
    }

    @ParameterizedTest(name = "check contains special characters of password {0} should give {1}")
    @MethodSource("providePasswordsAndResults")
    void checkisValidPasswordList(String[] givenPasswordList, boolean expected){
        boolean actual = SecurityUtils.isValidPasswordList(givenPasswordList);

        assertEquals(expected, actual);
    }

    private static List<Arguments> providePasswordsAndResults(){
        return List.of(
                Arguments.of(new String[]{"pass123@","passwort[123","passwort%123"}, false),
                Arguments.of(new String[]{"passWORD123@","passwort[123","passwort%123"}, true)

                );
    }

}