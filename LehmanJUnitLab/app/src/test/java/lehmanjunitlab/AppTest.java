import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class LehmanGradeBookTest {

    @Test
    @DisplayName("Grade 70 should return true for passing")
    void testPassingGrade() {
        LehmanGradeBook gb = new LehmanGradeBook();
        assertTrue(gb.isPassing(70), "A grade of 70 should pass.");
    }
}