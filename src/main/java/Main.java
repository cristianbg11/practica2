
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static spark.Spark.*;
import static spark.Spark.*;

public class Main {
    public static ArrayList estudiantes=new ArrayList<Object>();

    public static void main(String[] args) {

        port(8080);

        staticFiles.location("/publico");

        get("/formulario", (request, response)-> {
            return renderContent("publico/crear.html");
        });

        get("/", (request, response)-> {
           // return renderContent("publico/index.html");
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("listado",estudiantes);
            return new ModelAndView(attributes, "index.ftl");

        } , new FreeMarkerEngine());

        post("/insertar", (request, response) -> {
            Estudiante est=new Estudiante();
            est.matricula = Integer.parseInt(request.queryParams("matricula"));
            est.nombre=request.queryParams("nombre");
            est.apellido = request.queryParams("apellido");
            est.telefono = request.queryParams("telefono");
            estudiantes.add(est);
            response.redirect("/");
            return "Estudiante Creado";
        });

    }
    private static String renderContent(String htmlFile) throws IOException, URISyntaxException {
        URL url = Main.class.getResource(htmlFile);
        Path path = Paths.get(url.toURI());
        return new String(Files.readAllBytes(path), Charset.defaultCharset());
    }
}
