package scavenger.entities

import arc.Core
import arc.graphics.g2d.Draw
import arc.input.KeyCode
import arc.math.Angles
import arc.math.geom.Vec2
import arc.struct.ObjectMap
import arc.struct.Seq
import scavenger.Vars
import scavenger.interfaces.Pos
import scavenger.world.Layer

open class Player(override var xPos: Float, override var yPos: Float, open val type: EntityType) : Pos {
    private var rotation = 0f
    private var direction: Float? = null
    private var moving = false
    private var keys = Seq<KeyCode>()
    open val keyMap = ObjectMap<KeyCode, Float>()

    init {
        keyMap.putAll(ObjectMap.of(
            KeyCode.a, 180f,
            KeyCode.w, 90f,
            KeyCode.s, 270f,
            KeyCode.d, 0f
        ))

        Vars.groups.entities.add(this)
        
        Vars.input.apply {
            setUp(KeyCode.a, { keys.add(KeyCode.a) }, { keys = Seq.with(); direction = null })
            setUp(KeyCode.w, { keys.add(KeyCode.w) }, { keys = Seq.with(); direction = null })
            setUp(KeyCode.s, { keys.add(KeyCode.s) }, { keys = Seq.with(); direction = null })
            setUp(KeyCode.d, { keys.add(KeyCode.d) }, { keys = Seq.with(); direction = null })
        }
    }

    fun update() {
        val directionSeq = Seq<Float>()
        keys.each {
            directionSeq.add(keyMap.get(it))
        }
        if (directionSeq.size > 0) direction = directionSeq.average().toFloat()

        Core.camera.position.set(xPos, yPos)

        move()
    }

    fun draw() {
        Draw.z(Layer.entity)
        Draw.rect(type.sprite, xPos, yPos, Vars.tilesize.toFloat(), Vars.tilesize.toFloat(), rotation)
        Draw.z()
    }

    /** Moves in a direction. Will automatically rotate to the direction if not already. */
    private fun move() {
        moving = true
        if (rotation != direction) {
            rotation = Angles.moveToward(rotation, direction ?: 0f, type.rotateSpeed)
        } else {
            rawMove(direction ?: 0f, type.speed / 60 * Vars.tilesize)
        }


        moving = false
    }

     /** Moves the player an amount in a direction. Not to be used directly. */
     private fun rawMove(direction: Float, amount: Float) {
         val vec = Vec2().trns(direction + 90f % 360, amount)
         xPos += vec.x
         yPos += vec.y
    }
}