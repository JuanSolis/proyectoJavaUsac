import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.layout.CenterLayout;

public class Inicio extends JFrame {

    javax.swing.JLabel UserL;
    JTextField rutaT;
    JTextField tituloT;
    JButton btoncargar;
    JButton btOrdenar;
    String dirArchivo;
    JButton btnGraficaar;
    JPanel pnl_panel;
    FileReader archCSV = null;
    JFileChooser selector;
    File archivo;
    Producto[] arrayProducto;
    

    public Inicio() {

        setSize(550, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);

        //Aquie se crea un panel que tendra la grafica
        add(pnl_panel = new JPanel());
        pnl_panel.setBounds(10, 150, 480, 400);
        pnl_panel.setBorder(BorderFactory.createTitledBorder("panel"));
        

        //Aqui se crearon los textos "Ruta del Archivo" y "Titulo par ala grafica 
        UserL = new javax.swing.JLabel("Ruta del archivo:");
        UserL.setBounds(50, 10, 100, 25);
        UserL.setVisible(true);
        UserL.setLayout(null);
        add(UserL);

        //Aquui se crea el textField De ruta 
        rutaT = new JTextField();
        rutaT.setBounds(50, 40, 300, 25);
        rutaT.setLayout(null);

        add(rutaT);

        //Aqui se crea el boton buscar 
        btoncargar = new JButton("Cargar");

        btoncargar.setBounds(400, 40, 100, 25);
        btoncargar.setVisible(true);
        btoncargar.setLayout(new CenterLayout());
        
        

        btoncargar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae){

                selector = new JFileChooser();
                selector.showOpenDialog(rootPane);

                 archivo = selector.getSelectedFile();

                if (archivo != null) {
                    
                    rutaT.setText(archivo.getPath());
                }
                else {
                    JOptionPane.showMessageDialog(null, "Por favor seleccione un archivo");
                }
           

            }
        });

        add(btoncargar);

        //Aqui se creo el texto "Titilo de l  a grafica"
        UserL = new javax.swing.JLabel("Titulo de la grafica:");
        UserL.setBounds(50, 80, 130, 25);
        UserL.setVisible(true);
        UserL.setLayout(null);
        add(UserL);
        
        //
        tituloT = new JTextField();
        tituloT.setBounds(50, 100, 300, 25);
        tituloT.setLayout(null);
        add(tituloT);

        //Aquie se creo el voton aceptar
        btnGraficaar = new JButton("Graficar");

        btnGraficaar.setBounds(400, 100, 100, 25);
        btnGraficaar.setVisible(true);
        btnGraficaar.setLayout(null);
        add(btnGraficaar);

        btnGraficaar.addActionListener(new ActionListener() {
            // Mostrar Grafica
            @Override
            public void actionPerformed(ActionEvent ae) {
            Graficadora gr = new Graficadora();
                
                try{
                    gr.graficar(crearArrayProductos(archivo), tituloT.getText(), "Productos", "Cantidad de Producto");
                    pnl_panel.setLayout(new java.awt.BorderLayout());
                    pnl_panel.add(gr.oPanel);
                    

                } catch(Exception ex){
                    System.out.println(ex.getMessage());
                }

            }
            
            
            Producto[] crearArrayProductos(File archivoProducto) throws FileNotFoundException, IOException{
                
                
                
                String Origen = archivoProducto.getPath();
                FileReader lector = new FileReader(Origen);
                BufferedReader bdLector = new BufferedReader(lector);

                String linea = "";
                int cantidadLineas = 0;

                while((linea = bdLector.readLine()) != null){
                    cantidadLineas++;
                }
                
                arrayProducto = new Producto[cantidadLineas];
                
                bdLector.close();
                lector.close();
                
                FileReader lectorProductos = new FileReader(Origen);
                BufferedReader bdLectorProductos= new BufferedReader(lectorProductos);
                
                linea = "";
                int contador = 0;
                
                while((linea = bdLectorProductos.readLine()) != null){
                    Producto productoNuevo = new Producto(linea.split(",")[0], Integer.parseInt(linea.split(",")[1]));
                    arrayProducto[contador] = productoNuevo;
                    contador++;
                }
                
                bdLectorProductos.close();
                lectorProductos.close();
               
                return arrayProducto;

                
            }
            
        });
        
            


    }
}
