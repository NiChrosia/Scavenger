package scavenger.entities

import arc.Core
import arc.graphics.g2d.Draw
import arc.input.KeyCode
import arc.math.Angles
import arc.math.geom.Rect
import arc.math.geom.Vec2
import arc.struct.ObjectMap
import arc.struct.Seq
import arc.util.Log
import scavenger.Vars
import scavenger.content.Blocks
import scavenger.graphics.Drawf
import scavenger.interfaces.Pos
import scavenger.world.Block
import scavenger.world.Layer
import kotlin.math.abs

open class Player(
    override var xPos: Float,
    override var yPos: Float,
    open val type: EntityType,
    ) : Pos {
    private var rotation = 0f
    private var direction: Float = 0f
    private var moving = false
    private var keys = Seq<KeyCode>()
    open val keyMap = ObjectMap<KeyCode, Float>()

    init {
        keyMap.putAll(ObjectMap.of(
            KeyCode.a, 90f,
            KeyCode.w, 0f,
            KeyCode.s, 180f,
            KeyCode.d, 270f
        ))

        Vars.groups.entities.add(this)
        Vars.groups.hittable.add(this)
        
        Vars.input.apply {
            setUp(KeyCode.a, { keys.add(KeyCode.a); moving = true }, { keys = Seq.with(); moving = false })
            setUp(KeyCode.w, { keys.add(KeyCode.w); moving = true }, { keys = Seq.with(); moving = false })
            setUp(KeyCode.s, { keys.add(KeyCode.s); moving = true }, { keys = Seq.with(); moving = false })
            setUp(KeyCode.d, { keys.add(KeyCode.d); moving = true }, { keys = Seq.with(); moving = false })
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
        Drawf.shadow(xPos - 2f,
            yPos - 2f,
            type.sprite,
            Vars.tilesize.toFloat(),
            Vars.tilesize.toFloat(),
            alpha = 0.6f,
            rotation = rotation
        )

        Draw.z(Layer.entity)
        Draw.rect(type.sprite, xPos, yPos, Vars.tilesize.toFloat(), Vars.tilesize.toFloat(), rotation)
        Draw.z()
    }

    /** Moves in a direction. Will automatically rotate to the direction if not already. */
    private fun move() {
        if (moving) {
            rotation = Angles.moveToward(rotation, direction, type.rotateSpeed)
            val speed = type.speed / 60 * Vars.tilesize / if (abs(direction - rotation) > 0) abs(direction - rotation) else 1f

            var collides = false
            val tilesize = Vars.tilesize / 1f

            Vars.groups.hittable.each {
                if (it != this) {
                    if (it is Pos) {
                        if (it is Block.Building) if (!it.block.solid) return@each

                        if (Rect(
                                it.x - tilesize / 2f,
                                it.y - tilesize / 2f,
                                tilesize,
                                tilesize
                            ).contains(Vec2().trns(rotation, speed).apply {
                                add(xPos, yPos)
                            })) {
                                collides = collides || true

                                Log.info(Vec2(it.x, it.y))
                                Log.info(Vec2(xPos, yPos))
                        }
                    }
                }
            }

            Log.info(collides)

            if (!collides) rawMove(direction, speed)
        }
    }

     /** Moves the player an amount in a direction. Not to be used directly. */
     private fun rawMove(direction: Float, amount: Float) {
         val vec = Vec2().trns(direction + 90f % 360, amount)
         xPos += vec.x
         yPos += vec.y
    }
}