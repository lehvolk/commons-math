package org.apache.commons.math3.distribution;

import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.random.Well19937c;
import org.apache.commons.math3.util.FastMath;

/**
 * Logistic distribution implementation
 *
 * @version $Id$
 * @see <a href="http://mathworld.wolfram.com/LogisticDistribution.html">Logistic Distribution</a>
 * @see <a href="http://en.wikipedia.org/wiki/Logistic_distribution">Logistic Distribution</a>
 */
public class LogisticDistribution extends AbstractRealDistribution {

	private double mu;
	private double b;

	/**
	 * construct logistic distribution
	 *
	 * @param mu mean
	 * @param b  scale
	 */
	public LogisticDistribution(double mu, double b) {
		this(new Well19937c(), mu, b);
	}

	/**
	 * construct logistic distribution
	 *
	 * @param rg random generator
	 * @param mu mean
	 * @param b  scale
	 */
	public LogisticDistribution(RandomGenerator rg, double mu, double b) {
		super(rg);
		if (b <= 0.0) {
			throw new NotStrictlyPositiveException(LocalizedFormats.NOT_POSITIVE_SCALE, b);
		}
		this.mu = mu;
		this.b = b;
	}

	public double density(double x) {
		double z = 1 / b * (x - mu);
		double v = FastMath.exp(-z);
		return 1 / b * v / ((1.0 + v) * (1.0 + v));
	}

	public double cumulativeProbability(double x) {
		double z = 1 / b * (x - mu);
		return 1.0 / (1.0 + FastMath.exp(-z));
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
		return b * Math.log(p / (1.0 - p)) + mu;
	}

	public double getNumericalMean() {
		return mu;
	}

	public double getNumericalVariance() {
		return ((FastMath.PI * FastMath.PI / 3) * (1 / (b * b)));
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
	 * @return mean
	 */
	public double getMu() {
		return mu;
	}

	/**
	 * @return scale
	 */
	public double getB() {
		return b;
	}

}
