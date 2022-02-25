package ua.com.radiokot.collectibles.generator

import ua.com.radiokot.collectibles.generator.model.GeneratedCollectible

interface CollectiblesGenerator {
    fun generate(): GeneratedCollectible
}