package scavenger.math

import kotlin.math.abs

open class Mathm {
    companion object {
        fun closestAngle(current: Float, destination: Float, amount: Float): Float {
            val firstDistance = abs(destination - current)
            val secondDistance = 360 - firstDistance

            return if (firstDistance < secondDistance) {
                amount
            } else {
                -amount
            }
        }
    }
}