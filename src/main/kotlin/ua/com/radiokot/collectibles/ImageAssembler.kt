package ua.com.radiokot.collectibles

import com.sksamuel.scrimage.ImmutableImage

class ImageAssembler {
    fun assemble(layers: List<ImmutableImage>): ImmutableImage {
        require(layers.isNotEmpty()) {
            "List of layers can't be empty"
        }

        require(layers.map(ImmutableImage::ratio).distinct().size == 1) {
            "All the layers must have the same ratio"
        }

        val maxWidth = layers.maxByOrNull(ImmutableImage::width)!!.width
        val maxHeight = layers.maxByOrNull(ImmutableImage::height)!!.height

        return layers.fold(ImmutableImage.create(maxWidth, maxHeight)) { result, layer ->
            result.overlay(layer.copy().resizeTo(maxWidth, maxHeight))
        }
    }
}