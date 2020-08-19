package com.example.utils.di;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0017J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\bH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u00a8\u0006\f"}, d2 = {"Lcom/example/utils/di/ResourceProviderImpl;", "Lcom/example/base/IResourceProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "providesDatabase", "Lcom/example/base/data/Database;", "providesMovieDao", "Lcom/example/base/data/dao/MovieDao;", "database", "utils_debug"})
@javax.inject.Singleton()
public final class ResourceProviderImpl implements com.example.base.IResourceProvider {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    @javax.inject.Singleton()
    public com.example.base.data.Database providesDatabase() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.base.data.dao.MovieDao providesMovieDao(@org.jetbrains.annotations.NotNull()
    com.example.base.data.Database database) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    public ResourceProviderImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
}