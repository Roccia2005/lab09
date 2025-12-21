package it.unibo.mvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * 
 */
public final class SimpleController implements Controller {

    final private List<String> stringHystory = new ArrayList<>();
    private String nextString;

    @Override
    public void setNextString(final String nextString) throws NullPointerException {
        this.nextString = Objects.requireNonNull(nextString, "The given string is null");
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getStringHystory() {
        return Collections.unmodifiableList(stringHystory);
    }

    @Override
    public void printCurrentString() throws IllegalStateException {
        if (this.nextString == null) {
            throw new IllegalStateException("The next string does not exist");
        }
        System.out.println(this.nextString);
        stringHystory.addLast(this.nextString);//NOPMD
    }
}
