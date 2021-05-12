package scavenger

import arc.assets.Loadable
import scavenger.input.InputHandler
import scavenger.graphics.Renderer
import scavenger.world.World

open class Vars : Loadable {
    companion object {
        val groups = Groups()

        val renderer = Renderer()
        val input = InputHandler()
        val world = World()

        const val tilesize = 16

        const val minZoom = 0.025f
        const val maxZoom = 10f
    }
}