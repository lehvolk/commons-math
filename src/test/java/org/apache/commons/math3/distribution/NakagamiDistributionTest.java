package org.apache.commons.math3.distribution;

import org.apache.commons.math3.util.Precision;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Alexey Volkov
 * @since 22.07.14
 */
public class NakagamiDistributionTest extends RealDistributionAbstractTest {

    @Test
    public void testParameters() {
        NakagamiDistribution d = makeDistribution();
        Assert.assertEquals(0.5, d.getM(), Precision.EPSILON);
        Assert.assertEquals(1, d.getOmega(), Precision.EPSILON);
    }

    @Test
    public void testSupport() {
        NakagamiDistribution d = makeDistribution();
        Assert.assertEquals(d.getSupportLowerBound(), 0, Precision.EPSILON);
        Assert.assertTrue(Double.isInfinite(d.getSupportUpperBound()));
        Assert.assertTrue(d.isSupportConnected());
    }

    @Override
    public NakagamiDistribution makeDistribution() {
        return new NakagamiDistribution(0.5, 1);
    }

    @Override
    public double[] makeCumulativeTestPoints() {
        return new double[] {
                0, 0.2, 0.4, 0.6, 0.8, 1, 1.2, 1.4, 1.6, 1.8, 2
        };
    }

    @Override
    public double[] makeDensityTestValues() {
        return new double[] {
                0.0000000, 0.7820854, 0.7365403, 0.6664492, 0.5793831, 0.4839414, 0.3883721, 0.2994549, 0.2218417, 0.1579003,
                0.1079819
        };
    }

    @Override
    public double[] makeCumulativeTestValues() {
        return new double[] {
                0.0000000, 0.1585194, 0.3108435, 0.4514938, 0.5762892, 0.6826895, 0.7698607, 0.8384867, 0.8904014, 0.9281394,
                0.9544997
        };
    }

}

