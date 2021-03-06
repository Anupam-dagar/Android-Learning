// Generated by Dagger (https://dagger.dev).
package com.example.utils.di.components;

import android.app.Application;
import com.example.base.IResourceProvider;
import com.example.utils.di.modules.UtilsModule_BindResourceProviderFactory;
import dagger.internal.Preconditions;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class DaggerUtilsComponent implements UtilsComponent {
  private final Application application;

  private DaggerUtilsComponent(Application applicationParam) {
    this.application = applicationParam;
  }

  public static UtilsComponent.Builder builder() {
    return new Builder();
  }

  @Override
  public IResourceProvider getResourceProvider() {
    return UtilsModule_BindResourceProviderFactory.bindResourceProvider(application);}

  private static final class Builder implements UtilsComponent.Builder {
    private Application application;

    @Override
    public Builder application(Application application) {
      this.application = Preconditions.checkNotNull(application);
      return this;
    }

    @Override
    public UtilsComponent build() {
      Preconditions.checkBuilderRequirement(application, Application.class);
      return new DaggerUtilsComponent(application);
    }
  }
}
