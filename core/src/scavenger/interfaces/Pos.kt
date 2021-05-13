package scavenger.interfaces

import arc.math.geom.Position
import arc.math.geom.Vec2
import scavenger.Vars
import scavenger.world.Tile

interface Pos : Position {
    var xPos: Float
    var yPos: Float

    override fun getX() = xPos
    override fun getY() = yPos

    fun set(x: Float, y: Float) {
        this.xPos = x
        this.yPos = y
    }

    fun set(vec: Vec2) {
        this.xPos = vec.x
        this.yPos = vec.y
    }

    fun tileOn(): Tile {
        return Vars.world.tile((xPos / Vars.tilesize).toInt(), (yPos / Vars.tilesize).toInt())
    }
}