package Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of a minimal Stack.
 */
public class Stack
{

    /**
     * Object List, used for the Stack implementation.
     */
    private List<Object> _stack = null;

    /**
     * Default Constructor.
     */
    public Stack ()
    {
        this._stack = new ArrayList();
    }

    /**
     * Removes a Object from the top of the stack and returns it.
     *
     * @return The Object on top of the stack.
     */
    public Object pop ()
    {
        int length = this._stack.size();

        Object top = this._stack.get(length - 1);

        this._stack.remove(length - 1);

        return top;
    }

    /**
     * Adds a new Object to the top of the Stack.
     *
     * @param o The new Object.
     */
    public void put (Object o)
    {
        this._stack.add(o);
    }

    /**
     * Determines the size of the stack.
     *
     * @return The length of the stack.
     */
    public int size ()
    {
        return this._stack.size();
    }

}
