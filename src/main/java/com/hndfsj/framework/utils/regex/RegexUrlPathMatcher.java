package com.hndfsj.framework.utils.regex;

import java.util.regex.Pattern;

/**
 * @author Luke Taylor
 * @version $Id: RegexUrlPathMatcher.java 2473 2008-01-18 16:24:35Z luke_t $
 */
public class RegexUrlPathMatcher implements UrlMatcher {

    private boolean requiresLowerCaseUrl = false;

    public Object compile(String path) {
        return Pattern.compile(path);
    }

    public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {
        this.requiresLowerCaseUrl = requiresLowerCaseUrl;
    }

    public boolean pathMatchesUrl(Object compiledPath, String url) {
        Pattern pattern = (Pattern)compiledPath;

        return pattern.matcher(url).matches();
    }

    public String getUniversalMatchPattern() {
        return "/.*";
    }

    public boolean requiresLowerCaseUrl() {
        return requiresLowerCaseUrl;
    }
}
