package scavenger.graphics

import arc.Core
import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion
import scavenger.world.Layer

open class Drawf {
    companion object {
        fun shadow(
            x: Float,
            y: Float,
            region: TextureRegion = Core.atlas.find("circle-shadow"),
            width: Int = region.width,
            height: Int = region.height,
            rotation: Float = 0f,
            alpha: Float = 1f
        ) {
		    Draw.z(Layer.shadow)
            Draw.color(Palette.shadow)
            Draw.alpha(alpha)
            Draw.rect(region, x, y, width.toFloat(), height.toFloat(), rotation)
            Draw.alpha(1f)
            Draw.color()
		    Draw.z()
        }
    }
}