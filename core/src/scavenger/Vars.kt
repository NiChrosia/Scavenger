package scavenger

import arc.assets.Loadable
import scavenger.input.InputHandler
import scavenger.graphics.Renderer
import scavenger.world.World

open class Vars : Loadable {
    companion object {
        val renderer = Renderer()
        val input = InputHandler()
        val world = World()

        const val tilesize = 32

        const val minZoom = 0.025
        const val maxZoom = 10
    }
}