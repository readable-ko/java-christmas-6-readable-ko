package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayName("Status")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class StatusTest {
    @Test
    void 상태_결과값_출력() {
        assertThat(Status.GOT_MESSAGE.getStatus()).isFalse();
        assertThat(Status.NOTHING.getStatus()).isTrue();
    }
}
