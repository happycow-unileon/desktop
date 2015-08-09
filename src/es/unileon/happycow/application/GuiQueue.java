package es.unileon.happycow.application;

import es.unileon.happycow.application.windows.IWindow;
import es.unileon.happycow.handler.IdHandler;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author dorian
 */
public class GuiQueue{
    private LinkedList<IWindow> queue;
    private JFrameController controller;

    public GuiQueue(JFrameController controller) {
        queue=new LinkedList<>();
        this.controller=controller;
    }
    
    public void addWindow(IWindow window){
        HashMap<String,String> parameters=window.getFactory().getParameters();
        if(window.isUnique() && contains(window.getId())){
            returnTo(window.getId());
            queue.peek().getController().onResume(parameters);
            
        }else if(!queue.isEmpty() && !queue.peek().isBack()){
            queue.pop();
            queue.push(window);
            window.getController().setFrameController(controller);
            window.onCreate(parameters);
            
        }else{
            queue.push(window);
            window.getController().setFrameController(controller);
            window.onCreate(parameters);
        }
    }
    
    public IWindow peek(){
        return queue.peek();
    }
    
    public IWindow pop(){
        return queue.pop();
    }
    
    public IWindow back(){
        pop();
        return peek();
    }
    
    public boolean contains(IdHandler id){
        boolean result=false;
        for (Iterator<IWindow> iterator = queue.iterator(); iterator.hasNext() && !result;) {
            IWindow next = iterator.next();
            if(next.getId().compareTo(id)==0){
                result=true;
            }
        }
        return result;
    }
    
    public IWindow returnTo(IdHandler id){
        IWindow window=null;
        while(!queue.isEmpty()){
            IWindow peek=queue.peek();
            if(peek.getId().compareTo(id)==0){
                window=peek;
            }else{
                queue.pop();
            }
        }
        return window;
    }

}
