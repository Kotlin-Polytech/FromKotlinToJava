package part3.voyager.gui;

import part3.voyager.world.City;

import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import part3.voyager.world.Way;
import part3.voyager.world.Way.WayKind;

/**
 * Панель информации отображает данные о текущем объекте,
 * обрабатывает события изменения текущего объекта
 *
 * @author Михаил Глухих
 */
@SuppressWarnings("WeakerAccess")
public class InfoPanel extends JPanel implements CurrentListener {
    /**
     * Текстовое поле
     */
    private JTextField cityName;
    /**
     * Список выбора
     */
    private JComboBox wayKind;
    /**
     * Счетчик
     */
    private JSpinner wayCost;
    private JSpinner wayTime;

    /**
     * Слушатель изменения атрибутов
     */
    private InfoListener infoListener;

    /**
     * Инициализация слушателей всех событий
     */
    private void initListeners() {
        // Изменение имени города
        cityName.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (infoListener != null)
                    infoListener.cityNameChanged(cityName.getText());
            }
        });
        // Изменение типа пути
        wayKind.addActionListener(e -> {
            if (infoListener != null)
                infoListener.wayKindChanged(getWayKind());
        });
        // Изменение стоимости пути
        wayCost.addChangeListener(e -> {
            if (infoListener != null)
                infoListener.wayCostChanged((Integer) wayCost.getValue());
        });
        // Изменение времени в пути
        wayTime.addChangeListener(e -> {
            if (infoListener != null)
                infoListener.wayTimeChanged((Integer) wayTime.getValue());
        });
    }

    /**
     * Конструктор
     *
     * @param listener слушатель изменения атрибутов
     */
    public InfoPanel(InfoListener listener) {
        super();
        // Выбор размещения в одну колонку
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Создание и добавление компонентов
        JLabel cityLabel = new JLabel("Название города");
        add(cityLabel);
        cityName = new JTextField(15);
        cityName.setMaximumSize(new Dimension(300, 25));
        add(cityName);
        JLabel kindLabel = new JLabel("Вид пути");
        add(kindLabel);
        String[] kindNames = {
                WayKind.BUS.toString(),
                WayKind.TRAIN.toString(),
                WayKind.AIRCRAFT.toString()
        };
        wayKind = new JComboBox<>(kindNames);
        wayKind.setMaximumSize(new Dimension(300, 25));
        add(wayKind);
        JLabel costLabel = new JLabel("Стоимость пути");
        add(costLabel);
        wayCost = new JSpinner(new SpinnerNumberModel(1000, 100, 10000, 100));
        wayCost.setMaximumSize(new Dimension(300, 25));
        add(wayCost);
        JLabel timeLabel = new JLabel("Время в пути");
        add(timeLabel);
        wayTime = new JSpinner(new SpinnerNumberModel(1, 0, 20, 1));
        wayTime.setMaximumSize(new Dimension(300, 25));
        add(wayTime);
        this.infoListener = listener;
        initListeners();
    }

    /**
     * Переустановка слушателя
     *
     * @param listener новый слушатель изменения атрибутов
     */
    public void setListener(InfoListener listener) {
        infoListener = listener;
    }

    /**
     * Установка имени города
     *
     * @param name имя города
     */
    public void setCityName(String name) {
        cityName.setEnabled(true);
        cityName.setText(name);
    }

    /**
     * Получение типа пути
     *
     * @return тип пути
     */
    public WayKind getWayKind() {
        switch (wayKind.getSelectedIndex()) {
            case 0:
                return WayKind.BUS;
            case 1:
                return WayKind.TRAIN;
            default:
                return WayKind.AIRCRAFT;
        }
    }

    /**
     * Установка типа пути
     *
     * @param kind новый тип пути
     */
    public void setWayKind(WayKind kind) {
        wayKind.setEnabled(true);
        switch (kind) {
            case BUS:
                wayKind.setSelectedIndex(0);
                break;
            case TRAIN:
                wayKind.setSelectedIndex(1);
                break;
            case AIRCRAFT:
                wayKind.setSelectedIndex(2);
                break;
        }
    }

    /**
     * Установка стоимости пути
     *
     * @param cost новая стоимость пути
     */
    public void setWayCost(int cost) {
        wayCost.setEnabled(true);
        wayCost.setValue(cost);
    }

    /**
     * Установка времени в пути
     *
     * @param time новое время в пути
     */
    public void setWayTime(int time) {
        wayTime.setEnabled(true);
        wayTime.setValue(time);
    }

    /**
     * Обработчик выбора текущего города
     *
     * @param city выбранный город
     */
    public void currentCityChanged(City city) {
        if (city == null) {
            cityName.setEnabled(false);
        } else {
            this.setCityName(city.getName());
        }
    }

    /**
     * Обработчик выбора текущего пути
     *
     * @param way выбранный путь
     */
    public void currentWayChanged(Way way) {
        if (way == null) {
            wayKind.setEnabled(false);
            wayCost.setEnabled(false);
            wayTime.setEnabled(false);
        } else {
            this.setWayKind(way.getKind());
            this.setWayCost(way.getCost());
            this.setWayTime(way.getTime());
        }
    }
}
