package part3.voyager.gui;

import java.awt.Color;

import part3.voyager.controller.Controller;
import part3.voyager.world.City;
import part3.voyager.world.World;

import java.awt.Graphics;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JPanel;
import javax.swing.undo.UndoManager;

import org.jdom.JDOMException;
import part3.voyager.world.Way;
import part3.voyager.world.Way.WayKind;

/**
 * Главная панель - содержит изображение мира,
 * слушает события изменения атрибутов
 *
 * @author Михаил Глухих
 */
public class VoyagerPanel extends JPanel implements InfoListener {

    /**
     * Внутренний радиус круга города
     */
    private static final int CITY_INT_RADIUS = 6;
    /**
     * Внешний радиус круга города
     */
    public static final int CITY_EXT_RADIUS = 20;

    /**
     * Мир
     */
    private World world;
    /**
     * Слушатель изменения текущего объекта
     */
    private CurrentListener currentListener;
    /**
     * Текущий город (null - нет)
     */
    private City currentCity;
    /**
     * Текущий путь (null - нет)
     */
    private Way currentWay;

    /**
     * Режим работы
     */
    private enum Mode {
        SELECT,        // Выбор
        ADD_CITY,      // Добавить город
        ADD_WAY        // Добавить путь
    }

    private Mode mode;

    private Controller controller;

