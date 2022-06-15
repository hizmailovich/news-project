package by.epam.news.config;

import by.epam.news.utils.Constants;
import com.microsoft.sqlserver.jdbc.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Locale;

public class LanguageResolver implements LocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest httpServletRequest) {
        String lang = httpServletRequest.getParameter(Constants.LANGUAGE_NAME);
        Locale locale = Locale.getDefault();
        HttpSession session = httpServletRequest.getSession();
        String sessionLang = (String)session.getAttribute(Constants.LANGUAGE_NAME);
        if(!StringUtils.isEmpty(sessionLang)){
            locale = new Locale(sessionLang);
        }
        if (!StringUtils.isEmpty(lang)) {
            session.setAttribute(Constants.LANGUAGE_NAME, lang);
            locale = new Locale(lang);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }
}

