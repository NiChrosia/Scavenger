package scavenger.desktop

import arc.ApplicationCore
import arc.Core
import arc.assets.AssetManager
import arc.graphics.Camera
import arc.graphics.g2d.Draw
import arc.graphics.g2d.SortedSpriteBatch
import arc.graphics.g2d.TextureAtlas
import arc.input.KeyCode
import arc.scene.Scene
import arc.util.Log
import scavenger.Vars
import kotlin.math.abs

open class ClientLauncher : ApplicationCore() {
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

        Core.camera.width = Core.graphics.width.toFloat()
        Core.camera.height = Core.graphics.height.toFloat()

        Vars.input.update()
        Vars.input.apply {
            set(KeyCode.a) { if (abs(Core.camera.position.x) < Core.graphics.width / 2) Core.camera.position.x += speedMultiplier }
            set(KeyCode.w) { if (abs(Core.camera.position.y) < Core.graphics.height / 2) Core.camera.position.y -= speedMultiplier }
            set(KeyCode.s) { if (abs(Core.camera.position.y) < Core.graphics.height / 2) Core.camera.position.y += speedMultiplier }
            set(KeyCode.d) { if (abs(Core.camera.position.x) < Core.graphics.width / 2) Core.camera.position.x -= speedMultiplier }
            setUp(KeyCode.shiftLeft, { speedMultiplier = maxSpeed }, { speedMultiplier = defaultSpeed })
            setUp(KeyCode.shiftRight, { speedMultiplier = maxSpeed }, { speedMultiplier = defaultSpeed })
        }
        Log.info("Pos: ${Core.camera.position}")
    }

    override fun exit() {
        Draw.reset()
    }
}