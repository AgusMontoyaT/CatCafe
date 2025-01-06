import com.example.catcafe.modelo.Gato
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface GatoServicioApi {
        @GET("gatos")
        suspend fun obtenerGato(): List<Gato>

        @POST("gatos")
        suspend fun insertarGato(
            @Body gato: Gato
        ): Gato

        @PUT("gatos/{id}")
        suspend fun actualizarGato(
            @Path("id") id: String,
            @Body gato: Gato
        ): Gato

        @DELETE("gatos/{id}")
        suspend fun eliminarGato(
            @Path("id") id: String
        ): Gato
    }