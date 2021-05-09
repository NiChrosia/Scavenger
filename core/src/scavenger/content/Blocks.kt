package scavenger.content

import scavenger.world.Block

open class Blocks {
    companion object {
        var crate = Block("crate").apply {
            solid = true
        }
        var air = Block("air")
    }
}