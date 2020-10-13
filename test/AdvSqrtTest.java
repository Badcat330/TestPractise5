import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AdvSqrtTest {
    AdvSqrt advSqrt = new AdvSqrt();
    final int imax = 10;

    @DataProvider(name = "doubleProvider")
    public Object[][] GetData() {
        int i = 0;
        Object[] par = {51.01171470957408,
                54.85190989380491,
                57.35969137639698,
                19.16580699863239,
                35.46733216984694,
                19.782707084004997,
                9.351324017306268,
                34.56659294780965,
                3.9194037916312254,
                94.23130187963045};
        return new Object[][]{par};
    }

    @Test(dataProvider = "doubleProvider")
    public void SimpleTest(double[] arg) {
        for (int i = 0; i < imax; ++i) {
            long res = Double.doubleToLongBits(advSqrt.sqrt(arg[i]));
            long answer = Double.doubleToLongBits(Math.sqrt(arg[i]));
            boolean check = res == answer || res == answer + 1 || res == answer - 1;
            Assert.assertTrue(check, Double.toString(arg[i]));
        }
    }

    @Test
    public void DnrArgTest() {
        double arg = Double.longBitsToDouble(0x000f_ffff_ffff_ffffL);
        long res = Double.doubleToLongBits(advSqrt.sqrt(arg));
        long answer = Double.doubleToLongBits(Math.sqrt(arg));
        Assert.assertTrue(res == answer || res == answer + 1 || res == answer - 1);
    }

    @Test
    public void NegativeArgTest() {
        Assert.assertEquals(Double.NaN, advSqrt.sqrt(-4352));
    }

    @Test
    public void NegativeInfArgTest() {
        Assert.assertEquals(Double.NaN, advSqrt.sqrt(Double.NEGATIVE_INFINITY));
    }

    @Test
    public void NegativeZeroArgTest() {
        Assert.assertEquals(-0.0, advSqrt.sqrt(-0.0));
    }

    @Test
    public void PositiveInfArgTest() {
        Assert.assertEquals(Double.POSITIVE_INFINITY, advSqrt.sqrt(Double.POSITIVE_INFINITY));
    }

    @Test
    public void NanArgTest() {
        Assert.assertEquals(Double.NaN, advSqrt.sqrt(Double.NaN));
    }
}