package Control.Actions;

/*
 * Actions are constructed and executed by the inputController to 
 * process moves and attacks.
 */
public abstract class Action
{

    /**
     * some things only happen after successful execution.
     */
    private boolean executed = false;

    /**
     * this method must be overeridden by all subclasses and implement
     * functionality to execute the action. the implementation must end by
     * setting isExecuted true if execution was successful.
     */
    public void execute ()
    {
    }

    /**
     * If the Action could be executed or not
     *
     * @return - true when the action could be executed
     */
    public boolean isExecuted ()
    {
        return this.executed;
    }

    /**
     * Setter for the isExecuted attribute
     *
     * @param executed - true when the action should be executed,
     *                 false when the action should not be revertable
     */
    public void setExecuted (boolean executed)
    {
        this.executed = executed;
    }

}
