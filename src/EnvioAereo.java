public class EnvioAereo extends Envio {

    public EnvioAereo(String cliente, String codigo, double peso, double distancia) {
        super(cliente, codigo, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (5000 * getDistanciaKm()) + (4000 * getPeso());
    }
}