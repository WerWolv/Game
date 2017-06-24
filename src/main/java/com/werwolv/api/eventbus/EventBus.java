package com.werwolv.api.eventbus;

import com.google.common.collect.ImmutableSet;
import com.google.common.reflect.ClassPath;
import com.werwolv.api.event.Event;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class EventBus {

    private List<Class<?>> eventHandlers = new ArrayList<>();
    private Stack<Event> eventStack = new Stack<>();


    public void postEvent(Event event) {
        eventStack.push(event);
    }

    public void processEvents() {

        for (Iterator<Event> iterator = eventStack.iterator(); iterator.hasNext(); ) {
            Event currEvent = iterator.next();
            for (Class eventHandler : eventHandlers)
                runAllAnnotatedWith(SubscribeEvent.class, eventHandler, currEvent);
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

    @SuppressWarnings("unsafe")
    public void registerEventHandlers() {
        ImmutableSet<ClassPath.ClassInfo> set = null;
        try {
            set = ClassPath.from(getClass().getClassLoader()).getAllClasses();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(ClassPath.ClassInfo c : set) {
            try {
                Class clazz = c.load();
                if(clazz.isAnnotationPresent(EventBusSubscriber.class))
                    this.eventHandlers.add(clazz);
            } catch(NoClassDefFoundError e) {}
        }
    }
}
