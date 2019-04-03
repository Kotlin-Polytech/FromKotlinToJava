/*
 * City.java
 */

package part3.voyager.world;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import org.jdom.Element;

/**
 * Класс, описывающий отдельный город
 *
 * @author Михаил Глухих
 */
@SuppressWarnings("WeakerAccess")
public class City implements Externalizable {
    /**
     * Имя города
     */
    private String name;
    /**
     * Координаты города
     */
    private int x, y;

    /**
     * Конструктор
     *
     * @param name имя города
     * @param x    x-координата
     * @param y    y-координата
     */
    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    /**
     * Доступ к координатам
     *
     * @return x-координата
     */
    public int getX() {
        return x;
    }

    /**
     * Доступ к координатам
     *
     * @return y-координата
     */
    public int getY() {
        return y;
    }

    /**
     * Определяет квадрат расстояния от точки внутри города
     *
     * @param x x-координата
     * @param y y-координата
     * @return квадрат расстояния
     */
    public int getDistanceSquare(int x, int y) {
        return (x - this.x) * (x - this.x) + (y - this.y) * (y - this.y);
    }

    /**
     * Установка координат
     *
     * @param x x-координата
     * @param y y-координата
     */
    public void setCoord(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Получение имени
     *
     * @return имя города
     */
    public String getName() {
        return name;
    }

    /**
     * Установка имени
     *
     * @param name новое имя города
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Запись города в объектный поток
     *
     * @param out объектный поток
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Версия
        out.writeInt(1);
        // Информация
        out.writeUTF(name);
        out.writeInt(x);
        out.writeInt(y);
    }

    /**
     * Чтение города из объектного потока
     *
     * @param in объектный поток
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException {
        // Версия
        final int version = in.readInt();
        if (version > 1)
            throw new IOException("Ожидалась версия города 1, а получили " + version);
        // Информация
        name = in.readUTF();
        x = in.readInt();
        y = in.readInt();
    }

    /**
     * Сформировать XML-элемент по городу
     *
     * @return соответствующий XML-элемент
     */
    public Element getXMLElement() {
        final Element root = new Element("city");
        root.setAttribute("name", name);
        root.setAttribute("x", String.valueOf(x));
        root.setAttribute("y", String.valueOf(y));
        return root;
    }

    /**
     * Сформировать город по XML-элементу
     *
     * @param element XML-элемент
     * @return соответствующий город
     */
    public static City readXML(Element element) {
        return new City(element.getAttributeValue("name"),
                Integer.parseInt(element.getAttributeValue("x")),
                Integer.parseInt(element.getAttributeValue("y")));
    }
}
