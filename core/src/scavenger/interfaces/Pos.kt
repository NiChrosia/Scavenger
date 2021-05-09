package scavenger.interfaces

import arc.math.geom.Position
import scavenger.Vars
import scavenger.world.Tile

interface Pos : Position {
    var xPos: Float
    var yPos: Float

    fun set(x: Float, y: Float) {
        this.xPos = x
        this.yPos = y
    }

    fun tileOn(): Tile {
        return Vars.world.tile((xPos / Vars.tilesize).toInt(), (yPos / Vars.tilesize).toInt())
    }
}