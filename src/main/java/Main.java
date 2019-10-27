
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
    public static ArrayList estudiantes=new ArrayList<Estudiante>();
    public static void main(String[] args) {

        port(8080);
        staticFiles.location("/publico");

        get("/", (request, response)-> {
           // return renderContent("publico/index.html");
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("listado",estudiantes);
            return new ModelAndView(attributes, "index.ftl");

        } , new FreeMarkerEngine());

        get("/view", (request, response)-> {
            Map<String, Object> attributes = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            //Estudiante est = (Estudiante) estudiantes.get(id);
            attributes.put("vista",estudiantes.get(id));
            return new ModelAndView(attributes, "view.ftl");

        } , new FreeMarkerEngine());

        get("/edit", (request, response)-> {
            Map<String, Object> attributes = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            attributes.put("edicion",estudiantes.get(id));
            attributes.put("id",id);
            return new ModelAndView(attributes, "edit.ftl");

        } , new FreeMarkerEngine());

        get("/delete", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            estudiantes.remove(id);
            response.redirect("/");
            return "Estudiante Eliminado";
        });

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

        post("/actualizar", (request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            Estudiante est = (Estudiante) estudiantes.get(id);
            est.matricula = Integer.parseInt(request.queryParams("matricula"));
            est.nombre=request.queryParams("nombre");
            est.apellido = request.queryParams("apellido");
            est.telefono = request.queryParams("telefono");
            response.redirect("/");
            return 0;
        });

    }
    private static String renderContent(String htmlFile) throws IOException, URISyntaxException {
        URL url = Main.class.getResource(htmlFile);
        Path path = Paths.get(url.toURI());
        return new String(Files.readAllBytes(path), Charset.defaultCharset());
    }
}
