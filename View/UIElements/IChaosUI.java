package View.UIElements;

/**
 * All of our custom UI Nodes implement this interface to enable them all to be
 * scaled to the size of the monitor in use.
 */
public interface IChaosUI
{

    /**
     * Scales this UIElement to fit the active monitor.
     */
    public void scaleToMonitorSize ();
}
