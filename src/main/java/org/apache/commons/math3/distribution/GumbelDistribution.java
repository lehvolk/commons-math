package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/**
 * Logistic distribution implementation
 *
 * @version $Id$
 * @see <a href="http://mathworld.wolfram.com/GumbelDistribution.html">Gumbel Distribution</a>
 * @see <a href="http://en.wikipedia.org/wiki/Gumbel_distribution">Gumbel Distribution</a>
 */
public class GumbelDistribution extends AbstractRealDistribution {

	private double mu;
	private double beta;

	/**
	 * Construct instance of Gumbel distribution
	 *
	 * @param mu   mu
	 * @param beta beta
	 */
	public GumbelDistribution(double mu, double beta) {
		this(new Well19937c(), mu, beta);

	}

	/**
	 * Construct instance of Gumbel distribution
	 *
	 * @param rg   random generator
	 * @param mu   mu
	 * @param beta beta
	 */
	public GumbelDistribution(RandomGenerator rg, double mu, double beta) {
		super(rg);
		this.beta = beta;
		this.mu = mu;
	}

	public double density(double x) {
		final double z = (x - mu) / beta;
		double t = FastMath.exp(-z);
		return t * FastMath.exp(-t) / FastMath.abs(beta);
	}

	public double cumulativeProbability(double x) {
		final double z = (x - mu) / beta;
		if (beta > 0.) {
			return FastMath.exp(-FastMath.exp(-z));
		}
		return -FastMath.expm1(-FastMath.exp(-z));
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
		if (beta > 0.) {
			return mu - FastMath.log(-FastMath.log(p)) * beta;
		}
		return mu - FastMath.log(-FastMath.log1p(-p)) * beta;
	}

	public double getNumericalMean() {
		return mu + FastMath.EULER * beta;
	}

	public double getNumericalVariance() {
		return FastMath.PI * FastMath.PI * beta * beta / 6.0;
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
	 * @return scale
	 */
	public double getBeta() {
		return beta;
	}

	/**
	 * @return scale
	 */
	public double getMu() {
		return mu;
	}

}
