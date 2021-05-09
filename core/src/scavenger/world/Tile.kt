package scavenger.world

import scavenger.interfaces.Pos

open class Tile(open var floor: Floor.FloorEntity, open var block: Block.BlockEntity) : Pos {
    override var xPos = 0f
    override fun getX() = xPos
    
    override var yPos = 0f
    override fun getY() = yPos

    fun update() {
        floor.update()
        block.update()
    }
}