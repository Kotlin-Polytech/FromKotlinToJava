/*
 * Way.java
 */

package part3.voyager.world;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jdom.Element;

/**
 * Описание пути между двумя городами
 *
 * @author Михаил Глухих
 */
@SuppressWarnings("WeakerAccess")
public class Way implements Externalizable {

    /**
     * Тип пути
     */
    public enum WayKind {
        /**
         * Автобус
         */
        BUS,
        /**
         * Поезд
         */
        TRAIN,
        /**
         * Самолет
         */
        AIRCRAFT;

        /**
         * Конвертация типа в строку
         *
         * @return текстовое описание типа
         */
        @Override
        public String toString() {
            switch (this) {
                case BUS:
                    return "Автобус";
                case TRAIN:
                    return "Поезд";
                case AIRCRAFT:
                    return "Самолет";
                default:
                    return "Пеший";
            }
        }

        static public WayKind parse(final String string) {
            for (WayKind kind : WayKind.values()) {
                if (kind.toString().equals(string))
                    return kind;
            }
            return null;
        }
    }

    /**
     * Начало и конец
     */
    private City start, finish;
    /**
     * Тип
     */
    private WayKind kind;
    /**
     * Стоимость и время в пути
     */
    private int cost, time;

    /**
     * Конструктор
     *
     * @param start  начало
     * @param finish конец
     * @param kind   тип
     * @param cost   стоимость
     * @param time   время в пути
     */
    public Way(City start, City finish, WayKind kind, int cost, int time) {
        this.start = start;
        this.finish = finish;
        this.kind = kind;
        this.cost = cost;
        this.time = time;
    }

    /**
     * Находится ли точка внутри пути
     *
     * @param x x-координата
     * @param y y-координата
     * @return true, если находится
     */
    public boolean isInside(int x, int y) {
        int x1 = start.getX(), x2 = finish.getX();
        int y1 = start.getY(), y2 = finish.getY();
        if ((x - x1) * (x - x2) > 0 || (y - y1) * (y - y2) > 0)
            return false;
        else {
            return Math.abs((x2 - x) * (x2 - x1) + (y2 - y) * (y2 - y1)) >
                    0.9 * Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1)) *
                            Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
        }
    }

    /**
     * Соединен ли путь с данным городом
     *
     * @param city данный город
     * @return true, если соединен
     */
    public boolean connectedWith(City city) {
        return city == start || city == finish;
    }

    /**
     * Получить имя стартового города
     *
     * @return имя стартового города
     */
    public String getStartName() {
        return start.getName();
    }

    /**
     * Получить стартовый город
     *
     * @return стартовый город
     */
    public City getStart() {
        return start;
    }

    /**
     * Получить конечный город
     *
     * @return конечный город
     */
    public City getFinish() {
        return finish;
    }

    /**
     * Получить имя конечного города
     *
     * @return имя конечного города
     */
    public String getFinishName() {
        return finish.getName();
    }

    /**
     * Получить тип пути
     *
     * @return тип пути
     */
    public WayKind getKind() {
        return kind;
    }

    /**
     * Получить стоимость
     *
     * @return стоимость
     */
    public int getCost() {
        return cost;
    }

    /**
     * Получить время в пути
     *
     * @return время в пути
     */
    public int getTime() {
        return time;
    }

    /**
     * Установить тип пути
     *
     * @param kind новый тип
     */
    public void setKind(WayKind kind) {
        this.kind = kind;
    }

    /**
     * Установить стоимость пути
     *
     * @param cost новая стоимость пути
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * Установить время в пути
     *
     * @param time новое время в пути
     */
    public void setTime(int time) {
        this.time = time;
    }

    /**
     * Установить стартовый город
     *
     * @param start стартовый город
     */
    void setStart(City start) {
        this.start = start;
    }

    /**
     * Установить конечный город
     *
     * @param finish конечный город
     */
    void setFinish(City finish) {
        this.finish = finish;
    }

    /**
     * Совпадают ли два пути
     *
     * @param obj второй путь
     * @return true, если совпадают
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Way) {
            Way way = (Way) obj;
            return (start == way.start) &&
                    (finish == way.finish) &&
                    (kind == way.kind);
        } else return false;
    }

    /**
     * Получение хэш-кода
     *
     * @return хэш-код
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.start != null ? this.start.hashCode() : 0);
        hash = 97 * hash + (this.finish != null ? this.finish.hashCode() : 0);
        hash = 97 * hash + (this.kind != null ? this.kind.hashCode() : 0);
        return hash;
    }

    /**
     * Запись пути в объектный поток
     *
     * @param out объектный поток
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Версия
        out.writeInt(1);
        // Информация
        out.writeInt(kind.ordinal());
        out.writeInt(cost);
        out.writeInt(time);
    }

    /**
     * Чтение пути из объектного потока
     *
     * @param in объектный поток
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException {
        // Версия
        final int version = in.readInt();
        if (version > 1)
            throw new IOException("Ожидалась версия пути 1, а получили " + version);
        // Информация
        final int kindNum = in.readInt();
        for (WayKind wk : WayKind.values()) {
            if (wk.ordinal() == kindNum) {
                kind = wk;
                break;
            }
        }
        cost = in.readInt();
        time = in.readInt();
    }

    /**
     * Сформировать XML-элемент по пути
     *
     * @return соответствующий XML-элемент
     */
    public Element getXMLElement() {
        final Element root = new Element("way");
        root.setAttribute("kind", kind.toString());
        root.setAttribute("time", String.valueOf(time));
        root.setAttribute("cost", String.valueOf(cost));
        return root;
    }

    /**
     * Сформировать путь по XML-элементу
     *
     * @param element XML-элемент
     * @return соответствующий путь
     */
    public static Way readXML(Element element) {
        return new Way(null, null,
                WayKind.parse(element.getAttributeValue("kind")),
                Integer.parseInt(element.getAttributeValue("cost")),
                Integer.parseInt(element.getAttributeValue("time")));
    }
}
