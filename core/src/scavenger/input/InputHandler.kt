package scavenger.input

import arc.input.InputProcessor
import arc.input.KeyCode
import arc.struct.ObjectMap
import arc.struct.Seq

open class InputHandler : InputProcessor {
    open var mouseX = 0
    open var mouseY = 0

    open val defaultSpeed = 4
    open val maxSpeed = 10
    open var speedMultiplier = defaultSpeed
    companion object {
        val heldKeys = Seq<KeyCode>()
        /** A map with a keycode, a () -> Unit for active, a () -> Unit for when the key is pressed down,
         * and a () -> Unit for when the key is lifted up */
        val keyMap = ObjectMap<KeyCode, Triple<() -> Unit, () -> Unit, () -> Unit>>()
    }

    fun getKeys(): Seq<KeyCode> {
        return heldKeys
    }

    override fun mouseMoved(screenX: Int, screenY: Int): Boolean {
        val value = screenX == mouseX && screenY == mouseY

        mouseX = screenX
        mouseY = screenY

        return !value
    }

    override fun keyDown(keycode: KeyCode?): Boolean {
        if (keycode == null) return false

        heldKeys.add(keycode)
        if (keyMap.containsKey(keycode)) keyMap.get(keycode).second()
        return true
    }

    override fun keyUp(keycode: KeyCode?): Boolean {
        if (keycode == null) return false

        if (heldKeys.contains(keycode)) heldKeys.remove(keycode)
        if (keyMap.containsKey(keycode)) keyMap.get(keycode).third()
        return true
    }

    open fun update() {
        heldKeys.each { k ->
            if (keyMap.containsKey(k)) keyMap.get(k).first()
        }
    }

    open fun set(keycode: KeyCode, active: () -> Unit, down: () -> Unit, up: () -> Unit) {
        keyMap.put(keycode, Triple(active, down, up))
    }

    open fun setDown(keycode: KeyCode, active: () -> Unit, down: () -> Unit) {
        keyMap.put(keycode, Triple(active, down, {}))
    }

    open fun setUp(keycode: KeyCode, active: () -> Unit, up: () -> Unit) {
        keyMap.put(keycode, Triple(active, {}, up))
    }

    open fun set(keycode: KeyCode, active: () -> Unit) {
        keyMap.put(keycode, Triple(active, {}, {}))
    }
}