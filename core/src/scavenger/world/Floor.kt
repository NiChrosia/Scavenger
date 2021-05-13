package scavenger.world

import arc.Core
import arc.graphics.g2d.TextureAtlas

open class Floor(override val name: String) : Block(name) {
    override var sprite: TextureAtlas.AtlasRegion = Core.atlas.find("floor-$name")
}