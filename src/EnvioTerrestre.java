public class EnvioTerrestre extends Envio {

    public EnvioTerrestre(String cliente, String codigo, double peso, double distancia) {
        super(cliente, codigo, peso, distancia);
    }

    @Override
    public double calcularTarifa() {
        return (1500 * getDistanciaKm()) + (2000 * getPeso());
    }
}