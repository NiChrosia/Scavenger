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
            width: Float = region.width * 1.2f,
            height: Float = region.height * 1.2f,
            rotation: Float = 0f,
            alpha: Float = 1f
        ) {
		    Draw.z(Layer.shadow)
            Draw.color(Palette.shadow)
            Draw.alpha(alpha)
            Draw.rect(region, x, y, width, height, rotation)
            Draw.alpha(1f)
            Draw.color()
		    Draw.z()
        }
    }
}