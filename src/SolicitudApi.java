import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class SolicitudApi {
    public Moneda convertirMoneda(int seleccionUsuario, float valor){
//        Scanner lectura = new Scanner(System.in);
        String resultado = null;

        switch (seleccionUsuario) {
            case 1:
                resultado = "USD/ARS";
                break;
            case 2:
                resultado = "ARS/USD";
                break;
            case 3:
                resultado = "USD/BRL";
                break;
            case 4:
                resultado = "BRL/USD";
                break;
            case 5:
                resultado = "USD/COP";
                break;
            case 6:
                resultado = "COP/USD";
                break;
            default:
                System.out.println("Opción no válida. Por favor, elija una opción del 1 al 7.");
        }

        String apiUrl = System.getenv("API_MONEY");
        if (apiUrl == null) {
            throw new RuntimeException("La variable de entorno API_MONEY no está configurada.");
        }

        URI direccion = URI.create(apiUrl + resultado + "/" + valor);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();
        try {
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(response.body(), Moneda.class);
        } catch (Exception e) {
            throw new RuntimeException("Error");
        }
    }
    }


