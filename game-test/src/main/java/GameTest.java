import com.lewk.core.core.io.DisplayConfig;
import com.lewk.core.core.kernel.Game;

public class GameTest extends Game {

    public GameTest() {
        super(DisplayConfig.builder()
                .width(1280)
                .height(720)
                .build());
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onRender(float delta) {

    }

    @Override
    public void onDispose() {

    }

    public static void main(String[] args) {
        GameTest gameTest = new GameTest();
        gameTest.start();
    }
}
