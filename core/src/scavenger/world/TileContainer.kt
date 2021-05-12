package scavenger.world

import arc.func.Cons
import arc.math.Mathf
import arc.struct.Seq
import scavenger.Vars
import scavenger.content.Blocks
import scavenger.content.Floors

open class TileContainer(var width: Int, var height: Int) {
    private var tileArr = Seq<Seq<Tile>>()

    init {
        reset()
    }

    open fun each(cons: Cons<Tile>) {
        tileArr.each {
            it.each { tile ->
                cons.get(tile)
            }
        }
    }

    open fun isInBounds(x: Int, y: Int): Boolean {
        return x <= width && y <= height
    }

    open fun set(x: Int, y: Int, tile: Tile) {
        tileArr[y][x] = tile
    }

    open fun get(x: Int, y: Int): Tile {
        if (isInBounds(x, y)) {
            return tileArr[y][x]
        } else {
            throw IndexOutOfBoundsException("Cannot find tile at position ($x, $y)")
        }
    }

    open fun reset() {
        tileArr = Seq<Seq<Tile>>()

        for (y in 0..height) {
            tileArr.add(Seq<Tile>())
            for (x in 0..width) {
                val posX = (x * Vars.tilesize).toFloat()
                val posY = (y * Vars.tilesize).toFloat()
                
                tileArr[y].add(Tile(posX, posY,
                    Floors.metal,
                    if (Mathf.chance(0.2)) {
                        Blocks.crate.Building(posX, posY)
                    } else {
                        Blocks.air.Building(posX, posY)
                    }
                ))
            }
        }
    }
}