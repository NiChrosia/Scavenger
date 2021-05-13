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
import kotlinx.coroutines.runBlocking
import scavenger.Vars
import scavenger.content.EntityTypes
import scavenger.entities.Player

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

        Vars.input.apply {
            set(KeyCode.a) { Core.camera.position.x -= speedMultiplier }
            set(KeyCode.w) { Core.camera.position.y += speedMultiplier }
            set(KeyCode.s) { Core.camera.position.y -= speedMultiplier }
            set(KeyCode.d) { Core.camera.position.x += speedMultiplier }
            setUp(KeyCode.shiftLeft, { speedMultiplier = maxSpeed }, { speedMultiplier = defaultSpeed })
            setUp(KeyCode.shiftRight, { speedMultiplier = maxSpeed }, { speedMultiplier = defaultSpeed })
        }

        Player(50f * Vars.tilesize, 50f * Vars.tilesize, EntityTypes.default)
    }

    override fun update() {
        super.update()

        fun update() = runBlocking {
            Vars.groups.tiles.each {
                it.update()
            }

            Vars.groups.entities.each {
                it.update()
            }

            Vars.input.update()
        }

        update()
    }

    override fun exit() {
        Draw.reset()
    }
}