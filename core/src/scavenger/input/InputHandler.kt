package scavenger.input

import arc.input.InputProcessor
import arc.input.KeyCode
import arc.math.Mathf
import arc.struct.ObjectMap
import arc.struct.Seq
import scavenger.Vars

open class InputHandler : InputProcessor {
    open var mouseX = 0
    open var mouseY = 0

    open val defaultSpeed = 4
    open val maxSpeed = 10
    open var speedMultiplier = defaultSpeed

    open var scrollX = 0f
    open var scrollY = 0f

    companion object {
        val heldKeys = Seq<KeyCode>()
        /** A map with a keycode, a () -> Unit for active, a () -> Unit for when the key is pressed down,
         * and a () -> Unit for when the key is lifted up */
        val keyMap = ObjectMap<KeyCode, Seq<Triple<() -> Unit, () -> Unit, () -> Unit>>>()
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
        if (keyMap.containsKey(keycode)) keyMap.get(keycode).each {
            it.second()
        }
        return true
    }

    override fun keyUp(keycode: KeyCode?): Boolean {
        if (keycode == null) return false

        if (heldKeys.contains(keycode)) heldKeys.remove(keycode)
        if (keyMap.containsKey(keycode)) keyMap.get(keycode).each {
            it.third()
        }
        return true
    }

    open fun update() {
        heldKeys.each { k ->
            if (keyMap.containsKey(k)) keyMap.get(k).each {
                it.first()
            }
        }

        scrollY = Mathf.clamp(scrollY, Vars.minZoom, Vars.maxZoom)
    }

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        scrollX += -(amountX * 0.2f)
        scrollY += -(amountY * 0.2f)

        scrollY = Mathf.clamp(scrollY, Vars.minZoom, Vars.maxZoom)

        return amountX != 0f && amountY != 0f
    }

    open fun set(keycode: KeyCode, active: () -> Unit, down: () -> Unit, up: () -> Unit) {
        val input = Seq.with(Triple(active, down, up))

        if (keyMap.containsKey(keycode)) {
            keyMap.get(keycode).addAll(input)
        } else {
            keyMap.put(keycode, input)
        }
    }

    open fun set(keycode: KeyCode, active: () -> Unit) {
        set(keycode, active, {}, {})
    }

    open fun setDown(keycode: KeyCode, active: () -> Unit, down: () -> Unit) {
        set(keycode, active, down, {})
    }

    open fun setUp(keycode: KeyCode, active: () -> Unit, up: () -> Unit) {
        set(keycode, active, {}, up)
    }
}