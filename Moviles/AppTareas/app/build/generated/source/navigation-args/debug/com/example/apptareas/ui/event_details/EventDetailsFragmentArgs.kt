package com.example.apptareas.ui.event_details

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.jvm.JvmStatic

public data class EventDetailsFragmentArgs(
  public val userId: Int = 1,
  public val eventId: Int = 1,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("userId", this.userId)
    result.putInt("eventId", this.eventId)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("userId", this.userId)
    result.set("eventId", this.eventId)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): EventDetailsFragmentArgs {
      bundle.setClassLoader(EventDetailsFragmentArgs::class.java.classLoader)
      val __userId : Int
      if (bundle.containsKey("userId")) {
        __userId = bundle.getInt("userId")
      } else {
        __userId = 1
      }
      val __eventId : Int
      if (bundle.containsKey("eventId")) {
        __eventId = bundle.getInt("eventId")
      } else {
        __eventId = 1
      }
      return EventDetailsFragmentArgs(__userId, __eventId)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): EventDetailsFragmentArgs {
      val __userId : Int?
      if (savedStateHandle.contains("userId")) {
        __userId = savedStateHandle["userId"]
        if (__userId == null) {
          throw IllegalArgumentException("Argument \"userId\" of type integer does not support null values")
        }
      } else {
        __userId = 1
      }
      val __eventId : Int?
      if (savedStateHandle.contains("eventId")) {
        __eventId = savedStateHandle["eventId"]
        if (__eventId == null) {
          throw IllegalArgumentException("Argument \"eventId\" of type integer does not support null values")
        }
      } else {
        __eventId = 1
      }
      return EventDetailsFragmentArgs(__userId, __eventId)
    }
  }
}
