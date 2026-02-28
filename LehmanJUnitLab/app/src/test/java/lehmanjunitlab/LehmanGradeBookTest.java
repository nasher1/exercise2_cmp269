package lehmanjunitlab; 

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LehmanGradeBookTest {

    @Test
    @DisplayName("Grade 70 should return true for passing")
    void testPassingGrade() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertTrue(gb.isPassing(70), "A grade of 70 should pass.");
    }

    @Test
    @DisplayName("Letter grades test")
    void testLetterGrades() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('A', gb.getLetterGrade(95));
        assertEquals('B', gb.getLetterGrade(85));
        assertEquals('C', gb.getLetterGrade(75));
        assertEquals('D', gb.getLetterGrade(65));
        assertEquals('F', gb.getLetterGrade(50));
    }

    @Test
    @DisplayName("Boundary tests for letter grades")
    void testBoundaryGrades() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertEquals('A', gb.getLetterGrade(90));
        assertEquals('B', gb.getLetterGrade(80));
        assertEquals('C', gb.getLetterGrade(70));
        assertEquals('D', gb.getLetterGrade(60));
    }

    @Test
    @DisplayName("Invalid grade throws exception")
    void testInvalidGradeThrowsException() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertThrows(IllegalArgumentException.class, () -> gb.isPassing(150));
        assertThrows(IllegalArgumentException.class, () -> gb.getLetterGrade(-10));
    }
}