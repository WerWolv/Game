package com.werwolv.api.event;

import com.werwolv.api.Log;

import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventBus {

    private static List<Class<?>> eventHandlers = new ArrayList<>();
    private static Stack<Event> eventStack = new Stack<>();


    public void postEvent(Event event) {
        eventStack.push(event);
    }

    public void processEvents() {

        for (Iterator<Event> iterator = eventStack.iterator(); iterator.hasNext(); ) {
            Event currEvent = iterator.next();
            for (Class eventHandler : eventHandlers) {
                runAllAnnotatedWith(SubscribeEvent.class, eventHandler, currEvent);
            }
            iterator.remove();
        }
    }

    private void runAllAnnotatedWith(Class<? extends Annotation> annotation, Class<?> eventListener, Event event) {
        Method[] methods = eventListener.getMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(annotation)) {
                try {
                    if(method.getParameterTypes()[0].isInstance(event))
                        method.invoke(eventListener.newInstance(), event);
                } catch (IllegalAccessException | InvocationTargetException | InstantiationException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void registerEventHandlers() {
        this.eventHandlers.addAll(new Reflections("").getTypesAnnotatedWith(EventBusSubscriber.class));
    }

}
