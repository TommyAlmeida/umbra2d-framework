import com.lewk.core.core.kernel.Game;

public class GameTest extends Game {

    public GameTest() {
        super(1280, 720, "Game Test");
    }

    @Override
    public void onInit() {
        System.out.println("Init");
    }

    @Override
    public void onUpdate(double deltatime) {
        System.out.println("Updated: " + deltatime);
    }

    @Override
    public void onDispose() {
        System.out.println("Disposed");
    }

    public static void main(String[] args) {
        GameTest gameTest = new GameTest();
        gameTest.start();
    }
}
