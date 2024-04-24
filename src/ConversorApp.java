import java.io.IOException;
import java.util.Scanner;

public class ConversorApp {
    public static void main(String[] args) {
        while (true) {
            Scanner lectura = new Scanner(System.in);
            SolicitudApi consulta = new SolicitudApi();
            System.out.println("""
                    **********************************************
                    Sea bienvenido/a al Conversor de Moneda
                                   
                    1) Dólar =>> Peso argentino
                    2) Peso argentino =>> Dólar 
                    3) Dólar =>> Real brasileño
                    4) Real brasileño =>> Dólar 
                    5) Dólar =>> Peso colombiano
                    6) Peso colombiano =>> Dólar
                    7) Salir
                    Elija una opción válida:
                    **********************************************
                    """);
            try {
                var seleccionUsuario = Integer.valueOf(lectura.nextLine());
                if (seleccionUsuario.equals(7)){
                break;
                }
                System.out.println("Ingrese el valor que desea convertir: ");
                var valor = Float.valueOf(lectura.nextLine());
                Moneda moneda = consulta.convertirMoneda(seleccionUsuario, valor);
                System.out.println("El valor " + valor + " [" + moneda.base_code() + "] corresponde al valor final de =>>> "
                        + moneda.conversion_result() + " [" + moneda.target_code() + "]");


            } catch (NumberFormatException e) {
                System.out.println("Número no encontrado " + e.getMessage());
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
                System.out.println("Finalizando la aplicación.");
            }
        }
    }
}
