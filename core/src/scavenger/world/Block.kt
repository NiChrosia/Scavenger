package scavenger.world

import arc.Core
import arc.graphics.g2d.Draw
import scavenger.Vars
import scavenger.graphics.Drawf
import scavenger.interfaces.Pos

open class Block(name: String) {
    open var sprite = Core.atlas.find(name)
    open var solid = false

    inner class Building(override var xPos: Float, override var yPos: Float) : Pos {
        val block = this@Block

        init {
            Vars.groups.blocks.add(this)
        }

        fun update() {

        }

        fun draw() {
            if (solid) {
                Draw.z(Layer.shadow)
                Drawf.shadow(xPos, yPos, 1f * Vars.tilesize)
            }
            Draw.z(Layer.block)
            Draw.rect(sprite, xPos, yPos, Vars.tilesize.toFloat(), Vars.tilesize.toFloat())
            Draw.z()
        }
    }
}