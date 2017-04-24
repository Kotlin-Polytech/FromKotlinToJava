package part3.voyager.gui.edit

import part3.voyager.world.City
import part3.voyager.world.World

class CityCreation(val city: City, world: World) : AbstractWorldEdit(world) {
    override fun getPresentationName(): String {
        return "Добавить город ${city.name}"
    }

    override fun redo() {
        world.addCity(city)
    }

    override fun undo() {
        world.removeCity(city)
    }
}