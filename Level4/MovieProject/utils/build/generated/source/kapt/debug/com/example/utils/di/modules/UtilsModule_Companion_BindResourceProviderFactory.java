// Generated by Dagger (https://dagger.dev).
package com.example.utils.di.modules;

import android.content.Context;
import com.example.base.IResourceProvider;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class UtilsModule_Companion_BindResourceProviderFactory implements Factory<IResourceProvider> {
  private final UtilsModule.Companion module;

  private final Provider<Context> contextProvider;

  public UtilsModule_Companion_BindResourceProviderFactory(UtilsModule.Companion module,
      Provider<Context> contextProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
  }

  @Override
  public IResourceProvider get() {
    return bindResourceProvider(module, contextProvider.get());
  }

  public static UtilsModule_Companion_BindResourceProviderFactory create(
      UtilsModule.Companion module, Provider<Context> contextProvider) {
    return new UtilsModule_Companion_BindResourceProviderFactory(module, contextProvider);
  }

  public static IResourceProvider bindResourceProvider(UtilsModule.Companion instance,
      Context context) {
    return Preconditions.checkNotNull(instance.bindResourceProvider(context), "Cannot return null from a non-@Nullable @Provides method");
  }
}
