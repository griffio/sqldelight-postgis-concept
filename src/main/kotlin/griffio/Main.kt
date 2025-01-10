package griffio

import app.cash.sqldelight.ColumnAdapter
import app.cash.sqldelight.driver.jdbc.asJdbcDriver
import griffio.migrations.Locations
import griffio.queries.Sample
import net.postgis.jdbc.geometry.Point
import net.postgis.jdbc.geometry.binary.BinaryParser
import net.postgis.jdbc.geometry.binary.BinaryWriter
import org.postgresql.ds.PGSimpleDataSource

private fun getSqlDriver() = PGSimpleDataSource().apply {
    setURL("jdbc:postgresql://localhost:5432/postgis01")
    applicationName = "App Main"
    user = "postgres"
}.asJdbcDriver()

val locationsAdapter = Locations.Adapter(
pointAdapter = object : ColumnAdapter<Point, String> {
    override fun encode(value: Point) = BinaryWriter().writeHexed(value)
    override fun decode(databaseValue: String) = BinaryParser().parse(databaseValue) as Point
})

fun main() {
    val driver = getSqlDriver()
    val sample = Sample(driver, locationsAdapter)
    sample.geoQueries.a(40.7128, 74.0060)
    sample.geoQueries.b(40.7128, 74.0060, 5.0, 4326)
    val points: List<Point> = sample.geoQueries.selectPoints().executeAsList()
    println(points)
}
