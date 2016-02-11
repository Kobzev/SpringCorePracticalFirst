package ua.kobzev.theatre.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by kkobziev on 2/11/16.
 */

@Component
public class MainUtils {

    private static DomainUtils utils;

    @Autowired
    public void setUtils(DomainUtils utils){
        MainUtils.utils = utils;
    }

    public static DomainUtils getUtils(){
        return utils;
    }
}
