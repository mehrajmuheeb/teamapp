package com.teamapp.data.local

interface PreferencesHelper {
    fun saveString(key: String, value: String)

    fun getString(key: String, defaultValue: String): String?

    fun saveInt(key: String, value: Int)

    fun getInt(key: String, defaultValue: Int): Int

    fun saveFloat(key: String, value: Float)

    fun getFloat(key: String, defaultValue: Float): Float

    fun saveLong(key: String, value: Long)

    fun getLong(key: String, defaultValue: Long): Float

    fun saveBoolean(key: String, value: Boolean)

    fun getBoolean(key: String, defaultValue: Boolean): Boolean

    fun <T> saveObject(key: String, obj: T)

    fun <T> getObject(key: String, type: Class<T>): T
}