package scavenger

import arc.assets.Loadable
import scavenger.input.InputHandler
import scavenger.render.Renderer

open class Vars : Loadable {
    companion object {
        val renderer = Renderer()
        val input = InputHandler()
    }
}