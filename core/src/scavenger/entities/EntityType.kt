package scavenger.entities

import arc.Core
import scavenger.entities.ai.DefaultAI

open class EntityType(open val name: String) {
    open val sprite = Core.atlas.find(name)

    open var speed = 1f
    open var rotateSpeed = 1f
    open var health = 10f
    open var ai = DefaultAI()
}