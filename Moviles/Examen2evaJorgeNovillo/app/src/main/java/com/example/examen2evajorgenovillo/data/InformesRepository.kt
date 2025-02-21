package com.example.examen2evajorgenovillo.data

import com.example.examen2evajorgenovillo.common.NetworkResult
import com.example.examen2evajorgenovillo.data.local.dao.InformesDao
import com.example.examen2evajorgenovillo.domain.model.Informe
import javax.inject.Inject

class InformesRepository @Inject constructor(
    private val informesDao: InformesDao
) {
    fun getAll () : List<Informe> = informesDao.getAll()
}