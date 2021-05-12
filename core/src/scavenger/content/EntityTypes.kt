package scavenger.content

import scavenger.entities.EntityType

class EntityTypes {
    companion object {
        val default = EntityType("default").apply {
            rotateSpeed = 10f
        }
    }
}