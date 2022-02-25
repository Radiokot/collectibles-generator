package ua.com.radiokot.collectibles.generator.model

import com.sksamuel.scrimage.ImmutableImage

sealed class GenerationPoolEntry(
    val groupName: String,
    override val weight: Int,
): WeightedEntry {
    class Missing(
        groupName: String,
        weight: Int
    ) : GenerationPoolEntry(groupName, weight)

    class Image(
        val name: String,
        val isRare: Boolean,
        val content: ImmutableImage,
        groupName: String,
        weight: Int,
    ) : GenerationPoolEntry(groupName, weight)
}