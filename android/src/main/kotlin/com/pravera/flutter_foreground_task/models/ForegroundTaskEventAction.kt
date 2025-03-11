package com.pravera.flutter_foreground_task.models

import org.json.JSONObject
import java.util.Objects

data class ForegroundTaskEventAction(
    val type: ForegroundTaskEventType,
    val interval: Long,
    val delay: Long
) {
    companion object {
        private const val TASK_EVENT_TYPE_KEY = "taskEventType"
        private const val TASK_EVENT_INTERVAL_KEY = "taskEventInterval"
        private const val TASK_EVENT_DELAY_KEY = "taskEventDelay"

        fun fromJsonString(jsonString: String): ForegroundTaskEventAction {
            val jsonObj = JSONObject(jsonString)

            val type: ForegroundTaskEventType = if (jsonObj.isNull(TASK_EVENT_TYPE_KEY)) {
                ForegroundTaskEventType.NOTHING
            } else {
                val value = jsonObj.getInt(TASK_EVENT_TYPE_KEY)
                ForegroundTaskEventType.fromValue(value)
            }

            val interval: Long = if (jsonObj.isNull(TASK_EVENT_INTERVAL_KEY)) {
                5000L
            } else {
                val value = jsonObj.getInt(TASK_EVENT_INTERVAL_KEY)
                value.toLong()
            }

            val delay: Long = if (jsonObj.isNull(TASK_EVENT_DELAY_KEY)) {
                0L
            } else {
                val value = jsonObj.getInt(TASK_EVENT_DELAY_KEY)
                value.toLong()
            }

            return ForegroundTaskEventAction(type = type, interval = interval, delay = delay)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ForegroundTaskEventAction) {
            return false
        }
        return this.type.value == other.type.value && this.interval == other.interval && this.delay == other.delay
    }

    override fun hashCode(): Int {
        return Objects.hash(type.value, interval, delay)
    }
}
