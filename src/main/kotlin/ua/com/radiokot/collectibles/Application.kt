package ua.com.radiokot.collectibles

import com.sksamuel.scrimage.nio.ImmutableImageLoader
import com.sksamuel.scrimage.nio.PngWriter
import ua.com.radiokot.collectibles.generator.RandomCollectiblesGenerator
import ua.com.radiokot.collectibles.generator.model.GeneratedCollectible
import ua.com.radiokot.collectibles.generator.model.GenerationPoolEntry
import java.io.File

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val loader = ImmutableImageLoader.create()

        val groups = listOf(
            (File("D:\\Downloads\\Parts\\1-BG")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Background",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
            (File("D:\\Downloads\\Parts\\3-Body")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Body",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
            (File("D:\\Downloads\\Parts\\4-Head")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Head",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
            (File("D:\\Downloads\\Parts\\5-Ears")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Ears",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
            (File("D:\\Downloads\\Parts\\6-Mouse")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Mouth",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
            (File("D:\\Downloads\\Parts\\7-Nose")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Nose",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
            (File("D:\\Downloads\\Parts\\8-Eyes")
                .listFiles { _, fileName -> fileName.endsWith(".png") } ?: emptyArray())
                .map { file ->
                    GenerationPoolEntry.Image(
                        name = file.name,
                        groupName = "Eyes",
                        weight = 1,
                        isRare = false,
                        content = loader.fromFile(file)
                    )
                },
        )

        repeat(100) {
            val collectible = RandomCollectiblesGenerator(groups).generate()

            val collectibleImage = ImageAssembler().assemble(
                collectible.parts
                    .map(GeneratedCollectible.Part::content)
            )

            collectibleImage.output(PngWriter.NoCompression, File("D:\\Downloads\\Parts\\result.png"))

            Thread.sleep(1500)
        }
    }
}