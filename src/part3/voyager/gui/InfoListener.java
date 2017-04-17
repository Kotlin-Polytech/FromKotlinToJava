package part3.voyager.gui;

import part3.voyager.world.Way.WayKind;

/**
 * Интерфейс слушателя событий
 * изменения свойств текущего объекта
 * @author Михаил Глухих
 */
public interface InfoListener {

    /**
     * Обработчик изменения имени города
     * @param name новое имя города
     */
    void cityNameChanged(String name);
    /**
     * Обработчик изменения типа пути
     * @param kind новый тип пути
     */
    void wayKindChanged(WayKind kind);
    /**
     * Обработчик изменения стоимости пути
     * @param cost новая стоимость пути
     */
    void wayCostChanged(int cost);
    /**
     * Обработчик изменения времени в пути
     * @param time новое время в пути
     */
    void wayTimeChanged(int time);
}
