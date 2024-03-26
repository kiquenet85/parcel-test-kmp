package co.nes.parceltestkmp.data_access.database

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class DriverFactory {
     fun createDatabase(): VacationDatabase
}
