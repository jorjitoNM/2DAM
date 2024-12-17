package com.example.apptareas.ui.events_list

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.apptareas.R
import kotlin.Int

public class EventsListFragmentDirections private constructor() {
  private data class ActionEventsListFragmentToEventDetailsFragment(
    public val userId: Int = 1,
    public val eventId: Int = 1,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_eventsListFragment_to_eventDetailsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("userId", this.userId)
        result.putInt("eventId", this.eventId)
        return result
      }
  }

  public companion object {
    public fun actionEventsListFragmentToEventDetailsFragment(userId: Int = 1, eventId: Int = 1):
        NavDirections = ActionEventsListFragmentToEventDetailsFragment(userId, eventId)
  }
}
