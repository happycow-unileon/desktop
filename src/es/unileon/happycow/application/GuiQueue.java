package es.unileon.happycow.application;

import es.unileon.happycow.windows.IWindow;
import es.unileon.happycow.handler.IdHandler;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Queue of windows, in a similar way of activities in android
 *
 * @author dorian
 */
public class GuiQueue {

    /**
     * Queue of windows
     */
    private LinkedList<IWindow> queue;
    /**
     * Controller of the principal frame
     */
    private JFrameController controller;

    /**
     * Principal constructor with the controller of frame
     *
     * @param controller controller of frame
     */
    public GuiQueue(JFrameController controller) {
        queue = new LinkedList<>();
        this.controller = controller;
    }

    /**
     * Add a window to the stack
     *
     * @param window to add
     */
    public void addWindow(IWindow window) {
        //get and store the parameters of the windows factory
        Parameters parameters = window.getFactory().getParameters();
        //if the windows is unique in the program and the stack has one
        //return to the window
        if (window.isUnique() && contains(window.getId())) {
            returnTo(window.getId());
            //resume the window with the new parameters
            queue.peek().getController().onResume(parameters);

            //if queue is not empty and the first window can't go back
            //remove the window and store the new window
        } else if (!queue.isEmpty() && !queue.peek().isBack()) {
            queue.pop().onDestroy();
            queue.push(window);
            window.getController().setFrameController(controller);
            window.onCreate(parameters);

            //store normal the new window
        } else {
            queue.push(window);
            window.getController().setFrameController(controller);
            window.onCreate(parameters);
        }
    }

    /**
     * See the first window in queue
     * @return a window
     */
    public IWindow peek() {
        return queue.peek();
    }

    /**
     * Take out the first window in queue
     * @return 
     */
    private IWindow pop() {
        return queue.pop();
    }

    /**
     * Go back to the next window
     * @return the window restored to first position of queue
     */
    public IWindow back() {
        IWindow last=pop();
        IWindow result = peek();
        while(last.getType()==result.getType()){
            last=pop();
            result=peek();
        }
        result.onResume(new Parameters());
        return result;
    }

    /**
     * Search for a window
     * @param id of the window
     * @return 
     */
    public boolean contains(IdHandler id) {
        boolean result = false;
        for (Iterator<IWindow> iterator = queue.iterator(); iterator.hasNext() && !result;) {
            IWindow next = iterator.next();
            if (next.getId().compareTo(id) == 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * Come back to a determined window
     * @param id window to search
     * @return the window or null if not founded
     */
    public IWindow returnTo(IdHandler id) {
        IWindow window = null;
        while (!queue.isEmpty()) {
            IWindow peek = queue.peek();
            if (peek.getId().compareTo(id) == 0) {
                window = peek;
            } else {
                queue.pop().onDestroy();
            }
        }
        if (window != null) {
            window.onResume(new Parameters());
        }
        return window;
    }

}
