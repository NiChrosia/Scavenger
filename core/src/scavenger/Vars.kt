package scavenger

import arc.assets.Loadable
import scavenger.input.InputHandler
import scavenger.graphics.Renderer

open class Vars : Loadable {
    companion object {
        val renderer = Renderer()
        val input = InputHandler()
    }
}