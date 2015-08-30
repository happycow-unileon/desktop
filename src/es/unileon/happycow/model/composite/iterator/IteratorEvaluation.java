/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unileon.happycow.model.composite.iterator;

import es.unileon.happycow.model.composite.Component;
import es.unileon.happycow.model.composite.Entity;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 *
 * @author dorian
 */
public class IteratorEvaluation implements Iterator<Component>{
    private Queue<Component> queue;
    private Component raiz;

    public IteratorEvaluation(Component raiz) {
        this.queue = new LinkedList<>();
        this.raiz = raiz;
        calculate(raiz);
    }
    
    private void calculate(Component search){
        for(Component comp : search.getList()){
            if(comp.getEntity()==Entity.VALORATION){
                queue.add(comp);
            }else{
                calculate(comp);
            }
        }
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Component next() {
        Component result=queue.poll();
        if(result==null){
            throw new NoSuchElementException();
        }else{
            return result;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
