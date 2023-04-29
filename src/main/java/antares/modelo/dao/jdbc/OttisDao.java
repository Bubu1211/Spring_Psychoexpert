package antares.modelo.dao.jdbc;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class OttisDao {

    //Archivos de configuracion o de base XML
    public static final String PREGUNTAS_OTTIS_XML = "C:/Users/Dell/Documents/antares/preguntas_ottis/preguntas_cuestionario_ottis.xml";
    public static final String PREGUNTAS_OTTIS_RUTA = "imagenes/preguntas_ottis/reglas.dir";

    /**
     *
     * @return arreglo de tamaño 80 con las preguntas del test de ottis y sus
     * respuestas correctas
     */
    public PreguntaOttis[] obtenerPreguntas() {
        var lista = new PreguntaOttis[80];

        try {
            File archivo = new File(PREGUNTAS_OTTIS_XML);

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());

            NodeList list = document.getElementsByTagName("pregunta");

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node nodo = list.item(temp);
                System.out.println("Elemento:" + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    var n = element.getAttribute("n");
                    var r = element.getAttribute("respuesta");
                    System.out.println("n: " + n);
                    System.out.println("respuesta: " + r);
                    lista[temp] = new PreguntaOttis(Integer.parseInt(n), r);
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        }
        return lista;
    }

    /**
     * Registra un test de otis gamma en la base de datos
     *
     * @param lista
     * @param respuestas
     * @param time
     * @param paciente
     * @return verdadero si todo ha salido bien falso si hubo errores
     * @throws SQLException
     */
    public boolean registrarOttis(PreguntaOttis[] lista, String[] respuestas, long time, Paciente paciente) throws SQLException {
        String procedure = "CALL registrar_prueba_ottis(?, ?, ?,?, ?);";

        String ruta = "C:/Users/Dell/Documents/antares/preguntas_ottis/";
        String nombreArchivo = null;

        Connection conn = ConexionJDBC.getConexion();
        PreparedStatement stmt = conn.prepareStatement(procedure);

        long minuto = (int) (time / 60000);
        long segundo = (int) (time % 60000);
        stmt.setString(1, minuto + ":" + segundo);

        int puntaje = 0;
        int i = 0;
        for (PreguntaOttis p : lista) {
            if (p.getRespuesta().equalsIgnoreCase(respuestas[i])) {
                puntaje++;
            }
            i++;
        }
        stmt.setInt(2, puntaje);
        stmt.setInt(3, paciente.getId_paciente());

        //crear archivo con respuestas
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            //Elemento raíz
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("root");
            doc.appendChild(rootElement);

            Element elemento1 = doc.createElement("paciente");
            //Se agrega un atributo al nodo elemento y su valor
            Attr attr = doc.createAttribute("id_paciente");
            attr.setValue(paciente.getId_paciente() + "");
            elemento1.setAttributeNode(attr);

            for (i = 0; i < 80; i++) {
                //Primer elemento
                elemento1 = doc.createElement("pregunta");
                //Se agrega un atributo al nodo elemento y su valor
                attr = doc.createAttribute("n");
                attr.setValue(i + "");
                elemento1.setAttributeNode(attr);

                Attr attr2 = doc.createAttribute("respuesta");
                attr2.setValue(respuestas[i]);
                elemento1.setAttributeNode(attr2);
                rootElement.appendChild(elemento1);
            }

            //Se escribe el contenido del XML en un archivo
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            //Obtiene la fecha
            LocalDateTime date = LocalDateTime.now(ZoneId.of("America/Mexico_City"));
            DateTimeFormatter f = DateTimeFormatter.ofPattern("YYYY_MM_dd_h_mm_ss");
            nombreArchivo = paciente.getId_paciente() + "_" + paciente.getNombre() + date.format(f) +".xml";
            File file = new File(ruta + nombreArchivo);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);
            System.out.println("Hecho");
        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }

        stmt.setString(4, ruta);
        stmt.setString(5, nombreArchivo);
        stmt.executeUpdate();

        ConexionJDBC.close(stmt);
        ConexionJDBC.close(conn);

        return true;
    }

    public String[] obtenerRespuestas(Archivo a) {
        String[] respuestas = new String[80];

        try {
            File archivo = new File(a.fileString());

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

            Document document = documentBuilder.parse(archivo);
            document.getDocumentElement().normalize();

            System.out.println("Elemento raiz:" + document.getDocumentElement().getNodeName());

            NodeList lista = document.getElementsByTagName("pregunta");

            for (int temp = 0; temp < lista.getLength(); temp++) {
                Node nodo = lista.item(temp);
                System.out.println("Elemento:" + nodo.getNodeName());
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) nodo;
                    var n = element.getAttribute("n");
                    var r = element.getAttribute("respuesta");

                    respuestas[temp] = r;
                }
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            System.out.println(e.getMessage());
        }

        return respuestas;
    }

}
