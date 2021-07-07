package com.anit.goodweather.repository.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.anit.goodweather.repository.database.entites.CityItem


class DatabaseHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_NAME = "weather.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_WEATHER = "weater"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_CITY = "note"
        private const val COLUMN_TEMPERATURE = "temperature"
        private const val COLUMN_ICON = "icon"
        private const val COLUMN_DATE = "date"
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE $TABLE_WEATHER (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "$COLUMN_CITY TEXT, " +
                    "$COLUMN_TEMPERATURE REAL, " +
                    "$COLUMN_ICON TEXT, " +
                    "$COLUMN_DATE INTEGER" +
                    ");"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getListCity():List<String> {

        val result = mutableListOf<String>()

        val db = this.readableDatabase
        val cursor = db.query(
            TABLE_WEATHER,
            null,
            null,
            null,
            null,
            null,
            null
        )

        if (cursor != null && cursor.moveToFirst()) {

            val cityIdx = cursor.getColumnIndex(COLUMN_CITY)
            do {
                result.add(cursor.getString(cityIdx))
            } while (cursor.moveToNext())
        }
        try {
            cursor.close()
            db.close()
            this.close()
        } catch (ignored: Exception) {
            ignored.printStackTrace()
        }

        return result
    }

    fun addCityIem(cityItem: CityItem) {

        val cv = ContentValues()
        cv.put(COLUMN_CITY, cityItem.city)
        cv.put(COLUMN_TEMPERATURE, cityItem.temperature)
        cv.put(COLUMN_ICON, cityItem.icon)
        cv.put(COLUMN_DATE, cityItem.date.time)

        val db = this.writableDatabase

        val cursor = db.query(
            TABLE_WEATHER,
            null,
            "$COLUMN_CITY = ?",
            arrayOf(cityItem.city),
            null,
            null,
            null
        )

        if (cursor != null && cursor.moveToFirst()) {
            //update
            val indexId = cursor.getColumnIndex(COLUMN_ID)
            val id = cursor.getInt(indexId).toString()

            try {
                cursor.close()
            } catch (ignored: Exception) {
                ignored.printStackTrace()
            }


            db.update(
                TABLE_WEATHER,
                cv,
                "$COLUMN_ID = ?",
                arrayOf(id)
            )
        } else {
            //insert
            db.insert(TABLE_WEATHER, null, cv)
        }
        db.close()
        this.close()
    }
}
