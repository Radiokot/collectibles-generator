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
            (File("/Users/olegkoretsky/Downloads/Parts/1-BG")
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

            listOf(
                GenerationPoolEntry.Image(
                    name = "Devil wings",
                    groupName = "Body parts",
                    weight = 3,
                    isRare = true,
                    content = loader.fromFile(File("/Users/olegkoretsky/Downloads/Parts/2-Body Parts/Bodyparts_4.png")),
                ),
                GenerationPoolEntry.Image(
                    name = "Pokemon tail",
                    groupName = "Body parts",
                    weight = 3,
                    isRare = true,
                    content = loader.fromFile(File("/Users/olegkoretsky/Downloads/Parts/2-Body Parts/Bodyparts_6.png")),
                ),
                GenerationPoolEntry.Image(
                    name = "Rick Sanchez haircut",
                    groupName = "Body parts",
                    weight = 3,
                    isRare = true,
                    content = loader.fromFile(File("/Users/olegkoretsky/Downloads/Parts/2-Body Parts/Bodyparts_8.png")),
                ),
                GenerationPoolEntry.Missing(
                    groupName = "Body parts",
                    weight = 90
                )
            ),

            (File("/Users/olegkoretsky/Downloads/Parts/3-Body")
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
            (File("/Users/olegkoretsky/Downloads/Parts/4-Head")
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
            (File("/Users/olegkoretsky/Downloads/Parts/5-Ears")
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
            (File("/Users/olegkoretsky/Downloads/Parts/6-Mouse")
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
            (File("/Users/olegkoretsky/Downloads/Parts/7-Nose")
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
            (File("/Users/olegkoretsky/Downloads/Parts/8-Eyes")
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

        repeat(100) { i ->
            val collectible = RandomCollectiblesGenerator(groups).generate()

            val collectibleImage = ImageAssembler().assemble(
                collectible.parts
                    .map(GeneratedCollectible.Part::content)
            )

            val fileName =
                if (collectible.isRare)
                    "r$i.png"
                else
                    "$i.png"

            collectibleImage.output(
                PngWriter.MinCompression,
                File("/Users/olegkoretsky/Downloads/Parts/_Generated/$fileName")
            )
        }
    }
}