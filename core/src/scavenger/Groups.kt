package scavenger

import arc.struct.Seq
import scavenger.entities.Player
import scavenger.world.Block
import scavenger.world.Tile

open class Groups {
    val entities = Seq<Player>()
    val blocks = Seq<Block.Building>()
    val tiles = Seq<Tile>()
}