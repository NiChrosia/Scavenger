package scavenger.world

import arc.func.Cons
import arc.math.Mathf
import arc.struct.Seq
import arc.util.Log
import arc.util.noise.Simplex
import scavenger.Vars
import scavenger.content.Blocks
import scavenger.content.Floors

class TileContainer(var width: Int, var height: Int) {
    private var tileArr = Seq<Seq<Tile>>()
    private val simplex = Simplex(1)

    init {
        reset()
    }

    fun each(cons: Cons<Tile>) {
        tileArr.each {
            it.each { tile ->
                cons.get(tile)
            }
        }
    }

    fun isInBounds(x: Int, y: Int): Boolean {
        return x <= width && y <= height
    }

    fun set(x: Int, y: Int, tile: Tile) {
        tileArr[y][x] = tile
    }

    fun get(x: Int, y: Int): Tile {
        if (isInBounds(x, y)) {
            return tileArr[y][x]
        } else {
            throw IndexOutOfBoundsException("Cannot find tile at position ($x, $y)")
        }
    }

    private fun reset() {
        tileArr = Seq<Seq<Tile>>()

        for (y in 0..height) {
            tileArr.add(Seq<Tile>())
            for (x in 0..width) {
                val posX = (x * Vars.tilesize).toFloat()
                val posY = (y * Vars.tilesize).toFloat()

                val noiseLevel = simplex.octaveNoise2D(0.01, 0.1, 0.1, posX * 1.0, posY * 1.0)

                tileArr[y].add(Tile(posX, posY,
                    if (noiseLevel > Mathf.random(0.75f)) Floors.metal else Floors.wood,
                    if (noiseLevel > 0.75) {
                        Blocks.crate.Building(posX, posY)
                    } else {
                        Blocks.air.Building(posX, posY)
                    }
                ))
            }
        }
    }
}