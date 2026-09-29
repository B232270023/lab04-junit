package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class GradeCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "95, A",
            "85, B",
            "75, C",
            "65, D",
            "30, F"
    })
    @DisplayName("Ердийн оноонууд зөв үсгэн дүн гаргах ёстой")
    void letterGradeNormalValues(double score, String expected) {
        GradeCalculator calc = new GradeCalculator(); // Arrange

        String actual = calc.letterGrade(score); // Act

        assertEquals(expected, actual); // Assert
    }

    @ParameterizedTest
    @CsvSource({
            "90, A",
            "89.99, B",
            "60, D",
            "59.99, F",
            "0, F",
            "100, A"
    })
    @DisplayName("Хязгаарын оноонууд зөв үсгэн дүн гаргах ёстой")
    void letterGradeBoundaryValues(double score, String expected) {
        GradeCalculator calc = new GradeCalculator(); // Arrange

        String actual = calc.letterGrade(score); // Act

        assertEquals(expected, actual); // Assert
    }

    @Test
    @DisplayName("-1 оноо exception шидэх ёстой")
    void negativeLetterGradeThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(-1)
        );
    }

    @Test
    @DisplayName("101 оноо exception шидэх ёстой")
    void overMaximumLetterGradeThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.letterGrade(101)
        );
    }

    @Test
    @DisplayName("Дээд оноонуудын нийлбэр 100 байх ёстой")
    void totalScoreMaximumIs100() {
        GradeCalculator calc = new GradeCalculator();

        double actual =
                calc.totalScore(10, 40, 10, 10, 30);

        assertEquals(100.0, actual);
    }

    @Test
    @DisplayName("Ердийн оноонуудын нийлбэр зөв байх ёстой")
    void totalScoreNormalValue() {
        GradeCalculator calc = new GradeCalculator();

        double actual =
                calc.totalScore(8, 35, 7, 8, 25);

        assertEquals(83.0, actual);
    }

    @Test
    @DisplayName("Сөрөг attendance exception шидэх ёстой")
    void negativeAttendanceThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(-5, 40, 10, 10, 30)
        );
    }

    @Test
    @DisplayName("Lab 40-өөс их бол exception шидэх ёстой")
    void labOverMaximumThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 41, 10, 10, 30)
        );
    }

    @ParameterizedTest
    @CsvSource({
            "10, 40, 10, 10, 30, 100",
            "5, 20, 5, 5, 15, 50",
            "0, 0, 0, 0, 0, 0"
    })
    @DisplayName("totalScore олон утга дээр зөв ажиллах ёстой")
    void totalScoreParameterized(
            double att,
            double lab,
            double quiz1,
            double quiz2,
            double exam,
            double expected
    ) {
        GradeCalculator calc = new GradeCalculator();

        double actual =
                calc.totalScore(att, lab, quiz1, quiz2, exam);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Exam 30-аас их бол exception шидэх ёстой")
    void examOverMaximumThrowsException() {
        GradeCalculator calc = new GradeCalculator();

        assertThrows(
                IllegalArgumentException.class,
                () -> calc.totalScore(10, 40, 10, 10, 31)
        );
    }
}
