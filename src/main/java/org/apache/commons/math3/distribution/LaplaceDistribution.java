package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/**
 * Implements the Laplace distribution.
 *
 * @version $Id$
 * @see <a href="http://en.wikipedia.org/wiki/Laplace_distribution">Laplace distribution</a>
 */
public class LaplaceDistribution extends AbstractRealDistribution {

    private final double mu;
    private final double beta;

    /**
     * Constructs Laplace distribution with default parameters
     *
     * @param mu   mu
     * @param beta beta
     */
    public LaplaceDistribution(double mu, double beta) {
        this(new Well19937c(), mu, beta);
    }

    /**
     * Constructs Laplace distribution
     *
     * @param rd   random generator
     * @param mu   mu
     * @param beta beta
     */
    public LaplaceDistribution(RandomGenerator rd, double mu, double beta) {
        super(rd);
        if (beta <= 0.0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, beta);
        }
        this.mu = mu;
        this.beta = beta;
    }

    public double density(double x) {
        return FastMath.exp(-FastMath.abs(x - mu) / beta) / (2.0 * beta);
    }

    public double cumulativeProbability(double x) {
        if (x <= mu) {
            return FastMath.exp((x - mu) / beta) / 2.0;
        } else {
            return 1.0 - FastMath.exp((mu - x) / beta) / 2.0;
        }
    }

    @Override
    public double inverseCumulativeProbability(double p) throws OutOfRangeException {
        if (p < 0.0 || p > 1.0) {
            throw new OutOfRangeException(p, 0.0, 1.0);
        } else if (p == 0) {
            return 0.0;
        } else if (p == 1) {
            return Double.POSITIVE_INFINITY;
        }
        double x = (p > 0.5) ? -Math.log(2. - 2 * p) : Math.log(2 * p);
        return mu + beta * x;
    }

    public double getNumericalMean() {
        return mu;
    }

    public double getNumericalVariance() {
        return (2.0 * beta * beta);
    }

    public double getSupportLowerBound() {
        return Double.NEGATIVE_INFINITY;
    }

    public double getSupportUpperBound() {
        return Double.POSITIVE_INFINITY;
    }

    public boolean isSupportLowerBoundInclusive() {
        return false;
    }

    public boolean isSupportUpperBoundInclusive() {
        return false;
    }

    public boolean isSupportConnected() {
        return true;
    }

    /**
     * Returns the mu parameter
     */
    public double getMu() {
        return mu;
    }

    /**
     * Returns the beta parameter
     */
    public double getBeta() {
        return beta;
    }
}
