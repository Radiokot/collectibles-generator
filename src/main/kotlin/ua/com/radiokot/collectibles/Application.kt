package ua.com.radiokot.collectibles

import com.sksamuel.scrimage.nio.ImmutableImageLoader
import com.sksamuel.scrimage.nio.PngWriter
import java.io.File

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val partsRoot = File("D:\\Downloads\\Parts")
        val partDirectories = partsRoot.listFiles()?.filter(File::isDirectory) ?: emptyList()
        val partsMap = partDirectories.associateWith { partDirectory ->
            partDirectory.listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray()
        }

        val selectedParts = partsMap.map { (_, variants) -> variants.random() }

        val loader = ImmutableImageLoader.create()
        val nft = ImageAssembler().assemble(selectedParts.map { part ->
            loader.fromFile(part)
        })

        nft.output(PngWriter.NoCompression, File("D:\\Downloads\\Parts\\result.png"))
    }
}