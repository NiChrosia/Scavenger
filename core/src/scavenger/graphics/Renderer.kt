package scavenger.graphics

import arc.ApplicationListener
import arc.Core
import arc.graphics.Color
import arc.graphics.g2d.Draw
import scavenger.input.InputHandler

open class Renderer : ApplicationListener {
    open var input = InputHandler()

    override fun update() {
        Core.camera.update()
        Core.graphics.clear(Color.black)
        Draw.proj(Core.camera)

        val mouse = Core.input.mouseWorld()
        val texture = Core.atlas.find("ohno")

        Draw.color(Color.red)
        Draw.rect(texture, 0f, 0f)
        Draw.color()
        Draw.rect(texture, mouse.x, mouse.y)
        Draw.flush()
    }

    override fun resize(width: Int, height: Int) {
        Core.camera.resize(width.toFloat(), height.toFloat())
    }
}