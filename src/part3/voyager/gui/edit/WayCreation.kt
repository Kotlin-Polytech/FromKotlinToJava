package part3.voyager.gui.edit

import part3.voyager.world.Way
import part3.voyager.world.World

class WayCreation(val way: Way, world: World) : AbstractWorldEdit(world) {
    override fun getPresentationName(): String {
        return "Добавить путь из ${way.startName} в ${way.finishName}"
    }

    override fun redo() {
        world.addWay(way)
    }

    override fun undo() {
        world.removeWay(way)
    }
}