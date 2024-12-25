package com.example.apptareas.data.remote

import com.example.apptareas.data.remote.datasource.EventsDataSource
import com.example.apptareas.data.remote.model.events.EventRemote
import com.example.apptareas.di.IoDispatcher
import com.example.apptareas.utilities.Constantes
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class EventsRepository @Inject constructor(
    private val eventsDataSource: EventsDataSource,
    @IoDispatcher val dispatcher: CoroutineDispatcher,
) {
    fun getEvents() = flow {
        emit(NetworkResult.Loading())
        val result = eventsDataSource.getEvents()
        emit(result)
    }
        .catch { e ->
            emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
        }
        .flowOn(dispatcher)

    fun update(event: EventRemote) = flow {
        emit(NetworkResult.Loading())
        val result = eventsDataSource.updateEvent(event)
        emit(result)
    }
        .catch { e ->
            emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
        }
        .flowOn(dispatcher)

    fun deleteEvent(event: EventRemote) = flow {
        emit(NetworkResult.Loading())
        val result = eventsDataSource.deleteEvent(event)
        emit(result)
    }
        .catch { e ->
            emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
        }
        .flowOn(dispatcher)

    fun getEvent(eventId: Int) = flow {
        emit(NetworkResult.Loading())
        val result = eventsDataSource.getEvent(eventId)
        emit(result)
    }
        .catch { e ->
            emit(NetworkResult.Error(e.message ?: Constantes.DATA_BASE_ERROR))
        }
        .flowOn(dispatcher)
}
