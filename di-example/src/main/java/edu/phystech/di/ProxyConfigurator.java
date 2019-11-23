package edu.phystech.di;

public interface ProxyConfigurator {
    <T> T  wrapWithPoxy(T t, Class<? extends T> type);
}
