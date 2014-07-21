package org.apache.commons.math3.distribution;

import org.apache.commons.math3.util.Precision;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexey Volkov
 * @since 21.07.14
 */
public class LaplaceDistributionTest extends RealDistributionAbstractTest {

    @Test
    public void testParameters() {
        LaplaceDistribution d = makeDistribution();
        Assert.assertEquals(0, d.getMu(), Precision.EPSILON);
        Assert.assertEquals(1, d.getBeta(), Precision.EPSILON);
    }

    @Test
    public void testSupport() {
        LaplaceDistribution d = makeDistribution();
        Assert.assertTrue(Double.isInfinite(d.getSupportLowerBound()));
        Assert.assertTrue(Double.isInfinite(d.getSupportUpperBound()));
        Assert.assertTrue(d.isSupportConnected());
    }

    @Override
    public LaplaceDistribution makeDistribution() {
        return new LaplaceDistribution(0, 1);
    }

    @Override
    public double[] makeCumulativeTestPoints() {
        return new double[] {
                -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5
        };
    }

    @Override
    public double[] makeDensityTestValues() {
        return new double[] {
                0.003368973, 0.009157819, 0.024893534, 0.067667642, 0.183939721, 0.500000000, 0.183939721, 0.067667642,
                0.024893534, 0.009157819, 0.003368973
        };
    }

    @Override
    public double[] makeCumulativeTestValues() {
        return new double[] {
                0.003368973, 0.009157819, 0.024893534, 0.067667642, 0.183939721, 0.500000000, 0.816060279, 0.932332358,
                0.975106466, 0.990842181, 0.996631027
        };
    }

}

