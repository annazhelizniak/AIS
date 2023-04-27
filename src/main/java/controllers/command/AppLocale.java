package controllers.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public enum AppLocale {
    EN(new Locale("en", "US")), UK(new Locale("uk", "UA"));

    private final static Locale DEFAULT_LOCALE = EN.getLocale();

    private Locale locale;

    AppLocale(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Locale getDefault() {
        return DEFAULT_LOCALE;
    }

    public static Locale forValue(String language) {
        for (final AppLocale locale : AppLocale.values()) {
            if (locale.getLocale().getLanguage().equalsIgnoreCase(language)) {
                return locale.getLocale();
            }
        }
        return getDefault();
    }

    public static List<Locale> getAppLocales() {
        List<Locale> appLocales = new ArrayList<>();
        for (AppLocale appLocale : AppLocale.values()) {
            appLocales.add(appLocale.getLocale());
        }
        return appLocales;
    }
}