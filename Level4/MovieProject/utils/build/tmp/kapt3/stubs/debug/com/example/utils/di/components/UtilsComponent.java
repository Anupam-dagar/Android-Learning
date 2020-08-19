package com.example.utils.di.components;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001:\u0001\u0004J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0005"}, d2 = {"Lcom/example/utils/di/components/UtilsComponent;", "", "getResourceProvider", "Lcom/example/base/IResourceProvider;", "Builder", "utils_debug"})
@dagger.Component(modules = {com.example.utils.di.modules.UtilsModule.class})
public abstract interface UtilsComponent {
    
    @org.jetbrains.annotations.NotNull()
    public abstract com.example.base.IResourceProvider getResourceProvider();
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00002\u0006\u0010\u0002\u001a\u00020\u0003H\'J\b\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\u0006"}, d2 = {"Lcom/example/utils/di/components/UtilsComponent$Builder;", "", "application", "Landroid/app/Application;", "build", "Lcom/example/utils/di/components/UtilsComponent;", "utils_debug"})
    @dagger.Component.Builder()
    public static abstract interface Builder {
        
        @org.jetbrains.annotations.NotNull()
        @dagger.BindsInstance()
        public abstract com.example.utils.di.components.UtilsComponent.Builder application(@org.jetbrains.annotations.NotNull()
        android.app.Application application);
        
        @org.jetbrains.annotations.NotNull()
        public abstract com.example.utils.di.components.UtilsComponent build();
    }
}