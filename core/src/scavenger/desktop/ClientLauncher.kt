package scavenger.desktop

import arc.ApplicationCore
import arc.Core
import arc.assets.AssetManager
import arc.graphics.Camera
import arc.graphics.g2d.Draw
import arc.graphics.g2d.SortedSpriteBatch
import arc.graphics.g2d.TextureAtlas
import arc.input.KeyCode
import arc.math.Mathf
import arc.scene.Scene
import scavenger.Vars
import kotlin.math.abs

open class ClientLauncher : ApplicationCore() {
    companion object {
        var scale = 1f
    }

    override fun setup() {
        Core.settings.appName = "Scavenger"
        Core.settings.load()
        Core.camera = Camera()
        Core.batch = SortedSpriteBatch()
        Core.atlas = TextureAtlas("sprites/sprites.atlas")
        Core.scene = Scene()
        Core.assets = AssetManager()

        Core.assets.load(Vars())
        Core.input.addProcessor(Vars.input)
        add(Vars.renderer)
    }

    override fun update() {
        super.update()

        scale = Mathf.clamp(Vars.input.scrollY + 1, Vars.minZoom.toFloat(), Vars.maxZoom.toFloat())

        Vars.world.eachTile {
            it.update()
        }

        Core.camera.width = Core.graphics.width.toFloat() / scale
        Core.camera.height = Core.graphics.height.toFloat() / scale

        Vars.input.update()
        Vars.input.apply {
            set(KeyCode.a) { if (Core.camera.position.x > 0) Core.camera.position.x -= speedMultiplier }
            set(KeyCode.w) { if (abs(Core.camera.position.y) < Vars.world.getWidth() * Vars.tilesize) Core.camera.position.y += speedMultiplier }
            set(KeyCode.s) { if (Core.camera.position.y > 0) Core.camera.position.y -= speedMultiplier }
            set(KeyCode.d) { if (abs(Core.camera.position.x) < Vars.world.getHeight() * Vars.tilesize) Core.camera.position.x += speedMultiplier }
            setUp(KeyCode.shiftLeft, { speedMultiplier = maxSpeed }, { speedMultiplier = defaultSpeed })
            setUp(KeyCode.shiftRight, { speedMultiplier = maxSpeed }, { speedMultiplier = defaultSpeed })
        }
    }

    override fun exit() {
        Draw.reset()
    }
}