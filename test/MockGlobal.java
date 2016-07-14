import play.*;
import utils.TestUtils;

public class MockGlobal extends GlobalSettings {
    @Override
    public void onStart(Application app) {
        Logger.info("Fake application has started.");
        TestUtils.initDB();
    }

    public void onStop(Application app) {
        Logger.info("Fake application was shutdown.");
    }
}