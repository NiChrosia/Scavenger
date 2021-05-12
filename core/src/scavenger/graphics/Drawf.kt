package scavenger.graphics

import arc.graphics.g2d.Draw
import arc.graphics.g2d.TextureRegion

open class Drawf {
    companion object {
        fun shadow(x: Float, y: Float, radius: Float) {
            shadow(x, y, radius, 1f)
        }

        fun shadow(x: Float, y: Float, radius: Float, alpha: Float) {
            Draw.color(Palette.shadow)
            Draw.alpha(alpha)
            Draw.rect("circle-shadow", x, y, radius * 2, radius * 2)
            Draw.alpha(1f)
            Draw.color()
        }

        fun shadow(region: TextureRegion, x: Float, y: Float) {
            shadow(region, x, y, 0f)
        }

        fun shadow(region: TextureRegion, x: Float, y: Float, rotation: Float) {
            Draw.color(Palette.shadow)
            Draw.rect(region, x, y, rotation)
            Draw.color()
        }
    }
}