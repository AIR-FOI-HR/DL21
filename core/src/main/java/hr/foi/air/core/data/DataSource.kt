package hr.foi.air.core.data

import android.content.Context

interface DataSource {
    fun loadData(dataSourceListener: DataSourceListener, context: Context)
}