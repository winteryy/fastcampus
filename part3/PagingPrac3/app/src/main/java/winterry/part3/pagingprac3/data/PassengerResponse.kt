package winterry.part3.pagingprac3.data

data class PassengerResponse(
    val `data`: List<Data>,
    val totalPages: Int,
    val totalPassengers: Int
)