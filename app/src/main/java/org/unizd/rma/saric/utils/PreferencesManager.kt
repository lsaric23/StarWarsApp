package org.unizd.rma.saric.utils

import android.content.Context
import android.content.SharedPreferences

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )

    companion object {
        private const val PREFS_NAME = "starwars_app_prefs"
        private const val KEY_LAST_SELECTED_CREATURE_ID = "last_selected_creature_id"
    }


    fun saveLastSelectedCreatureId(creatureId: String) {
        sharedPreferences.edit()
            .putString(KEY_LAST_SELECTED_CREATURE_ID, creatureId)
            .apply()
    }


    fun getLastSelectedCreatureId(): String? {
        return sharedPreferences.getString(KEY_LAST_SELECTED_CREATURE_ID, null)
    }

    fun clearSelection() {
        sharedPreferences.edit()
            .remove(KEY_LAST_SELECTED_CREATURE_ID)
            .apply()
    }
}