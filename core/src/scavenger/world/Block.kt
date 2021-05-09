package scavenger.world

import arc.Core
import arc.graphics.g2d.Draw
import scavenger.Vars
import scavenger.graphics.Drawf
import scavenger.interfaces.Pos

open class Block(name: String) {
    open var sprite = Core.atlas.find(name)
    open var solid = false

    inner class BlockEntity(override var xPos: Float, override var yPos: Float) : Pos {
        override fun getX() = xPos
        override fun getY() = yPos

        fun update() {
            draw()
        }

        fun draw() {
            if (solid) {
                Draw.z(Layer.shadow)
                Drawf.shadow(xPos, yPos, 1f * Vars.tilesize)
            }
            Draw.z(Layer.block)
            Draw.rect(sprite, xPos, yPos)
            Draw.z()
        }
    }
}