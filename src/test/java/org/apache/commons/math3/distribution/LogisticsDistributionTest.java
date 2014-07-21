package org.apache.commons.math3.distribution;

import org.apache.commons.math3.util.Precision;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexey Volkov
 * @since 22.07.14
 */
public class LogisticsDistributionTest extends RealDistributionAbstractTest {

    @Test
    public void testParameters() {
        LogisticDistribution d = makeDistribution();
        Assert.assertEquals(2, d.getMu(), Precision.EPSILON);
        Assert.assertEquals(5, d.getB(), Precision.EPSILON);
    }

    @Test
    public void testSupport() {
        LogisticDistribution d = makeDistribution();
        Assert.assertTrue(Double.isInfinite(d.getSupportLowerBound()));
        Assert.assertTrue(Double.isInfinite(d.getSupportUpperBound()));
        Assert.assertTrue(d.isSupportConnected());
    }

    @Override
    public LogisticDistribution makeDistribution() {
        return new LogisticDistribution(2, 5);
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
                0.03173698, 0.03557889, 0.03932239, 0.04278194, 0.04575685, 0.04805215, 0.04950331, 0.05000000, 0.04950331,
                0.04805215, 0.04575685
        };
    }

    @Override
    public double[] makeCumulativeTestValues() {
        return new double[] {
                0.1978161, 0.2314752, 0.2689414, 0.3100255, 0.3543437, 0.4013123, 0.4501660, 0.5000000, 0.5498340, 0.5986877,
                0.6456563
        };
    }

}

