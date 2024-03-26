package co.nes.parceltestkmp.data_access.database

import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.sqliter.DatabaseConfiguration

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DriverFactory {

    actual fun createDatabase(): VacationDatabase {
        val driver = NativeSqliteDriver(
            schema = VacationDatabase.Schema,
            name = "VacationDatabase.db",
            onConfiguration = { config: DatabaseConfiguration ->
                config.copy(
                    extendedConfig = DatabaseConfiguration.Extended(foreignKeyConstraints = true)
                )
            }
        )

        return VacationDatabase(driver)
    }
}
