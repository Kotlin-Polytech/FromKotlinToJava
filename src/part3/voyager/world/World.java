package part3.voyager.world;

import java.io.Externalizable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import part3.voyager.world.Way.WayKind;

/**
 * Мир - города и пути
 *
 * @author Михаил Глухих
 */
public class World implements Externalizable {
    /**
     * Города
     */
    private final List<City> cities = new ArrayList<>();
    /**
     * Пути
     */
    private final List<Way> ways = new ArrayList<>();

    /**
     * Получение ближайшего города
     *
     * @param x x-координата
     * @param y y-координата
     * @return город (null если не найден)
     */
    public City getNearestCity(int x, int y) {
        City result = null;
        int minDist = Integer.MAX_VALUE;
        for (City city : cities) {
            int dist = city.getDistanceSquare(x, y);
            if (dist < minDist) {
                minDist = dist;
                result = city;
            }
        }
        return result;
    }

    /**
     * Добавление города
     *
     * @param city новый город
     */
    public void addCity(City city) {
        cities.add(city);
    }

    /**
     * Удаление города
     *
     * @param city удаляемый город
     */
    public void removeCity(City city) {
        cities.remove(city);
        Way wayToRemove;
        // Удаляем все связанные пути
        for (; ; ) {
            wayToRemove = null;
            for (Way way : ways) {
                if (way.connectedWith(city)) {
                    wayToRemove = way;
                    break;
                }
            }
            if (wayToRemove == null)
                break;
            ways.remove(wayToRemove);
        }
    }

    /**
     * Получение списка городов
     *
     * @return список городов
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * Получение пути по координатам
     *
     * @param x x-координата
     * @param y y-координата
     * @return подходящий путь или null, если такого нет
     */
    public Way getWayByCoord(int x, int y) {
        for (Way way : ways) {
            if (way.isInside(x, y)) {
                return way;
            }
        }
        return null;
    }

    /**
     * Получение пути по типу, началу, концу
     *
     * @param start  начало
     * @param finish конец
     * @param kind   тип
     * @return путь (null если не существует)
     */
    public Way getWayByCities(City start, City finish, WayKind kind) {
        Way origWay = new Way(start, finish, kind, 0, 0);
        for (Way way : ways) {
            if (way.equals(origWay))
                return way;
        }
        return null;
    }

    /**
     * Добавление пути
     *
     * @param way новый путь
     */
    public void addWay(Way way) {
        ways.add(way);
    }

    /**
     * Удаление пути
     *
     * @param way удаляемый путь
     */
    public void removeWay(Way way) {
        ways.remove(way);
    }

    /**
     * Получение списка путей
     *
     * @return список путей
     */
    public List<Way> getWays() {
        return ways;
    }

    /**
     * Запись мира в объектный поток
     *
     * @param out объектный поток
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        // Версия
        out.writeInt(1);
        // Города
        out.writeInt(cities.size());
        for (City city : cities)
            city.writeExternal(out);
        // Пути
        out.writeInt(ways.size());
        for (Way way : ways) {
            way.writeExternal(out);
            final int startIndex = cities.indexOf(way.getStart());
            out.writeInt(startIndex);
            final int finishIndex = cities.indexOf(way.getFinish());
            out.writeInt(finishIndex);
        }
    }

    /**
     * Чтение мира из объектного потока
     *
     * @param in объектный поток
     * @throws IOException если происходит ошибка ввода-вывода
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // Версия
        final int version = in.readInt();
        if (version > 1)
            throw new IOException("Ожидалась версия мира 1, а получили " + version);
        // Города
        cities.clear();
        final int cityNum = in.readInt();
        for (int i = 0; i < cityNum; i++) {
            final City city = new City("", 0, 0);
            city.readExternal(in);
            cities.add(city);
        }
        // Пути
        ways.clear();
        final int wayNum = in.readInt();
        for (int i = 0; i < wayNum; i++) {
            final Way way = new Way(null, null, WayKind.BUS, 0, 0);
            way.readExternal(in);
            final int startIndex = in.readInt();
            way.setStart(cities.get(startIndex));
            final int finishIndex = in.readInt();
            way.setFinish(cities.get(finishIndex));
            ways.add(way);
        }
    }

    /**
     * Пишем в XML-файл
     *
     * @param file XML-файл
     * @throws IOException при ошибке ввода-вывода
     */
    public void writeXML(final File file) throws IOException {
        final Element root = new Element("world");
        final Element citiesRoot = new Element("cities");
        final List<Element> cityElements = new LinkedList<>();
        for (City city : cities) {
            cityElements.add(city.getXMLElement());
        }
        citiesRoot.setContent(cityElements);
        root.addContent(citiesRoot);
        final Element waysRoot = new Element("ways");
        final List<Element> wayElements = new LinkedList<>();
        for (Way way : ways) {
            final Element wayElement = way.getXMLElement();
            final int startIndex = cities.indexOf(way.getStart());
            wayElement.setAttribute("start", String.valueOf(startIndex));
            final int finishIndex = cities.indexOf(way.getFinish());
            wayElement.setAttribute("finish", String.valueOf(finishIndex));
            wayElements.add(wayElement);
        }
        waysRoot.setContent(wayElements);
        root.addContent(waysRoot);
        final Document document = new Document(root);
        final XMLOutputter outputter = new XMLOutputter();
        outputter.output(document, new FileOutputStream(file));
    }

    /**
     * Читаем XML-файл
     *
     * @param file XML-файл
     * @throws IOException   при ошибке ввода-вывода
     * @throws JDOMException при ошибке разбора XML
     */
    public void readXML(final File file) throws IOException, JDOMException {
        final SAXBuilder builder = new SAXBuilder();
        final Document document = builder.build(file);
        final Element root = document.getRootElement();
        cities.clear();
        final Element citiesRoot = root.getChild("cities");
        for (Object obj : citiesRoot.getChildren()) {
            cities.add(City.readXML((Element) obj));
        }
        ways.clear();
        final Element waysRoot = root.getChild("ways");
        for (Object obj : waysRoot.getChildren()) {
            final Element wayElem = (Element) obj;
            final Way way = Way.readXML(wayElem);
            final int startIndex =
                    Integer.parseInt(wayElem.getAttributeValue("start"));
            way.setStart(cities.get(startIndex));
            final int finishIndex =
                    Integer.parseInt(wayElem.getAttributeValue("finish"));
            way.setFinish(cities.get(finishIndex));
            ways.add(way);
        }
    }
}
