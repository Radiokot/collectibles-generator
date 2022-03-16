package ua.com.radiokot.collectibles.generator.model

import com.sksamuel.scrimage.ImmutableImage

sealed class GenerationPoolEntry(
    /**
     * Name of the entries group, e.g. Head, Glasses, Jacket
     */
    val groupName: String,

    override val weight: Int,
): WeightedEntry {
    /**
     * If picked, leads to the corresponding group to be skipped.
     */
    class Missing(
        groupName: String,
        weight: Int
    ) : GenerationPoolEntry(groupName, weight)

    /**
     * If picked, will be used on the result image.
     */
    class Image(
        /**
         * Human-readable name of the entry.
         */
        val name: String,

        /**
         * Indicates whether it is a big luck to get this entry.
         */
        val isRare: Boolean,

        /**
         * Actual content.
         */
        val content: ImmutableImage,

        /**
         * Names of entries this one is incompatible with.
         *
         * For example, some hats are incompatible with some ears.
         */
        val incompatibleWith: Collection<String> = emptyList(),
        groupName: String,
        weight: Int,
    ) : GenerationPoolEntry(groupName, weight)
}