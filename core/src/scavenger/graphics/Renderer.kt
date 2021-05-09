package scavenger.graphics

import arc.ApplicationListener
import arc.Core
import arc.graphics.Color
import arc.graphics.g2d.Draw
import scavenger.desktop.ClientLauncher
import scavenger.world.Layer

open class Renderer : ApplicationListener {
    override fun update() {
        Core.camera.update()
        Core.graphics.clear(Color.black)
        Draw.proj(Core.camera)

        val mouse = Core.input.mouseWorld()
        val texture = Core.atlas.find("cursor")

        Draw.z(Layer.cursor)
        Draw.rect(
            texture,
            mouse.x,
            mouse.y,
            texture.width / ClientLauncher.scale,
            texture.height / ClientLauncher.scale
        )
        Draw.z()
        Draw.flush()
    }

    override fun resize(width: Int, height: Int) {
        Core.camera.resize(width.toFloat(), height.toFloat())
    }
}