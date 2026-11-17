
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

class BonusTest {

    @Test
    @DisplayName("B1: attendance 31->32(T), null input")
    void bonusNullAttendance() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(null, 0));
    }

    @Test
    @DisplayName("B2: threshold 31->32(T), negative threshold")
    void bonusNegativeThreshold() {
        assertThrows(IllegalArgumentException.class,
            () -> DataFlowTargets.bonus(new int[]{}, -1));
    }

    @Test
    @DisplayName("B3: present 35->50(T), score 51->56")
    void bonusEmptyAttendanceZeroThreshold() {
        assertEquals(5,
            DataFlowTargets.bonus(new int[]{}, 0));
    }

    @Test
    @DisplayName("B4: a 38->39(T/F), present 40->50(F)")
    void bonusMixedAttendanceBelowThreshold() {
        assertEquals(0,
            DataFlowTargets.bonus(new int[]{1, 0}, 2));
    }

    @Test
    @DisplayName("B5: present 35->50(F), zero iterations")
    void bonusEmptyAttendancePositiveThreshold() {
        assertEquals(0,
            DataFlowTargets.bonus(new int[]{}, 1));
    }

    @Test
    @DisplayName("B6: present 40->40, best 43->42(T)")
    void bonusTwoConsecutiveDays() {
        assertEquals(5,
            DataFlowTargets.bonus(new int[]{1, 1}, 2));
    }

    @Test
    @DisplayName("B7: streak 46->41, best 43->42(F)")
    void bonusInterruptedStreak() {
        assertEquals(5,
            DataFlowTargets.bonus(new int[]{1, 0, 1}, 2));
    }

    @Test
    @DisplayName("B8: best 43->53(T), score 51->54->56")
    void bonusFiveConsecutiveDaysFullReward() {
        assertEquals(8,
            DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 5));
    }

    @Test
    @DisplayName("B9: score 49->54, streak reward only")
    void bonusFiveConsecutiveDaysStreakOnly() {
        assertEquals(3,
            DataFlowTargets.bonus(new int[]{1, 1, 1, 1, 1}, 6));
    }

@Test
@DisplayName("AD1: score 51->54, 54->56; best 43->53")
void allDefsFullBonus() {
    assertEquals(8, DataFlowTargets.bonus(
        new int[]{1, 1, 1, 1, 1}, 5
    ));
}

@Test
@DisplayName("AD2: score 49->56; streak 46->41")
void allDefsResetAndNoReward() {
    assertEquals(0, DataFlowTargets.bonus(
        new int[]{1, 0, 1}, 3
    ));
}

}
