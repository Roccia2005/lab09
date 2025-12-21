package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {

    /**
    *   @param next the next string to print
    */
    void setNextString(String next);

    /**
    *    @return the next string to print
    */
    String getNextString();

    /**
    *    @return a list of all the string printed
    */
    List<String> getStringHystory();

    /**
    *
    */
    void printCurrentString();

}