    /**
     * Инициализация слушателей
     */
    private void initListeners() {
        /* Слушатель мыши */
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                onPress(e);
            }
        };
        addMouseListener(mouseListener);

        MouseMotionListener mouseMotionListener = new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                onDrag(e);
            }
        };
        addMouseMotionListener(mouseMotionListener);
    }

    /**
     * Обработчик нажатия на клавишу мыши
     *
     * @param e мышиное событие
     */
    private void onPress(MouseEvent e) {
        // Вызывается один из более мелких обработчиков,
        // в зависимости от режима и нажатой клавиши
        if (e.getButton() == MouseEvent.BUTTON1) {
            if (mode == Mode.ADD_CITY) {
                controller.onPressAddCity(e.getX(), e.getY());
            } else if (mode == Mode.ADD_WAY) {
                controller.onPressAddWay(e.getX(), e.getY());
            } else {
                controller.onPressSelect(e.getX(), e.getY());
            }
        } else if (e.getButton() == MouseEvent.BUTTON3) {
            if (mode == Mode.SELECT) {
                controller.onPressRemove(e.getX(), e.getY());
            }
        }
    }

    private void onDrag(MouseEvent e) {
        if (mode != Mode.SELECT ||
            currentCity == null) {
            return;
        }
        controller.onDragCurrentCity(e.getX(), e.getY());
    }

    /**
     * Конструктор
     *
     * @param listener слушатель изменения текущего объекта
     */
    VoyagerPanel(CurrentListener listener, UndoManager undoManager) {
        super();
        currentListener = listener;
        world = new World();
        controller = new Controller(world, this, undoManager);
        mode = Mode.SELECT;
        initListeners();
    }

    public City getCurrentCity() {
        return currentCity;
    }

    public void setCurrentCity(City city) {
        currentCity = city;
        currentListener.currentCityChanged(currentCity);
    }

    public Way getCurrentWay() {
        return currentWay;
    }

    public void setCurrentWay(Way way) {
        currentWay = way;
        currentListener.currentWayChanged(currentWay);
    }

    /**
     * Переустановка слушателя
     *
     * @param listener слушатель изменения текущего объекта
     */
    public void setListener(CurrentListener listener) {
        currentListener = listener;
        currentListener.currentCityChanged(currentCity);
        currentListener.currentWayChanged(currentWay);
    }

    /**
     * Режим добавления города
     */
    public void chooseCityMode() {
        mode = Mode.ADD_CITY;
    }

    /**
     * Режим добавления пути
     */
    public void chooseWayMode() {
        mode = Mode.ADD_WAY;
    }

    /**
     * Режим выбора
     */
    public void chooseSelectMode() {
        mode = Mode.SELECT;
    }

    /**
     * Прочитать мир из заданного файла
     *
     * @param file файл с сохраненным миром
     * @throws java.io.IOException              если файл не существует или недоступен
     * @throws java.lang.ClassNotFoundException при ошибке сериализации
     */
    public void openWorldFromFile(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(
                new FileInputStream(file));
        world = (World) inputStream.readObject();
        inputStream.close();
    }

    /**
     * Прочитать мир из заданного XML-файла
     *
     * @param file XML-файл с описанием мира
     * @throws IOException   при ошибке ввода-вывода
     * @throws JDOMException при ошибке разбора XML
     */
    public void openWorldFromXML(File file) throws IOException, JDOMException {
        world.readXML(file);
    }

    /**
     * Сохранить мир в заданный файл
     *
     * @param file файл, куда сохраняется мир
     * @throws java.io.IOException при ошибке создания файла
     */
    public void saveWorldToFile(File file) throws IOException {
        ObjectOutputStream outputStream = new ObjectOutputStream(
                new FileOutputStream(file));
        outputStream.writeObject(world);
        outputStream.close();
    }

    /**
     * Сохранить мир в заданном XML-файле
     *
     * @param file XML-файл
     * @throws IOException при ошибке ввода-вывода
     */
    public void saveWorldToXML(File file) throws IOException {
        world.writeXML(file);
    }

    /**
     * Обработчик изменения имени города
     *
     * @param name новое имя города
     */
    public void cityNameChanged(String name) {
        if (currentCity != null) {
            currentCity.setName(name);
            repaint();
        }
    }

    /**
     * Обработчик изменения типа пути
     *
     * @param kind новый тип пути
     */
    public void wayKindChanged(WayKind kind) {
        if (currentWay != null) {
            Way newWay = world.getWayByCities(currentWay.getStart(),
                    currentWay.getFinish(), kind);
            // Выбрать уже существующий путь такого типа
            if (newWay != null) {
                currentWay = newWay;
                currentListener.currentWayChanged(newWay);
            } else {
                // Или изменить тип выбранного пути
                currentWay.setKind(kind);
            }
            repaint();
        }
    }

    /**
     * Обработчик изменения стоимости пути
     *
     * @param cost новая стоимость пути
     */
    public void wayCostChanged(int cost) {
        if (currentWay != null)
            currentWay.setCost(cost);
    }

    /**
     * Обработчик изменения времени в пути
     *
     * @param time новое время в пути
     */
    public void wayTimeChanged(int time) {
        if (currentWay != null)
            currentWay.setTime(time);
    }

    /**
     * Рисуем заданный город
     *
     * @param g    графический контекст
     * @param city город
     */
    private void paintCity(Graphics g, City city) {
        g.setColor(Color.RED);
        g.fillOval(
                city.getX() - CITY_EXT_RADIUS,
                city.getY() - CITY_EXT_RADIUS,
                2 * CITY_EXT_RADIUS, 2 * CITY_EXT_RADIUS);
        if (city == currentCity) {
            g.setColor(Color.YELLOW);
            g.drawOval(
                    city.getX() - CITY_EXT_RADIUS,
                    city.getY() - CITY_EXT_RADIUS,
                    2 * CITY_EXT_RADIUS, 2 * CITY_EXT_RADIUS);
        }
        g.setColor(Color.WHITE);
        g.fillOval(
                city.getX() - CITY_INT_RADIUS,
                city.getY() - CITY_INT_RADIUS,
                2 * CITY_INT_RADIUS, 2 * CITY_INT_RADIUS);
        g.drawString(city.getName(), city.getX() + CITY_EXT_RADIUS, city.getY());
    }

    /**
     * Рисуем заданный путь
     *
     * @param g   графический контекст
     * @param way путь
     */
    private void paintWay(Graphics g, Way way) {
        int dx = 0, dy = 0;
        final City start = way.getStart();
        final City finish = way.getFinish();
        if (Math.abs(finish.getX() - start.getX()) >=
                Math.abs(finish.getY() - start.getY())) {
            dy = 5;
        } else {
            dx = 5;
        }
        switch (way.getKind()) {
            case BUS:
                g.setColor(way == currentWay ? Color.YELLOW : Color.RED);
                g.drawLine(start.getX() + dx, start.getY() + dy,
                        finish.getX() + dx, finish.getY() + dy);
                break;
            case TRAIN:
                g.setColor(way == currentWay ? Color.YELLOW : Color.LIGHT_GRAY);
                g.drawLine(start.getX(), start.getY(),
                        finish.getX(), finish.getY());
                break;
            case AIRCRAFT:
                g.setColor(way == currentWay ? Color.YELLOW : Color.BLUE);
                g.drawLine(start.getX() - dx, start.getY() - dy,
                        finish.getX() - dx, finish.getY() - dy);
                break;
        }
    }

    /**
     * Обработчик перерисовки
     *
     * @param g графический контекст
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (City city : world.getCities())
            paintCity(g, city);
        for (Way way : world.getWays())
            paintWay(g, way);
    }
}
