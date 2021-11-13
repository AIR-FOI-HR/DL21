package hr.foi.air.ws.handlers

interface MyWebserviceHandler {
    fun <T>onDataArrived(result: List<T>, ok: Boolean, timeStamp : Long?)
}