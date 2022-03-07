package ua.com.radiokot.collectibles.generator.model
import ua.com.radiokot.collectibles.generator.weightedRandom

/**
 * An entry of a pool which has an adjustable
 * probability of picking defined by [weight].
 *
 * For example, if 4 entries have equal weights, probability
 * of picking each of them is 25%. But if there are 2 entries with weights of 9 and 1,
 * then the probabilities are 90% and 10% correspondingly.
 *
 * @see [weightedRandom]
 */
interface WeightedEntry {
    val weight: Int
}