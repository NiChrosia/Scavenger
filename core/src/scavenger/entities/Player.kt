package scavenger.entities

import arc.graphics.g2d.Draw
import arc.input.KeyCode
import arc.math.geom.Vec2
import scavenger.Vars
import scavenger.interfaces.Pos
import scavenger.math.Mathm
import scavenger.world.Layer

class Player(override var xPos: Float, override var yPos: Float, val type: EntityType) : Pos {
    private var location = Vec2(xPos, yPos)
    private var rotation = 0f

    init {
        Vars.groups.entities.add(this)
        
        Vars.input.apply {
            set(KeyCode.a) { move(180f) }
            set(KeyCode.w) { move(90f) }
            set(KeyCode.s) { move(270f) }
            set(KeyCode.d) { move(0f) }
        }
    }

     fun update() {

    }

     fun draw() {
        Draw.z(Layer.entity)
        Draw.rect(type.sprite, location.x, location.y, rotation)
        Draw.z()
    }

     fun move(direction: Float) {
        if (rotation != direction) {
            rawRotate(Mathm.closestAngle(rotation, direction, type.rotateSpeed))
        } else {
            rawMove(direction, type.rotateSpeed / 60 * Vars.tilesize)
        }
    }

     /** Moves the player an amount in a direction. Not to be used directly. */
     private fun rawMove(direction: Float, amount: Float) {
        location.trns(direction, amount)
    }

     /** Rotates the player the specified amount. Not to be used directly. */
     private fun rawRotate(amount: Float) {
        rotation += amount
    }
}