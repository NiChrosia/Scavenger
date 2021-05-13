package scavenger.world

import arc.graphics.g2d.Draw
import arc.math.geom.Vec2
import scavenger.Vars
import scavenger.interfaces.Pos

class Tile(override var xPos: Float,
                override var yPos: Float,
                var floor: Floor,
                var building: Block.Building?) : Pos {

    init {
        Vars.groups.tiles.add(this)
    }

    fun update() {
        building?.update()
    }

    fun draw() {
        Draw.z(Layer.floor)
        Draw.rect(floor.sprite, xPos, yPos, Vars.tilesize.toFloat(), Vars.tilesize.toFloat())
        Draw.z()
        building?.draw()
    }
}