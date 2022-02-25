package ua.com.radiokot.collectibles.generator.model

import com.sksamuel.scrimage.ImmutableImage

class GeneratedCollectible(
    val parts: List<Part>
) {
    class Part(
        val name: String,
        val groupName: String,
        val content: ImmutableImage,
        val probabilityPercent: Double,
        val isRare: Boolean,
    ) {
        constructor(
            poolEntry: GenerationPoolEntry.Image,
            probabilityPercent: Double
        ) : this(
            name = poolEntry.name,
            groupName = poolEntry.groupName,
            content = poolEntry.content,
            probabilityPercent = probabilityPercent,
            isRare = poolEntry.isRare
        )
    }

    val isRare = parts.any { it.isRare }
}