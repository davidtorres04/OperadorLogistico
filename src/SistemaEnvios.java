import java.util.ArrayList;
import java.util.List;

public class SistemaEnvios {

    private List<Envio> envios;

    public SistemaEnvios() {
        envios = new ArrayList<>();
    }

    public void agregarEnvio(Envio envio) {
        envios.add(envio);
    }

    public void retirarEnvio(String codigo) {
        envios.removeIf(e -> e.getCodigo().equals(codigo));
    }

    public List<Envio> getEnvios() {
        return envios;
    }

    public boolean existeCodigo(String codigo) {
        for (Envio e : envios) {
            if (e.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}