public abstract class Envio {

    private String cliente;
    private String codigo;
    private double peso;
    private double distanciaKm; // 🔥 cambio aquí

    public Envio(String cliente, String codigo, double peso, double distanciaKm) {
        this.cliente = cliente;
        this.codigo = codigo;
        this.peso = peso;
        this.distanciaKm = distanciaKm;
    }

    public abstract double calcularTarifa();

    public String getCliente() {
        return cliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPeso() {
        return peso;
    }

    public double getDistanciaKm() { // 🔥 cambio aquí
        return distanciaKm;
    }

    @Override
    public String toString() {
        return "Cliente: " + cliente +
               ", Código: " + codigo +
               ", Peso: " + peso +
               ", Distancia Km: " + distanciaKm +
               ", Tarifa: " + calcularTarifa();
    }
}