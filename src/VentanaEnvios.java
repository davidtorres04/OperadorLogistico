import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class VentanaEnvios extends JFrame {

    private JTextField txtCliente, txtCodigo, txtPeso, txtDistancia;
    private JComboBox<String> comboTipo;
    private JTable tabla;
    private DefaultTableModel modelo;

    private SistemaEnvios sistema;

    public VentanaEnvios() {
        sistema = new SistemaEnvios();

        setTitle("Operador Logístico");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 10));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Envío"));

        txtCliente = new JTextField();
        txtCodigo = new JTextField();
        txtPeso = new JTextField();
        txtDistancia = new JTextField();

        comboTipo = new JComboBox<>(new String[]{"Terrestre", "Aereo", "Maritimo"});

        panelForm.add(new JLabel("Cliente:"));
        panelForm.add(txtCliente);

        panelForm.add(new JLabel("Código:"));
        panelForm.add(txtCodigo);

        panelForm.add(new JLabel("Peso (Kg):"));
        panelForm.add(txtPeso);

        panelForm.add(new JLabel("Distancia (Km):"));
        panelForm.add(txtDistancia);

        panelForm.add(new JLabel("Tipo:"));
        panelForm.add(comboTipo);

        add(panelForm, BorderLayout.NORTH);

        modelo = new DefaultTableModel(
                new String[]{"Cliente", "Código", "Peso", "Distancia Km", "Tipo", "Tarifa"}, 0
        );

        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel();

        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnLimpiar = new JButton("Limpiar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnLimpiar);

        add(panelBotones, BorderLayout.SOUTH);


        btnAgregar.addActionListener(e -> agregarEnvio());
        btnEliminar.addActionListener(e -> eliminarEnvio());
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private void agregarEnvio() {
        try {
            String cliente = txtCliente.getText();
            String codigo = txtCodigo.getText();
            double peso = Double.parseDouble(txtPeso.getText());
            double distancia = Double.parseDouble(txtDistancia.getText());
            String tipo = comboTipo.getSelectedItem().toString();

            if (cliente.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Complete todos los campos");
                return;
            }
            
            if (sistema.existeCodigo(codigo)) {
                JOptionPane.showMessageDialog(this, "El código ya existe");
                return;
            }

            Envio envio = null;

            switch (tipo) {
                case "Terrestre":
                    envio = new EnvioTerrestre(cliente, codigo, peso, distancia);
                    break;
                case "Aereo":
                    envio = new EnvioAereo(cliente, codigo, peso, distancia);
                    break;
                case "Maritimo":
                    envio = new EnvioMaritimo(cliente, codigo, peso, distancia);
                    break;
            }

            sistema.agregarEnvio(envio);

            modelo.addRow(new Object[]{
                    cliente,
                    codigo,
                    peso,
                    distancia, // se muestra normal
                    tipo,
                    envio.calcularTarifa()
            });

            JOptionPane.showMessageDialog(this, "Envío agregado");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Datos inválidos");
        }
    }

    private void eliminarEnvio() {
        int fila = tabla.getSelectedRow();

        if (fila >= 0) {
            String codigo = modelo.getValueAt(fila, 1).toString();
            sistema.retirarEnvio(codigo);
            modelo.removeRow(fila);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un envío");
        }
    }

    private void limpiarCampos() {
        txtCliente.setText("");
        txtCodigo.setText("");
        txtPeso.setText("");
        txtDistancia.setText("");
    }
}