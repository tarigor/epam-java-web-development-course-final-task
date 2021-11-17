package com.epam.hotel.command;

import java.util.HashMap;

/**
 * An abstract class provides the filed declaration which can be used by child classes.
 */
public abstract class CommonSiteActivityCommand {
    protected static HashMap<String, Boolean> attributesMap = new HashMap<>();
    //protected CommonSiteActivityServiceImpl commonSiteActivityService = ServiceFactory.getInstance().getCommonSiteActivityService();
}
