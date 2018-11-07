package zd.zero.abdt.ui.editor;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class ColorManager {

    private static ColorManager instance = new ColorManager();

    private Map<RGB, Color> colors;

    public static ColorManager getInstance() {
        return instance;
    }

    private ColorManager() {
        colors = new HashMap<>();
    }

    public Color getColor( RGB rgb ) {
        return colors.computeIfAbsent( rgb, k -> new Color( Display.getCurrent(), k ) );
    }
}
