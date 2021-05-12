package scavenger

import arc.backend.sdl.SdlApplication
import arc.backend.sdl.SdlConfig
import scavenger.desktop.ClientLauncher

fun main(arg: Array<String>) {
	SdlApplication(
		ClientLauncher(),
		SdlConfig().apply {
			title = "Scavenger"
			width = 900
			height = 700
			maximized = true
		}
	)
}