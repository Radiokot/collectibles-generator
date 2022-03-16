package ua.com.radiokot.collectibles.generator

import ua.com.radiokot.collectibles.generator.model.GenerationPoolEntry
import ua.com.radiokot.collectibles.generator.model.GeneratedCollectible

/**
 * Simple collectibles generator that takes parts from the pool
 *
 * @param orderedPool ordered set of parts
 */
class RandomCollectiblesGenerator(
    private val orderedPool: List<Collection<GenerationPoolEntry>>
) : CollectiblesGenerator {

    override fun generate(): GeneratedCollectible {
        val incompatibleParts = mutableSetOf<String>()

        return GeneratedCollectible(
            parts = orderedPool
                .mapNotNull { poolEntries ->
                    val filteredPoolEntries =  poolEntries
                        .filterNot { it is GenerationPoolEntry.Image && incompatibleParts.contains(it.name) }

                    if (filteredPoolEntries.isEmpty()) {
                        return@mapNotNull null
                    }

                    val choice = filteredPoolEntries.weightedRandom()
                    val originalProbability = poolEntries.probabilityOf(choice)

                    if (choice is GenerationPoolEntry.Image) {
                        incompatibleParts.addAll(choice.incompatibleWith)
                        choice to originalProbability
                    } else {
                        null
                    }
                }
                .map { (poolEntry, probability) ->
                    GeneratedCollectible.Part(poolEntry, probability)
                }
        )
    }
}