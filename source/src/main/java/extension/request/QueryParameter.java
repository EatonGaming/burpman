package extension.request;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;

public class QueryParameter {
    private boolean enabled;
    private String name;
    private String value;
    private final Runnable queryParamUpdatedAction;
    private final Runnable removeQueryParamAction;

    public static QueryParameter queryParamForModel(String name, String value, Runnable queryParamUpdatedAction, Runnable removeQueryParamAction)
    {
        return new QueryParameter(true, name, value, queryParamUpdatedAction, removeQueryParamAction);
    }

    public static QueryParameter queryParam(String name, String value)
    {
        return new QueryParameter(true, name, value, () -> {}, () -> {});
    }

    public static QueryParameter disabledQueryParam(String name, String value)
    {
        return new QueryParameter(false, name, value, () -> {}, () -> {});
    }

    private QueryParameter(boolean enabled, String name, String value, Runnable queryParamUpdatedAction, Runnable removeQueryParamAction) {
        this.enabled = enabled;
        this.name = name;
        this.value = value;
        this.queryParamUpdatedAction = queryParamUpdatedAction;
        this.removeQueryParamAction = removeQueryParamAction;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;

        queryParamUpdatedAction.run();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;

        queryParamUpdatedAction.run();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;

        queryParamUpdatedAction.run();
    }

    public String toQueryStringFormat()
    {
        return String.format("%s=%s", name, value);
    }

    public JButton getDeleteLabel()
    {
        URL deleteIconStream = getClass().getResource("/images/baseline_clear_black_18dp.png");

        try {
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(deleteIconStream));
            JButton button = new JButton(imageIcon);

            button.setOpaque(false);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);

            button.addActionListener(e -> removeQueryParamAction.run());

            return button;
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParameter that = (QueryParameter) o;
        return enabled == that.enabled && Objects.equals(name, that.name) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled, name, value);
    }
}
