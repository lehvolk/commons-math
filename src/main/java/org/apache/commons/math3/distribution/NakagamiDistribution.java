package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.special.Gamma;
import org.apache.commons.math3.util.FastMath;

/**
 * Nakagami distribution implementation
 *
 * @version $Id$
 * @see <a href="http://en.wikipedia.org/wiki/Nakagami_distribution">Nakagami Distribution</a>
 */
public class NakagamiDistribution extends AbstractRealDistribution {

    /**
     * Default inverse cumulative probability accuracy.
     */
    public static final double DEFAULT_INVERSE_ABSOLUTE_ACCURACY = 1e-9;
    private final double omega;         // Scale parameter
    private final double m;              // Shape parameter
    private final double inverseAbsoluteAccuracy;

    /**
     * construct instance of nakagimi distribution
     *
     * @param m     m
     * @param omega omega
     */
    public NakagamiDistribution(double m, double omega) {
        this(new Well19937c(), m, omega, DEFAULT_INVERSE_ABSOLUTE_ACCURACY);
    }

    /**
     * construct instance of nakagimi distribution
     *
     * @param m                       m
     * @param omega                   omega
     * @param inverseAbsoluteAccuracy inverse absolute accuracy
     */

    public NakagamiDistribution(double m, double omega, double inverseAbsoluteAccuracy) {
        this(new Well19937c(), m, omega, inverseAbsoluteAccuracy);
    }

    /**
     * construct instance of nakagimi distribution
     *
     * @param rg                      random generator
     * @param m                       m
     * @param omega                   omega
     * @param inverseAbsoluteAccuracy inverse absolute accuracy
     */
    public NakagamiDistribution(RandomGenerator rg, double m, double omega, double inverseAbsoluteAccuracy) {
        super(rg);
        if (m <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, omega);
        }
        if (omega < 0.5) {
            throw new NumberIsTooSmallException(omega, 0.5, true);

        }
        this.inverseAbsoluteAccuracy = inverseAbsoluteAccuracy;
        this.m = m;
        this.omega = omega;
    }

    public double density(double x) {
        if (x <= 0) {
            return 0.0;
        }
        return 2.0 * FastMath.pow(m, m) / (Gamma.gamma(m) * FastMath.pow(omega, m)) * FastMath.pow(x, 2 * m - 1) *
                FastMath.exp(-m * x * x / omega);
    }

    public double cumulativeProbability(double x) {
        return Gamma.regularizedGammaP(m, m * x * x / omega);
    }

    public double getNumericalMean() {
        return Gamma.gamma(m + 1 / 2) / Gamma.gamma(m) * FastMath.sqrt(omega / m);
    }

    public double getNumericalVariance() {
        double v = Gamma.gamma(m + 1 / 2) / Gamma.gamma(m);
        return omega * (1 - 1 / m * v * v);
    }

    public double getSupportLowerBound() {
        return 0;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportLowerBoundInclusive() {
        return true;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public boolean isSupportConnected() {
        return true;
    }

    /**
     * @return get m parameter
     */
    public double getM() {
        return m;
    }

    /**
     * @return get omega parameter
     */
    public double getOmega() {
        return omega;
    }

    @Override
    protected double getSolverAbsoluteAccuracy() {
        return inverseAbsoluteAccuracy;
    }
}
