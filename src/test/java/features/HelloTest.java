package features;

import com.example.funIM.App;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.solon.test.HttpTester;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;


@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(App.class)
public class HelloTest extends HttpTester {
    @Test
    public void note() {
        System.out.println("测试接口");
    }
}