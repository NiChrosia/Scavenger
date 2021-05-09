package scavenger.world

import arc.Core
import arc.graphics.g2d.Draw
import scavenger.interfaces.Pos

open class Floor(open val name: String) {
    var sprite = Core.atlas.find(name)

    inner class FloorEntity(override var xPos: Float, override var yPos: Float) : Pos {
        override fun getX() = xPos
        override fun getY() = yPos

        fun update() {
            draw()
        }

        fun draw() {
            Draw.z(Layer.floor)
            Draw.rect(sprite, xPos, yPos)
            Draw.z()
        }
    }
}