package com.example.hospitalroomcompose;

import dagger.hilt.InstallIn;
import dagger.hilt.codegen.OriginatingElement;
import dagger.hilt.components.SingletonComponent;
import dagger.hilt.internal.GeneratedEntryPoint;

@OriginatingElement(
    topLevelClass = HospitalApp.class
)
@GeneratedEntryPoint
@InstallIn(SingletonComponent.class)
public interface HospitalApp_GeneratedInjector {
  void injectHospitalApp(HospitalApp hospitalApp);
}
