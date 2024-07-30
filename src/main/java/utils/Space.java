package utils;

/**
 * Обертка над аргументами для создания SpacePage
 */
public class Space {
    private static final String EMPTY_STRING = "";
    private static final int BASE_WORKFLOW_NUM = 0;

    /**
     * Название пространства
     */
    private String name;

    /**
     * Описание пространства
     */
    private String description;

    /**
     * Приватность пространства (true - приватное, false - не приватное)
     */
    private boolean isPrivate;

    /**
     * Цвет пространства в формате "r, g, b"
     */
    private String color;

    /**
     * Название иконки пространства
     */
    private String icon;

    /**
     * номер workflow пространства
     */
    private int workflow;

    public Space(String name, String description, boolean isPrivate, String color, String icon, int workflow) {
        this.name = name;
        this.description = description;
        this.isPrivate = isPrivate;
        this.color = color;
        this.icon = icon;
        this.workflow = workflow;
    }

    public Space(String name, String description, String color, String icon) {
        this(name, description, false, color, icon, BASE_WORKFLOW_NUM);
    }

    public Space(String name, String description, boolean isPrivate) {
        this(name, description, isPrivate, EMPTY_STRING, EMPTY_STRING, BASE_WORKFLOW_NUM);
    }

    public Space(String name, String description) {
        this(name, description, false, EMPTY_STRING, EMPTY_STRING, BASE_WORKFLOW_NUM);
    }

    public Space(String name) {
        this(name, EMPTY_STRING, false, EMPTY_STRING, EMPTY_STRING, BASE_WORKFLOW_NUM);
    }

    public Space(String name, int workflow) {this(name, EMPTY_STRING, false, EMPTY_STRING, EMPTY_STRING, workflow);}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getColor() {
        return color;
    }

    public String getIcon() {
        return icon;
    }

    public int getWorkflow() {return workflow;}
}
