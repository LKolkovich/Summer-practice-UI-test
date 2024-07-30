package pages.popUpWindows.windowsForSpaces.settings;

import elements.Buttons.Button;
import pages.popUpWindows.BaseWindow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Окно, используемое для настроек цвета и иконки (аватара) уже созданного пространства.
 * В тестах используется для получения этих данных.
 */
public class SpaceColorAndAvatarWindow extends BaseWindow {
    private final Button pickedColorButton = Button.byAriaPressedAndDataTestContains("color");
    private final Button pickedIconButton = Button.byAriaPressedAndDataTestContains("avatar");
    private final String styleAttribute = "style";
    private final String ariaLabelAttribute = "aria-label";
    private final String colorStringSeparator = ", ";
    private final String colorRegEx = "\\((\\d{1,3}), (\\d{1,3}), (\\d{1,3})\\)";
    private final int rightColorNum = 1;
    private final int greenColorNum = 2;
    private final int blueColorNum = 3;


    /**
     * Конструктор, логирует открытие окна
     */
    public SpaceColorAndAvatarWindow() {
        logger.info("space color and icon customisation window is opened");
    }

    /**
     * Получает цвет пространства из атрибута style веб элемента
     * @return String - цвет в формате "r, g, b"
     */
    public String getColor() {
        String fullColorInfo = pickedColorButton.getAttributeValue(styleAttribute);
        Pattern pattern = Pattern.compile(colorRegEx);
        Matcher matcher = pattern.matcher(fullColorInfo);

        if (matcher.find()) {
            int right = Integer.parseInt(matcher.group(rightColorNum));
            int green = Integer.parseInt(matcher.group(greenColorNum));
            int blue = Integer.parseInt(matcher.group(blueColorNum));

            String color = right + colorStringSeparator + green + colorStringSeparator + blue;
            logger.info("got space color: " + color);
            return color;
        } else {
            logger.info("space has default color");
            return "";
        }
    }

    /**
     * Получает название иконки из атрибута веб элемента
     * @return String - название иконки
     */
    public String getIcon() {
        String icon = pickedIconButton.getAttributeValue(ariaLabelAttribute);
        logger.info("got space icon: " + icon);
        return icon;
    }

}
