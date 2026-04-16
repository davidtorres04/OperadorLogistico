public class EnvioMaritimo extends Envio {

    public EnvioMaritimo(String cliente, String codigo, double peso, double distancia) {
        super(cliente, codigo, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (800 * getDistanciaKm()) + (1000 * getPeso());
    }
}
