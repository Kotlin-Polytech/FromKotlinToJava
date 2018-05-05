package part3.voyager.controller;

import part3.voyager.gui.VoyagerPanel;
import part3.voyager.gui.edit.CityCreation;
import part3.voyager.gui.edit.WayCreation;
import part3.voyager.world.City;
import part3.voyager.world.Way;
import part3.voyager.world.World;

import javax.swing.undo.UndoManager;

public class Controller {

    private World world;

    private VoyagerPanel panel;

    private UndoManager undoManager;

    /**
     * Первая точка пути
     */
    private City startCity;

    public Controller(World world, VoyagerPanel panel, UndoManager undoManager) {
        this.world = world;
        this.panel = panel;
        this.undoManager = undoManager;
    }

    /**
     * Нажатие мыши в режиме добавления города
     *
     * @param x x-координата
     * @param y y-координата
     */
    public void onPressAddCity(int x, int y) {
        City city = new City("Город", x, y);
        world.addCity(city);
        undoManager.addEdit(new CityCreation(city, world));
        panel.setCurrentCity(city);
        panel.repaint();
    }

    /**
     * Нажатие мыши в режиме добавления пути
     *  @param x x-координата
     * @param y y-координата
     */
    public void onPressAddWay(int x, int y) {
        if (startCity == null) {
            onPressAddWayStart(x, y);
        } else {
            onPressAddWayFinish(x, y);
        }
    }

    /**
     * Нажатие мыши в режиме добавления пути (первая точка)
     *  @param x x-координата
     * @param y y-координата
     */
    private void onPressAddWayStart(int x, int y) {
        City startCity = world.getNearestCity(x, y);
        if (startCity != null &&
            startCity.getDistanceSquare(x, y) <= VoyagerPanel.CITY_EXT_RADIUS * VoyagerPanel.CITY_EXT_RADIUS) {
            this.startCity = startCity;
            panel.setCurrentCity(startCity);
            panel.repaint();
        }
    }

    /**
     * Нажатие мыши в режиме добавления пути (вторая точка)
     *  @param x x-координата
     * @param y y-координата
     */
    private void onPressAddWayFinish(int x, int y) {
        City finishCity = world.getNearestCity(x, y);
        if (finishCity != null && finishCity != startCity &&
            finishCity.getDistanceSquare(x, y) <= VoyagerPanel.CITY_EXT_RADIUS * VoyagerPanel.CITY_EXT_RADIUS) {
            Way.WayKind newWayKind = null;
            // Выбор свободного типа пути
            for (Way.WayKind kind : Way.WayKind.values()) {
                if (world.getWayByCities(startCity, finishCity, kind) == null) {
                    newWayKind = kind;
                    break;
                }
            }
            // Если свободный тип найден
            if (newWayKind != null) {
                Way newWay = new Way(startCity, finishCity, newWayKind,
                        1000, 2);
                world.addWay(newWay);
                undoManager.addEdit(new WayCreation(newWay, world));
                panel.setCurrentCity(finishCity);
                panel.setCurrentWay(newWay);
                panel.repaint();
                startCity = null;
            }
        }
    }

    /**
     * Нажатие мыши в режиме выбора
     *
     * @param x x-координата
     * @param y y-координата
     */
    public void onPressSelect(int x, int y) {
        City city = world.getNearestCity(x, y);
        if (city != null &&
                city.getDistanceSquare(x, y) <= VoyagerPanel.CITY_EXT_RADIUS * VoyagerPanel.CITY_EXT_RADIUS) {
            // Если удалось выбрать город
            panel.setCurrentCity(city);
            panel.repaint();
        } else {
            // Если нет - пытаемся выбрать путь
            final Way way = world.getWayByCoord(x, y);
            if (way != null) {
                panel.setCurrentWay(way);
                panel.repaint();
            }
        }
    }

    /**
     * Нажатие мыши в режиме удаления (правая кнопка)
     *
     * @param x x-координата
     * @param y y-координата
     */
    public void onPressRemove(int x, int y) {
        City city = world.getNearestCity(x, y);
        if (city != null &&
                city.getDistanceSquare(x, y) <= VoyagerPanel.CITY_EXT_RADIUS * VoyagerPanel.CITY_EXT_RADIUS) {
            // Удаление города и связанных путей
            world.removeCity(city);
            if (panel.getCurrentCity() == city) {
                panel.setCurrentCity(null);
            }
            // Если удален текущий путь ...
            Way currentWay = panel.getCurrentWay();
            if (currentWay != null &&
                    (currentWay.getStart() == city || currentWay.getFinish() == city)) {
                panel.setCurrentWay(null);
            }
        } else {
            // Удаление путей
            Way way = world.getWayByCoord(x, y);
            if (way != null) {
                world.removeWay(way);
                if (panel.getCurrentWay() == way) {
                    panel.setCurrentWay(null);
                }
            }
        }
        panel.repaint();
    }
}
