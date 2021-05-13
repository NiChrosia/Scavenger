package scavenger.graphics

import arc.ApplicationListener
import arc.Core
import arc.graphics.Color
import arc.graphics.g2d.Draw
import scavenger.Vars
import scavenger.world.Layer
import arc.math.Mathf
import arc.math.geom.Circle
import kotlinx.coroutines.runBlocking

open class Renderer : ApplicationListener {
    open val circleSize = 70f * Vars.tilesize

    companion object {
        var scale = 0f
    }

    override fun update() {
        Core.camera.update()
        Core.graphics.clear(Color.black)
        Draw.proj(Core.camera)
        Draw.sort(true)

        scale = Mathf.clamp(Vars.input.scrollY + 1, Vars.minZoom, Vars.maxZoom)
        Core.camera.width = Core.graphics.width.toFloat() / scale
        Core.camera.height = Core.graphics.height.toFloat() / scale

        val circle = Circle(
            Core.camera.position.x,
            Core.camera.position.y,
            circleSize / scale
        )

        fun draw() = runBlocking {
            Vars.groups.tiles.each { tile ->
                if (circle.contains(tile.x, tile.y)) tile.draw()
            }

            Vars.groups.entities.each {
                if (circle.contains(it.x, it.y)) it.draw()
            }
        }

        draw()

        val mouse = Core.input.mouseWorld()
        val texture = Core.atlas.find("cursor")

        Draw.z(Layer.cursor)
        Draw.rect(
            texture,
            mouse.x,
            mouse.y,
            texture.width / scale,
            texture.height / scale
        )
        Draw.z()
        Draw.flush()
    }

    override fun resize(width: Int, height: Int) {
        Core.camera.resize(width.toFloat(), height.toFloat())
    }
}