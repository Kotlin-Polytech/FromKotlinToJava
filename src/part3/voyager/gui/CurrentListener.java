/*
 * CurrentListener.java
 */

package part3.voyager.gui;

import part3.voyager.world.City;
import part3.voyager.world.Way;

/**
 * Интерфейс слушателя событий
 * выбора текущего объекта
 * @author Михаил Глухих
 */
public interface CurrentListener {
    /**
     * Обработчик выбора текущего города
     * @param city выбранный город
     */
    void currentCityChanged(City city);
    /**
     * Обработчик выбора текущего пути
     * @param way выбранный путь
     */
    void currentWayChanged(Way way);
}
