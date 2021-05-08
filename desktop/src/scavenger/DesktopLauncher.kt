package scavenger

import arc.backend.sdl.SdlApplication
import arc.backend.sdl.SdlConfig
import arc.util.Log
import scavenger.desktop.ClientLauncher

open class DesktopLauncher(arg: Array<String>) : ClientLauncher() {
	companion object {
		@JvmStatic
		fun main(arg: Array<String>) {
			try {
				SdlApplication(
					DesktopLauncher(arg),
					SdlConfig().apply {
						title = "Scavenger"
						width = 900
						height = 700
					}
				)
			} catch (e: Throwable) {
				Log.err(e)
			}
		}
	}
}