package scavenger.content

import scavenger.entities.EntityType

class EntityTypes {
    companion object {
        val default = EntityType("default").apply {
            rotateSpeed = 4f
            speed = 10f
        }
    }
}