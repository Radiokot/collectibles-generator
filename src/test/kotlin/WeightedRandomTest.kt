import org.junit.Test
import ua.com.radiokot.collectibles.generator.model.WeightedEntry
import ua.com.radiokot.collectibles.generator.weightedRandom
import kotlin.math.roundToInt

class WeightedRandomTest {
    class SimpleWeighedEntry(override val weight: Int) : WeightedEntry

    @Test
    fun weightedRandom() {
        val entries = listOf(
            SimpleWeighedEntry(25),
            SimpleWeighedEntry(25),
            SimpleWeighedEntry(5),
            SimpleWeighedEntry(40),
            SimpleWeighedEntry(5),
        )

        val results = entries.associateWith { 0 }.toMutableMap()

        repeat(100000) {
            val picked = entries.weightedRandom()
            results[picked] = results[picked]!! + 1
        }

        entries.forEach {
            assert((it.weight - 1..it.weight + 1).contains((results[it]!! / 1000f).roundToInt()))
        }
    }
}