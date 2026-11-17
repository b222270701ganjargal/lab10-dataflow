
package mn.edu.must.sqat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BonusTest {

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
