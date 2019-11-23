package edu.phystech.di;

public interface ObjectConfigurator {
    void configure(Object t) throws ReflectiveOperationException;
}
