package scavenger.world

import arc.func.Cons

open class World {
    private var tiles = TileContainer(100, 100)

    var width = tiles.width
    var height = tiles.height

    open fun tile(x: Int, y: Int): Tile {
        return tiles.get(x, y)
    }

    open fun eachTile(cons: Cons<Tile>) {
        tiles.each(cons)
    }
}