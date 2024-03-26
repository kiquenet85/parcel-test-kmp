package co.nes.parceltestkmp.data_access.database

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory(private val context: Context) {

    actual fun createDatabase(): VacationDatabase {
        val driver = AndroidSqliteDriver(
            schema = VacationDatabase.Schema,
            context = context,
            name = "VacationDatabase.db",
            callback = object : AndroidSqliteDriver.Callback(VacationDatabase.Schema) {
                override fun onOpen(db: SupportSQLiteDatabase) {
                    db.setForeignKeyConstraintsEnabled(true)
                }
            }
        )
        return VacationDatabase(driver)
    }
}
