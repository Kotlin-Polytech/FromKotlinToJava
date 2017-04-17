package part3.voyager.gui;

import java.io.File;
import javax.swing.filechooser.FileFilter;


/**
 * Файловый фильтр для World
 * @author Mikhail Glukhikh
 */
public class WorldFileFilter extends FileFilter {

    static private final FileFilter instanceWorld = new WorldFileFilter("world",
            "Файлы коммивояжера (*.world)");

    static public FileFilter getWorldFilter() {
        return instanceWorld;
    }
    static private final FileFilter instanceXML = new WorldFileFilter("xml",
            "Файлы XML (*.xml)");

    static public FileFilter getXMLFilter() {
        return instanceXML;
    }

    private final String ext;

    private final String descr;


    private WorldFileFilter(final String ext, final String descr) {
        super();
        this.ext = ext;
        this.descr = descr;
    }

    @Override
    public boolean accept(File f) {
        if (f!=null) {
            if (f.isDirectory())
                return true;
            String name = f.getName();
            int i = name.lastIndexOf('.');
            if (i>0 && i < name.length()-1)
                return name.substring(i+1).equalsIgnoreCase(ext);
        }
        return false;
    }

    @Override
    public String getDescription() {
        return descr;
    }
}
