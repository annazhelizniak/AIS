package controllers.command;

import Page.Page;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Locale;
import Page.Attribute;
import Page.MessageLocale;
public class ChangeLocaleCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        setLocale(request);
        return Page.HOME_VIEW;
    }

    private void setLocale(HttpServletRequest request) {
        String selectedLanguage = request.getParameter(Attribute.LANG);
        Locale chosenLocale = AppLocale.forValue(selectedLanguage);

        request.getSession().setAttribute(Attribute.LOCALE, chosenLocale);
        MessageLocale.setResourceBundleLocale(chosenLocale);
    }
}
