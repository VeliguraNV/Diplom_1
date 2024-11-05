package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class BunTest {

    public static final String nameBun = "Булка";
    public static final float priceBun = 100;
    @Test
    public void getNameString() {
        Bun bun = new Bun(nameBun, priceBun);
        Assert.assertEquals(nameBun, bun.getName());
    }

    @Test
    public void getPrice() {
        Bun bun = new Bun(nameBun, priceBun);
        Assert.assertEquals(priceBun, bun.getPrice(), 0);
    }
}