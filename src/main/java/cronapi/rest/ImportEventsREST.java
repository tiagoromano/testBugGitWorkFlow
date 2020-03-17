package cronapi.rest;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cronapi.QueryManager;
import cronapi.util.Operations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/js/system-events.js")
public class ImportEventsREST {
  
  private static JsonObject JSON;

  static {
    JSON = loadJSON();
  }

  private static JsonObject loadJSON() {
    ClassLoader classLoader = QueryManager.class.getClassLoader();
    try (InputStream stream = classLoader.getResourceAsStream("META-INF/events.json")) {
      InputStreamReader reader = new InputStreamReader(stream);
      JsonElement jsonElement = new JsonParser().parse(reader);
      return jsonElement.getAsJsonObject();
    }
    catch(Exception e) {
      return new JsonObject();
    }
  }
  
  private static JsonObject getJSON() {
    if(Operations.IS_DEBUG) {
      return loadJSON();
    }
    else {
      return JSON;
    }
  }
  
  private static boolean isNull(JsonElement value) {
    return value == null || value.isJsonNull();
  }
  
  @RequestMapping(method = RequestMethod.GET)
  public void listEvents(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("application/javascript");
    PrintWriter out = response.getWriter();
    out.println("window.blockly = window.blockly || {};");
    out.println("window.blockly.events = window.blockly.events || {};");
    for(Map.Entry<String, JsonElement> entry : getJSON().entrySet()) {
      if(!isNull(entry.getValue())) {
        JsonObject customObj = entry.getValue().getAsJsonObject();
        if (customObj.get("type") != null && !customObj.get("type").isJsonNull() && customObj.get("type").getAsString().equals("client")) {
          write(out, entry.getKey(), customObj);
        }
      }
    }
  }

  private void write(PrintWriter out, String eventName, JsonObject eventObj) {
    String namespace = "window.blockly.events." + eventName;
    // Try to set the 'window.blockly.events.{eventName}' with the blockly method reference
    StringBuilder sb = new StringBuilder("try{ ");
    sb.append(namespace);
    if (!isNull(eventObj.get("blockly"))) {
      String blocklyNamespace = eventObj.get("blockly").getAsJsonObject().get("namespace").getAsString();
      String method = Operations.safeNameForMethodBlockly(eventObj.get("blocklyMethod").getAsString());
      sb.append(" = blockly.").append(blocklyNamespace).append(".").append(method).append(";");
      sb.append(" } catch(ex){ ");
      // If it is not possible to add the reference (Blockly not found due to be from other project - mobile/web) set the reference as String
      sb.append(namespace).append(" = 'blockly.").append(blocklyNamespace).append(".").append(method).append("';");
      sb.append(" }");
    } else {
      // No event to be added
      sb.append(" = {}; } catch(ex){ }");
    }
    out.println(sb.toString());
  }
}
