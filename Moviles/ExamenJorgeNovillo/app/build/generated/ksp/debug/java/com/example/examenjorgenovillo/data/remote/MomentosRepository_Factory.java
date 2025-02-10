package com.example.examenjorgenovillo.data.remote;

import com.example.examenjorgenovillo.data.remote.datasource.MomentosDataSource;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class MomentosRepository_Factory implements Factory<MomentosRepository> {
  private final Provider<MomentosDataSource> momentosDataSourceProvider;

  public MomentosRepository_Factory(Provider<MomentosDataSource> momentosDataSourceProvider) {
    this.momentosDataSourceProvider = momentosDataSourceProvider;
  }

  @Override
  public MomentosRepository get() {
    return newInstance(momentosDataSourceProvider.get());
  }

  public static MomentosRepository_Factory create(
      Provider<MomentosDataSource> momentosDataSourceProvider) {
    return new MomentosRepository_Factory(momentosDataSourceProvider);
  }

  public static MomentosRepository newInstance(MomentosDataSource momentosDataSource) {
    return new MomentosRepository(momentosDataSource);
  }
}
