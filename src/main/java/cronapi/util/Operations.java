package cronapi.util;

import cronapi.*;
import cronapi.CronapiMetaData.CategoryType;
import cronapi.CronapiMetaData.ObjectType;
import cronapi.clazz.CronapiClassLoader;
import cronapi.database.DatabaseQueryManager;
import cronapi.database.HistoryListener;
import cronapi.i18n.Messages;
import cronapi.rest.DownloadREST;
import cronapi.rest.security.BlocklySecurity;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.eclipse.rap.json.JsonArray;
import org.eclipse.rap.json.JsonObject;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.*;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CronapiMetaData(category = CategoryType.UTIL, categoryTags = {"Util"})
public class Operations {

  public static boolean IS_DEBUG;

  public static boolean IS_WINDOWS;
  public static boolean IS_LINUX;
  private static int THREAD_POOLSIZE = 50;
  public static ConcurrentHashMap<String, Logger> LOGGERS = new ConcurrentHashMap<>();
  public static Level LOG_LEVEL = Level.INFO;
  public static boolean LOG_DEFINED = false;

  static {

    if (!StringUtils.isEmpty(System.getenv("CronappLogLevel"))) {
      LOG_LEVEL = toLevel(System.getenv("CronappLogLevel"));
      LOG_DEFINED = true;
    } else {
      if (!StringUtils.isEmpty(System.getProperty("CronappLogLevel"))) {
        LOG_LEVEL = toLevel(System.getProperty("CronappLogLevel"));
        LOG_DEFINED = true;
      }
    }

    String SO = System.getProperty("os.name");
    if (SO.indexOf(' ') > -1)
      SO = SO.substring(0, SO.indexOf(' '));

    IS_WINDOWS = SO.equalsIgnoreCase("Windows");
    IS_LINUX = SO.equalsIgnoreCase("Linux");

    String cmd = ManagementFactory.getRuntimeMXBean().getInputArguments().toString();

    IS_DEBUG = cmd.indexOf("-agentlib:jdwp") > 0 || cmd.indexOf("-Xrunjdwp") > 0;
  }

  @CronapiMetaData(type = "function", name = "{{setReturn}}", nameTags = {
      "return", "retorno"}, description = "{{setReturnDescription}}", wizard = "procedures_return_callnoreturn")
  public static final void setReturn(Var param) throws Exception {

  }

  @CronapiMetaData(type = "function", name = "{{getCurrentUserName}}", nameTags = {
      "getCurrentUser"}, description = "{{getCurrentUserNameDescription}}", returnType = ObjectType.STRING)
  public static final Var getCurrentUserName() throws Exception {
    User user = RestClient.getRestClient().getUser();
    String username = null;
    if (user != null)
      username = user.getUsername();
    return Var.valueOf(username);
  }

  @CronapiMetaData(type = "function", name = "{{shellExecuteName}}", nameTags = {
      "shellExecute"}, description = "{{shellExecuteDescription}}", params = {"{{shellExecuteParam0}}",
      "{{shellExecuteParam1}}"}, paramsType = {ObjectType.STRING,
      ObjectType.BOOLEAN}, returnType = ObjectType.STRING)
  public static final Var shellExecute(Var cmdline, Var waitFor) throws Exception {
    Boolean waitForCasted = (Boolean) waitFor.getObject();
    Process p = Runtime.getRuntime().exec(cmdline.getObjectAsString());
    if (waitForCasted) {
      BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));
      String r = "";
      String line;
      while ((line = input.readLine()) != null) {
        r += (line + "\n");
      }
      input.close();
      return new Var(r);
    }
    return new Var();
  }

  // Retorna um numério aleatório
  @CronapiMetaData(type = "function", name = "{{randomName}}", nameTags = {
      "random"}, description = "{{randomDescription}}", params = {
      "{{randomParam0}}"}, paramsType = {ObjectType.DOUBLE}, returnType = ObjectType.DOUBLE)
  public static final Var random(Var maxValue) throws Exception {
    return new Var(Math.round(Math.random() * maxValue.getObjectAsDouble()));
  }

  @CronapiMetaData(type = "function", name = "{{compressToZipName}}", nameTags = {
      "compressToZip"}, description = "{{compressToZipDescription}}", params = {
      "{{compressToZipParam0}}"}, paramsType = {ObjectType.OBJECT}, returnType = ObjectType.OBJECT)
  public static final Var compressToZip(Var value) throws Exception {
    java.io.ByteArrayOutputStream output = new java.io.ByteArrayOutputStream();
    java.util.zip.DeflaterOutputStream compresser = new java.util.zip.DeflaterOutputStream(output);
    compresser.write((byte[]) value.getObject());
    compresser.finish();
    compresser.close();
    return new Var(output.toByteArray());
  }

  @CronapiMetaData(type = "function", name = "{{decodeZipFromByteName}}", nameTags = {
      "decodeZipFromByte"}, description = "{{decodeZipFromByteDescription}}", params = {
      "{{decodeZipFromByteParam0}}"}, paramsType = {ObjectType.OBJECT}, returnType = ObjectType.OBJECT)
  public static final Var decodeZipFromByte(Var value) throws Exception {
    java.io.ByteArrayInputStream input = new java.io.ByteArrayInputStream((byte[]) value.getObject());
    java.util.zip.InflaterInputStream decompresser = new java.util.zip.InflaterInputStream(input);
    byte[] buffer = new byte[1024 * 4];// 4KB
    java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
    int len;
    while ((len = decompresser.read(buffer)) != -1) {
      out.write(buffer, 0, len);
    }
    decompresser.close();
    out.close();
    input.close();
    return new Var(out.toByteArray());
  }

  @CronapiMetaData(type = "function", name = "{{sleep}}", nameTags = {
      "sleep"}, description = "{{functionToSleep}}", params = {
      "{{timeSleepInSecond}}"}, paramsType = {ObjectType.LONG}, returnType = ObjectType.VOID)
  public static final void sleep(Var time) throws Exception {
    long sleepTime = (time.getObjectAsInt() * 1000);
    Thread.sleep(sleepTime);
  }

  @CronapiMetaData(type = "function", name = "{{throwException}}", nameTags = {
      "throwException"}, description = "{{functionToThrowException}}", params = {
      "{{exceptionToBeThrow}}"}, paramsType = {ObjectType.OBJECT}, returnType = ObjectType.VOID)
  public static final void throwException(Var exception) throws Exception {
    if (exception.getObject() instanceof Exception)
      throw Exception.class.cast(exception.getObject());
    else if (exception.getObject() instanceof String)
      throw new Exception(exception.getObjectAsString());
  }

  @CronapiMetaData(type = "function", name = "{{createExceptionName}}", nameTags = {
      "createException"}, description = "{{createExceptionName}}", params = {
      "{{createExceptionParam0}}"}, paramsType = {ObjectType.STRING}, returnType = ObjectType.OBJECT)
  public static final Var createException(Var msg) throws Exception {
    Exception e = new Exception(msg.getObjectAsString());
    return new Var(e);
  }

  @CronapiMetaData(type = "function", name = "{{callBlocklyNoReturnName}}", nameTags = {
      "callBlocklyNoReturn"}, description = "{{callBlocklyNoReturnDescription}}", wizard = "procedures_callblockly_callnoreturn", returnType = ObjectType.VOID, arbitraryParams = true)
  public static final void callBlocklyNoReturn(
      @ParamMetaData(type = ObjectType.STRING, description = "{{callBlocklyNoReturnParam0}}") Var classNameWithMethod,
      @ParamMetaData(type = ObjectType.STRING, description = "{{callBlocklyNoReturnParam1}}") Var... params)
      throws Exception {
    callBlockly(classNameWithMethod, params);
  }

  // Internal function
  // @CronapiMetaData(type = "function", name = "{{callClienteFunctionName}}", nameTags = {
  // "callClienteFunction" }, description = "{{callClienteFunctionDescription}}", returnType = ObjectType.VOID,
  // arbitraryParams = true)
  public static final void callClientFunction(
      @ParamMetaData(type = ObjectType.STRING, description = "{{callClienteFunctionParam0}}") Var function,
      @ParamMetaData(type = ObjectType.STRING, description = "{{callClienteFunctionParam1}}") Var... params)
      throws Exception {
    ClientCommand command = new ClientCommand(function.getObjectAsString());
    for (Var p : params)
      command.addParam(p);

    RestClient.getRestClient().addCommand(command);
  }

  @CronapiMetaData(type = "function", name = "{{callBlockly}}", nameTags = {
      "callBlockly"}, description = "{{functionToCallBlockly}}", params = {"{{classNameWithMethod}}",
      "{{params}}"}, wizard = "procedures_callblockly_callreturn", paramsType = {ObjectType.OBJECT,
      ObjectType.OBJECT}, returnType = ObjectType.OBJECT, arbitraryParams = true)
  public static final Var callBlockly(Var classNameWithMethod, Var... params) throws Exception {
    return callBlockly(classNameWithMethod, false, "", params);
  }

  @CronapiMetaData(type = "internal")
  public static String safeNameForMethodBlockly(String s) {
    String result;
    if (s == null || s.isEmpty())
      return "unnamed";
    s = s.replace(" ", "_");
    try {
      result = URLEncoder.encode(s, "UTF-8").replaceAll("[^\\w]", "_");
      if ("0123456789".indexOf(result.substring(0, 1)) > -1)
        result = "my_" + result;
    } catch (UnsupportedEncodingException e) {
      result = s;
    }
    return result;
  }

  @CronapiMetaData(type = "internal")
  public static final Var callBlockly(Var classNameWithMethod, boolean checkSecurity, String restMethod,
                                      Var... params) throws Exception {

    String className = classNameWithMethod.getObjectAsString();
    String method = null;
    if (className.indexOf(":") > -1) {
      method = safeNameForMethodBlockly(className.substring(className.indexOf(":") + 1));
      className = className.substring(0, className.indexOf(":"));
    }

    Class clazz = null;

    try {
      if (IS_DEBUG) {
        CronapiClassLoader loader = new CronapiClassLoader();
        clazz = loader.findClass(className);
      } else {
        clazz = Class.forName(className);
      }
    } catch (Exception e) {
      String[] parts = className.split("\\.");
      String simpleName = parts[parts.length - 1];
      className = "";
      for (int i = 0; i < parts.length - 1; i++) {
        if (!className.isEmpty())
          className += ".";
        className += parts[i];
      }

      if (!className.isEmpty())
        className += ".";

      className += reduceVariable(simpleName, false);

      try {
        if (IS_DEBUG) {
          CronapiClassLoader loader = new CronapiClassLoader();
          clazz = loader.findClass(className);
        } else {
          clazz = Class.forName(className);
        }
      } catch (Exception e2) {
        try {
          if (IS_DEBUG) {
            CronapiClassLoader loader = new CronapiClassLoader();
            clazz = loader.findClass("blockly." + className);
          } else {
            clazz = Class.forName("blockly." + className);
          }
        } catch (Exception e3) {
          throw new Exception(Messages.getString("blocklyNotFound"), e);
        }
      }

    }

    boolean checkSOAP = false;
    if (checkSecurity) {
      if (restMethod.equals("soap")) {
        restMethod = "POST";
        checkSOAP = true;
      }
      BlocklySecurity.checkSecurity(clazz, restMethod);
    }

    Method methodToCall = method == null ? clazz.getMethods()[0] : null;
    for (Method m : clazz.getMethods()) {
      if (m.getName().equalsIgnoreCase(method)) {
        methodToCall = m;
        break;
      }
    }

    if (methodToCall == null) {
      throw new Exception(Messages.getString("methodNotFound"));
    }

    if (params == null)
      params = new Var[0];

    Var[] callParams = params;

    if (methodToCall.getParameterCount() != callParams.length) {
      callParams = new Var[methodToCall.getParameterCount()];
      for (int i = 0; i < methodToCall.getParameterCount(); i++) {
        if (i < params.length)
          callParams[i] = params[i];
        else
          callParams[i] = Var.VAR_NULL;
      }
    }

    boolean isBlockly = false;
    boolean isSoap = false;
    boolean audit = false;
    for (Annotation annotation : clazz.getAnnotations()) {
      if (annotation.annotationType().getName().equals("cronapi.CronapiMetaData")) {
        Method type = annotation.annotationType().getMethod("type");
        if (type != null) {
          String value = (String) type.invoke(annotation);
          if (value != null && value.equals("blockly")) {
            isBlockly = true;
          }
        }
      }
      if (annotation.annotationType().getName().equals("cronapi.rest.security.CronappAudit")) {
        audit = true;
      }

      if (annotation.annotationType().getName().equals("javax.jws.WebService")) {
        isSoap = true;
      }
    }
    if (!isBlockly) {
      throw new Exception(Messages.getString("accessDenied"));
    }

    if (checkSOAP && !isSoap) {
      throw new Exception(Messages.getString("accessDenied"));
    }

    Object o = methodToCall.invoke(clazz, callParams);
    Var result = Var.valueOf(o);
    if (audit) {
      auditBlockly(clazz, methodToCall, result, callParams);
    }

    return result;
  }

  private static void auditBlockly(Class blockly, Method function, Var result, Var[] params) throws Exception {
    DatabaseQueryManager logManager = HistoryListener.getAuditLogManager();

    if (logManager != null) {

      JsonObject json = new JsonObject();
      JsonArray arrayParams = new JsonArray();
      json.add("parameters", arrayParams);
      for (Var p : params) {
        arrayParams.add(p.getObjectAsString());
      }
      json.add("result", result.getObjectAsString());

      Var auditLog = new Var(new LinkedHashMap<>());

      auditLog.set("type", blockly.getName());
      auditLog.set("command", function.getName());
      auditLog.set("category", "Blockly");
      auditLog.set("date", new Date());
      auditLog.set("objectData", json.toString());
      if (RestClient.getRestClient() != null) {
        auditLog.set("user", RestClient.getRestClient().getUser() != null ? RestClient.getRestClient().getUser().getUsername() : null);
        auditLog.set("host", RestClient.getRestClient().getHost());
        auditLog.set("agent", RestClient.getRestClient().getAgent());
      }
      auditLog.set("server", HistoryListener.CURRENT_IP);
      auditLog.set("affectedFields", null);
      auditLog.set("application", AppConfig.guid());

      logManager.insert(auditLog);

    }
  }

  @CronapiMetaData(type = "function", name = "{{encryptPasswordName}}", nameTags = {
      "encryptPassword"}, description = "{{encryptPasswordDescription}}", params = {
      "{{encryptPasswordParam0}}"}, paramsType = {ObjectType.STRING}, returnType = ObjectType.STRING)
  public static final Var encryptPassword(Var password) throws Exception {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return new Var(passwordEncoder.encode(password.getObjectAsString()));
  }

  @CronapiMetaData(type = "function", name = "{{MD5OfVar}}", nameTags = {
      "string md5", "bytes md5", "md5"}, description = "{{functionToReturnMD5OfVar}}", params = {
      "{{MD5OfVarParam0}}"}, paramsType = {ObjectType.OBJECT}, returnType = ObjectType.STRING)
  public static Var encodeMD5(Var value) throws Exception {
    return Var.valueOf(value.getMD5());
  }

  @CronapiMetaData(type = "function", name = "{{matchesencryptPasswordName}}", nameTags = {
      "matchesencryptPassword"}, description = "{{matchesencryptPasswordDescription}}", params = {
      "{{matchesencryptPasswordParam0}}", "{{matchesencryptPasswordParam1}}"}, paramsType = {
      ObjectType.STRING, ObjectType.STRING}, returnType = ObjectType.BOOLEAN)
  public static final Var matchesencryptPassword(Var password, Var encrypted) throws Exception {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    return new Var(passwordEncoder.matches(password.getObjectAsString(), encrypted.getObjectAsString()));
  }

  @CronapiMetaData(type = "function", name = "{{getHeadersFromExternalURL}}", nameTags = {
      "getHeadersFromExternalURL"}, description = "{{getHeadersFromExternalURLDescription}}", returnType = ObjectType.STRING)
  public static final Var getHeadersFromExternalURL(
      @ParamMetaData(type = ObjectType.STRING, description = "{{HTTPMethod}}", blockType = "util_dropdown", keys = {
          "GET", "POST", "PUT",
          "DELETE"}, values = {"{{HTTPGet}}", "{{HTTPPost}}", "{{HTTPPut}}", "{{HTTPDelete}}"}) Var method,

      @ParamMetaData(type = ObjectType.STRING, description = "{{contentType}}", blockType = "util_dropdown", keys = {
          "application/x-www-form-urlencoded",
          "application/json"}, values = {"{{x_www_form_urlencoded}}", "{{app_json}}"}) Var contentType,

      @ParamMetaData(type = ObjectType.STRING, description = "{{URLAddress}}") Var address,
      @ParamMetaData(type = ObjectType.MAP, description = "{{paramsHTTP}}") Var params,
      @ParamMetaData(type = ObjectType.MAP, description = "{{cookieContainer}}") Var cookieContainer)
      throws Exception {
    return Operations.getContentFromURL(method, contentType, address, params, cookieContainer, new Var("HEADER"), Var.VAR_NULL);
  }

  public static final Var getURLFromOthers(Var method, Var contentType, Var address, Var params, Var cookieContainer)
      throws Exception {
    return Operations.getContentFromURL(method, contentType, address, params, cookieContainer, new Var("BODY"), Var.VAR_NULL);
  }

  @CronapiMetaData(type = "function", name = "{{getURLFromOthersName}}", nameTags = {
      "getURLFromOthersName"}, description = "{{getURLFromOthersDescription}}", returnType = ObjectType.STRING)
  public static final Var getURLFromOthers(
      @ParamMetaData(type = ObjectType.STRING, description = "{{HTTPMethod}}", blockType = "util_dropdown", keys = {
          "GET", "POST", "PUT",
          "DELETE", "PATCH", "HEAD", "OPTIONS", "TRACE"}, values = {"{{HTTPGet}}", "{{HTTPPost}}", "{{HTTPPut}}",
          "{{HTTPDelete}}", "PATCH", "HEAD", "OPTIONS", "TRACE"}) Var method,

      @ParamMetaData(type = ObjectType.STRING, description = "{{contentType}}", blockType = "util_dropdown", keys = {
          "application/x-www-form-urlencoded",
          "application/json"}, values = {"{{x_www_form_urlencoded}}", "{{app_json}}"}) Var contentType,

      @ParamMetaData(type = ObjectType.STRING, description = "{{URLAddress}}") Var address,
      @ParamMetaData(type = ObjectType.MAP, description = "{{paramsHTTP}}") Var params,
      @ParamMetaData(type = ObjectType.MAP, description = "{{cookieContainer}}") Var cookieContainer,
      @ParamMetaData(type = ObjectType.MAP, description = "{{postData}}") Var postData)
      throws Exception {
    return Operations.getContentFromURL(method, contentType, address, params, cookieContainer, new Var("BODY"), postData);
  }

  private static final String APPLICATION_X_WWW_FORM_URLENCODED = "application/x-www-form-urlencoded";
  private static final String APPLICATION_JSON = "application/json";

  private static final Var getContentFromURL(Var method, Var contentType, Var address, Var params,
                                             Var cookieContainer, Var returnType, Var postData) throws Exception {

    HttpClient httpClient = HttpClients.createSystem();
    final HttpRequestBase httpMethod;

    if (method.getObjectAsString().equalsIgnoreCase("POST")) {
      httpMethod = new HttpPost(address.getObjectAsString());
    } else if (method.getObjectAsString().equalsIgnoreCase("PUT")) {
      httpMethod = new HttpPut(address.getObjectAsString());
    } else if (method.getObjectAsString().equalsIgnoreCase("DELETE")) {
      httpMethod = new HttpDelete(address.getObjectAsString());
    } else if (method.getObjectAsString().equalsIgnoreCase("PATCH")) {
      httpMethod = new HttpPatch(address.getObjectAsString());
    } else if (method.getObjectAsString().equalsIgnoreCase("HEAD")) {
      httpMethod = new HttpHead(address.getObjectAsString());
    } else if (method.getObjectAsString().equalsIgnoreCase("OPTIONS")) {
      httpMethod = new HttpOptions(address.getObjectAsString());
    } else if (method.getObjectAsString().equalsIgnoreCase("TRACE")) {
      httpMethod = new HttpTrace(address.getObjectAsString());
    } else {
      httpMethod = new HttpGet(address.getObjectAsString());
    }

    if (!cookieContainer.isNull()) {
      Map<?, ?> headerObject = cookieContainer.getObjectAsMap();
      headerObject.entrySet().stream().forEach((entry) -> {
        httpMethod.addHeader(Var.valueOf(entry.getKey()).getObjectAsString(),
            Var.valueOf(entry.getValue()).getObjectAsString());
      });
    }

    Var toReturn;
    HttpResponse httpResponse;
    Map<String, String> responseMap = new HashMap<String, String>();

    if (!postData.isNull() && params.isNull()
        && (method.getObjectAsString().equalsIgnoreCase("DELETE")
        || method.getObjectAsString().equalsIgnoreCase("GET"))) {

      StringEntity postDataList = new StringEntity(postData.getObjectAsString(),
          Charset.forName(cronapi.CronapiConfigurator.ENCODING));
      postDataList.setContentType(contentType.getObjectAsString());

      if (method.getObjectAsString().equalsIgnoreCase("DELETE")) {
        HttpDeleteWithBody deleteWithBody = new HttpDeleteWithBody(address.getObjectAsString());
        deleteWithBody.setEntity(postDataList);

        URI uri = new URIBuilder(deleteWithBody.getURI()).build();
        deleteWithBody.setURI(uri);
        httpResponse = httpClient.execute(deleteWithBody);
      } else {
        HttpGetWithBody getWithBody = new HttpGetWithBody(address.getObjectAsString());
        getWithBody.setEntity(postDataList);

        URI uri = new URIBuilder(getWithBody.getURI()).build();
        getWithBody.setURI(uri);
        httpResponse = httpClient.execute(getWithBody);
      }
    } else if (!params.isNull() && postData.isNull()) {
      if (httpMethod instanceof HttpEntityEnclosingRequestBase) {

        HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) httpMethod;

        if (params.getObject() instanceof Map && contentType.getObjectAsString().equals(APPLICATION_X_WWW_FORM_URLENCODED)) {

          Map<?, ?> mapObject = params.getObjectAsMap();
          List<NameValuePair> paramsData = new LinkedList<>();
          mapObject.entrySet().stream().forEach((entry) -> {
            paramsData.add(new BasicNameValuePair(Var.valueOf(entry.getKey()).getObjectAsString(),
                Var.valueOf(entry.getValue()).getObjectAsString()));
          });

          httpEntityEnclosingRequestBase.setEntity(new UrlEncodedFormEntity(paramsData, cronapi.CronapiConfigurator.ENCODING));
        } else {

          StringEntity paramsData = new StringEntity(params.getObjectAsString(),
              Charset.forName(cronapi.CronapiConfigurator.ENCODING));
          paramsData.setContentType(contentType.getObjectAsString());
          httpEntityEnclosingRequestBase.setEntity(paramsData);
        }
      } else {

        List<NameValuePair> paramsData = new LinkedList<>();

        Map<?, ?> mapObject = params.getObjectAsMap();
        mapObject.entrySet().stream().forEach((entry) -> {
          paramsData.add(new BasicNameValuePair(Var.valueOf(entry.getKey()).getObjectAsString(),
              Var.valueOf(entry.getValue()).getObjectAsString()));
        });
        URI uri = new URIBuilder(httpMethod.getURI()).addParameters(paramsData).build();
        httpMethod.setURI(uri);
      }
      httpResponse = httpClient.execute(httpMethod);
    } else {
      List<NameValuePair> paramsData = new LinkedList<>();
      List<NameValuePair> postDataList = new LinkedList<>();
      List<NameValuePair> bothDataList = new LinkedList<>();
      if (!params.isNull() && params.getObject() instanceof Map) {
        Map<?, ?> mapObject = params.getObjectAsMap();
        mapObject.entrySet().stream().forEach((entry) -> {
          paramsData.add(new BasicNameValuePair(Var.valueOf(entry.getKey()).getObjectAsString(),
              Var.valueOf(entry.getValue()).getObjectAsString()));
        });
      }
      if (!postData.isNull() && postData.getObject() instanceof Map) {
        Map<?, ?> mapObject = postData.getObjectAsMap();
        mapObject.entrySet().stream().forEach((entry) -> {
          postDataList.add(new BasicNameValuePair(Var.valueOf(entry.getKey()).getObjectAsString(),
              Var.valueOf(entry.getValue()).getObjectAsString()));
        });
      }
      bothDataList.addAll(paramsData);
      bothDataList.addAll(postDataList);

      if (httpMethod instanceof HttpEntityEnclosingRequestBase) {

        HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase = (HttpEntityEnclosingRequestBase) httpMethod;

        if (contentType.getObjectAsString().equals(APPLICATION_X_WWW_FORM_URLENCODED)) {
          httpEntityEnclosingRequestBase.setEntity(new UrlEncodedFormEntity(bothDataList, cronapi.CronapiConfigurator.ENCODING));
        } else {
          JsonObject fusionObject = new JsonObject();
          for (NameValuePair nameValuePair : bothDataList) {
            fusionObject.add(nameValuePair.getName(), nameValuePair.getValue());
          }
          StringEntity postToData = new StringEntity(fusionObject.toString(),
              Charset.forName(cronapi.CronapiConfigurator.ENCODING));
          postToData.setContentType(contentType.getObjectAsString());
          httpEntityEnclosingRequestBase.setEntity(postToData);
        }
      } else {
        URI uri = new URIBuilder(httpMethod.getURI()).addParameters(bothDataList).build();
        httpMethod.setURI(uri);
      }
      httpResponse = httpClient.execute(httpMethod);
    }

    if (returnType != null && returnType.equals("HEADER")) {
      Header[] headers = httpResponse.getAllHeaders();
      for (Header header : headers) {
        responseMap.put(header.getName(), header.getValue());
      }
      toReturn = Var.valueOf(responseMap);
    } else {
      Scanner scanner;
      if (httpResponse.getEntity() != null && httpResponse.getEntity().getContent() != null) {
        scanner = new Scanner(httpResponse.getEntity().getContent(),
            cronapi.CronapiConfigurator.ENCODING);
      } else {
        scanner = new Scanner("");
      }
      String response = "";
      try {
        response = scanner.useDelimiter("\\A").next();
      } catch (Exception e) {
      }
      scanner.close();
      toReturn = Var.valueOf(response);
    }
    httpMethod.completed();
    return toReturn;

  }

  @CronapiMetaData(type = "function", name = "{{getFromSession}}", nameTags = {
      "getFromSession"}, description = "{{getFromSessionDescription}}", returnType = ObjectType.STRING)
  public static final Var getValueFromSession(
      @ParamMetaData(type = ObjectType.STRING, description = "{{fieldName}}") Var fieldName) throws Exception {
    return Var.valueOf(RestClient.getRestClient().getSessionValue(fieldName.toString()));
  }

  @CronapiMetaData(type = "function", name = "{{setInSession}}", nameTags = {
      "setInSession"}, description = "{{setInSessionDescription}}", returnType = ObjectType.STRING)
  public static final void getValueFromSession(
      @ParamMetaData(type = ObjectType.STRING, description = "{{fieldName}}") Var fieldName,
      @ParamMetaData(type = ObjectType.STRING, description = "{{fieldValue}}") Var fieldValue) throws Exception {
    RestClient.getRestClient().updateSessionValue(fieldName.toString(), fieldValue);
  }

  // Internal Function - Missing translation
  // @CronapiMetaData(type = "function", name = "{{readLinesFromStreamName}}", nameTags = {
  // "readLinesFromStream" }, description = "{{readLinesFromStreamDescription}}", params = {
  // "{{readLinesFromStreamParam0}}", "{{readLinesFromStreamParam1}}" }, paramsType = { ObjectType.OBJECT,
  // ObjectType.STATEMENTSENDER })
  public static void readLinesFromStream(Var input, Callback callback) throws Exception {
    BufferedReader reader = new BufferedReader(new InputStreamReader((InputStream) input.getObject()));
    String line;
    while ((line = reader.readLine()) != null) {
      callback.call(Var.valueOf(line));
    }
  }

  // Internal Function - Missing translation
  // @CronapiMetaData(type = "function", name = "{{readBytesFromStreamName}}", nameTags = {
  // "readBytesFromStream" }, description = "{{readBytesFromStreamDescription}}", params = {
  // "{{readBytesFromStreamParam0}}", "{{readBytesFromStreamParam1}}",
  // "{{readBytesFromStreamParam2}}", }, paramsType = { ObjectType.OBJECT, ObjectType.LONG,
  // ObjectType.STATEMENTSENDER })
  public static final void readBytesFromStream(Var input, Var size, Callback callback) throws Exception {
    byte[] buffer = new byte[size.getObjectAsInt() > 0 ? size.getObjectAsInt() : 1024];
    InputStream ios = (InputStream) input.getObject();
    int read = 0;
    while ((read = ios.read(buffer)) != -1) {
      byte[] readBytes = Arrays.copyOf(buffer, read);
      callback.call(Var.valueOf(readBytes));
    }
  }

  @CronapiMetaData(type = "function", name = "{{generateUUIDName}}", nameTags = {
      "generateUUID"}, description = "{{generateUUIDDescription}}", paramsType = {ObjectType.STRING})
  public static final Var generateUUID() {
    return Var.valueOf(UUID.randomUUID().toString());
  }

  // Poolsize ExecutorService
  private final static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_POOLSIZE);

  @CronapiMetaData(type = "function", name = "{{executeAsync}}", nameTags = {
      "executeAsync"}, description = "{{executeAsyncDescription}}", returnType = ObjectType.VOID, params = {
      "{{cmd}}"}, paramsType = {ObjectType.STATEMENT})
  public static final void executeAsync(Runnable cmd) throws Exception {
    threadPool.execute(RestClient.getContextRunnable(cmd, true));
  }

  // Poolsize ScheduledExecutorService
  private static final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(THREAD_POOLSIZE);

  @CronapiMetaData(type = "function", name = "{{scheduleExecution}}", nameTags = {
      "scheduleExecution"}, description = "{{scheduleExecutionDescription}}", returnType = ObjectType.VOID)
  public static final void scheduleExecution(
      @ParamMetaData(type = ObjectType.STATEMENT, description = "{{cmd}}") Runnable cmd,
      @ParamMetaData(type = ObjectType.LONG, description = "{{initialTime}}") Var initialTime,
      @ParamMetaData(type = ObjectType.LONG, description = "{{updateTime}}") Var updateTime,
      @ParamMetaData(type = ObjectType.OBJECT, description = "{{timeUnit}}", blockType = "util_dropdown", keys = {
          "SECONDS", "MILLISECONDS", "MINUTES",
          "HOURS"}, values = {"{{SECONDS}}", "{{MILLISECONDS}}", "{{MINUTES}}", "{{HOURS}}"}) Var unit)
      throws Exception {

    TimeUnit timeUnit = TimeUnit.SECONDS;

    if ("SECONDS".equalsIgnoreCase(unit.getObjectAsString()))
      timeUnit = TimeUnit.SECONDS;
    if ("MILLISECONDS".equalsIgnoreCase(unit.getObjectAsString()))
      timeUnit = TimeUnit.MILLISECONDS;
    if ("MINUTES".equalsIgnoreCase(unit.getObjectAsString()))
      timeUnit = TimeUnit.MINUTES;
    if ("HOURS".equalsIgnoreCase(unit.getObjectAsString()))
      timeUnit = TimeUnit.HOURS;

    long init = (initialTime.isNull() ? 0 : initialTime.getObjectAsLong());
    long update = (updateTime.isNull() ? 0 : updateTime.getObjectAsLong());

    Runnable run = RestClient.getContextRunnable(cmd, true);

    if (update == 0) {
      executor.schedule(run, init, timeUnit);
    } else {
      executor.scheduleWithFixedDelay(run, init, update, timeUnit);
    }

  }

  @CronapiMetaData(type = "function", name = "{{uploadName}}", nameTags = {"upload", "enviar"}, description = "{{uploadDescription}}")
  public static final void upload(
      @ParamMetaData(type = ObjectType.STRING, description = "{{uploadParam}}") Var description,
      @ParamMetaData(type = ObjectType.STRING, defaultValue = "*", description = "{{uploadParam0}}") Var filter,
      @ParamMetaData(type = ObjectType.STRING, defaultValue = "20MB", description = "{{uploadParam1}}") Var maxSize,
      @ParamMetaData(type = ObjectType.OBJECT, description = "{{uploadParam2}}",
          blockType = "util_dropdown", keys = {"true", "false"},
          values = {"{{yes}}", "{{no}}"}) Var multiple,
      @ParamMetaData(type = ObjectType.STATEMENTSENDER, description = "{{uploadParam3}}") cronapi.util.Callback callback
  ) throws Exception {
    String id = DownloadREST.authorizeUpload(callback);
    RestClient.getRestClient().addCommand("cronapi.util.upload").addParam(id, description, filter, maxSize, multiple);
  }

  @CronapiMetaData(type = "internal")
  public static String translateAcentos(String aValue) {
    final String CHR_ACENTUADA = "àèìòùáéíóúâêîôûãõçñäëïöüÀÈÌÒÙÁÉÍÓÚÂÊÎÔÛÃÕÇÑÄËÏÖÜ";
    final String CHR_NAO_ACENTUADA = "aeiouaeiouaeiouaocnaeiouAEIOUAEIOUAEIOUAOCNAEIOU";
    int idx, idxpos;
    StringBuilder result = new StringBuilder();
    for (idx = 0; idx < aValue.length(); idx++) {
      idxpos = CHR_ACENTUADA.indexOf(aValue.charAt(idx));
      if (idxpos != -1) {
        result.append(CHR_NAO_ACENTUADA.charAt(idxpos));
      } else {
        result.append(aValue.charAt(idx));
      }
    }
    return result.toString();
  }

  @CronapiMetaData(type = "internal")
  public static String reduceVariable(String var, boolean notClassName) {
    String reducedVariable = null;

    if (var != null) {
      // Retira acentos
      if (notClassName) {
        reducedVariable = translateAcentos(var.toUpperCase()).trim().replaceAll("\\s", "_");

        // Troca os caracteres especiais por "_"
        Pattern pattern = Pattern.compile("^\\d+|\\W");
        Matcher matcher = pattern.matcher(reducedVariable);
        reducedVariable = matcher.replaceAll("_");
      } else {
        reducedVariable = translateAcentos(var);

        // Troca os caracteres especiais por " "
        Pattern pattern = Pattern.compile("\\W");
        Matcher matcher = pattern.matcher(reducedVariable);
        reducedVariable = matcher.replaceAll(" ").trim();

        pattern = Pattern.compile("^[\\d\\W]+");
        matcher = pattern.matcher(reducedVariable);
        reducedVariable = matcher.replaceAll(" ").trim();

        // Troca 2 ou mais espaços por 1 espaço e "_" por 1 espaço,
        // depois um trim()
        reducedVariable = reducedVariable.replaceAll("\\s{2,}", " ").replaceAll("_", " ").trim();

        if (reducedVariable.length() > 1) {
          reducedVariable = reducedVariable.substring(0, 1).toUpperCase() + reducedVariable.substring(1);
        }

        // Após os espaços deve-se colocar a letra maiúscula
        int spacePosition;
        while ((spacePosition = reducedVariable.indexOf(" ")) != -1) {
          String aux = reducedVariable.substring(spacePosition + 1);

          reducedVariable = reducedVariable.substring(0, spacePosition) + firstToUpper(aux);
        }
      }
    }

    if (reducedVariable == null || reducedVariable.trim().length() == 0) {
      return reduceVariable((notClassName ? "Identifier" : "Class") + var, notClassName);
    } else
      return reducedVariable;
  }

  @CronapiMetaData(type = "internal")
  public static String firstToUpper(String text) {
    if (text.length() >= 2) {
      return text.substring(0, 1).toUpperCase() + text.substring(1).toLowerCase();
    } else {
      return text.toUpperCase();
    }
  }

  @CronapiMetaData(type = "function", name = "{{redirect}}", nameTags = {"redirecionar", "redirect"}, description = "{{redirectDescription}}")
  public static final void redirect(
      @ParamMetaData(type = ObjectType.STRING, description = "{{location}}") Var location
  ) throws Exception {
    RestClient.getRestClient().getResponse().sendRedirect(location.getObjectAsString());
  }

  @CronapiMetaData(type = "function", name = "{{createDownloadURL}}", nameTags = {"download", "url"}, description = "{{createDownloadURLDescription}}")
  public static Var createDownloadLink(@ParamMetaData(type = ObjectType.STRING, description = "{{createDownloadURLParam}}") Var file) {
    return Var.valueOf(DownloadREST.getDownloadUrl(new File(file.toString())));
  }

  @CronapiMetaData(type = "function", name = "{{getSystemParam}}", nameTags = {"system", "parameter"}, description = "{{getSystemParam}}")
  public static Var getSystemParameter(@ParamMetaData(type = ObjectType.STRING, description = "{{getSystemParamKey}}") Var key) {
    Var value = CronapiBeanConfigurator.INIT_PARAMS.get(key.toString());
    if (value != null) {
      return value;
    }

    return Var.VAR_EMPTY;
  }

  @CronapiMetaData(type = "function", name = "{{getSystemProfile}}", nameTags = {"system", "profile"}, description = "{{getSystemProfile}}")
  public static Var getSystemProfile() {
    return getSystemParameter(Var.valueOf("app.profile"));
  }

  private static Level toLevel(String strLevel) {
    Level level = Level.INFO;

    if (strLevel.equals("FINE")) {
      level = Level.FINE;
    } else if (strLevel.equals("FINER")) {
      level = Level.FINER;
    } else if (strLevel.equals("FINEST")) {
      level = Level.FINEST;
    } else if (strLevel.equals("SEVERE")) {
      level = Level.SEVERE;
    } else if (strLevel.equals("WARNING")) {
      level = Level.WARNING;
    } else if (strLevel.equals("CONFIG")) {
      level = Level.CONFIG;
    } else if (strLevel.equals("ALL")) {
      level = Level.ALL;
    } else if (strLevel.equals("OFF")) {
      level = Level.OFF;
    }

    return level;
  }

  @CronapiMetaData(type = "function", name = "{{log}}", nameTags = {"log", "imprimir", "logging", "logar"}, description = "{{logDescription}}")
  public static void log(
      @ParamMetaData(type = ObjectType.STRING, description = "{{logCategory}}", defaultValue = "General") Var category,
      @ParamMetaData(type = ObjectType.STRING, description = "{{logLevel}}", blockType = "util_dropdown",
          keys = {"INFO", "SEVERE", "WARNING", "CONFIG", "FINE", "FINER", "FINEST"},
          values = {"{{INFO}}", "{{SEVERE}}", "{{WARNING}}", "{{CONFIG}}", "{{FINE}}", "{{FINER}}", "{{FINEST}}"},
          defaultValue = "INFO"
      ) Var type,
      @ParamMetaData(type = ObjectType.STRING, description = "{{logMessage}}") Var message,
      @ParamMetaData(type = ObjectType.OBJECT, description = "{{logDetail}}") Var exception
  ) {

    if (category == null || category.isEmptyOrNull()) {
      category = Var.valueOf("General");
    }

    Logger log = LOGGERS.get(category.getObjectAsString());

    if (log == null) {
      log = Logger.getLogger(category.getObjectAsString());

      if (LOG_DEFINED) {
        log.setLevel(LOG_LEVEL);
        Logger parent = log.getParent();
        while (parent != null) {
          for (Handler handler : parent.getHandlers()) {
            handler.setLevel(LOG_LEVEL);
          }
          parent = parent.getParent();
        }
      } else if (!getSystemParameter(Var.valueOf("app.loglevel")).isEmptyOrNull()) {
        Level newLevel = toLevel(getSystemParameter(Var.valueOf("app.loglevel")).getObjectAsString());
        log.setLevel(newLevel);
        Logger parent = log.getParent();
        while (parent != null) {
          for (Handler handler : parent.getHandlers()) {
            handler.setLevel(newLevel);
          }
          parent = parent.getParent();
        }
      } else {

      }

      LOGGERS.put(category.getObjectAsString(), log);
    }

    Level level = toLevel(type.getObjectAsString());

    if (exception != null && !exception.isEmptyOrNull() && exception.getObject() instanceof Throwable) {
      log.log(level, message.getObjectAsString(), (Throwable) exception.getObject());
    } else if (exception != null && !exception.isEmptyOrNull()) {
      log.log(level, message.getObjectAsString(), exception.getObjectAsString());
    } else {
      log.log(level, message.getObjectAsString());
    }
  }

  @CronapiMetaData(type = "function", name = "{{audit}}", nameTags = {"audit", "auditar"}, description = "{{auditDescription}}")
  public static void audit(
      @ParamMetaData(type = ObjectType.STRING, description = "{{auditType}}", defaultValue = "General") Var type,
      @ParamMetaData(type = ObjectType.STRING, description = "{{auditCommand}}") Var command,
      @ParamMetaData(type = ObjectType.STRING, description = "{{auditCategory}}", defaultValue = "Trace") Var category,
      @ParamMetaData(type = ObjectType.STRING, description = "{{auditData}}") Var data
  ) throws Exception {
    DatabaseQueryManager logManager = HistoryListener.getAuditLogManager();

    if (logManager != null) {

      Var auditLog = new Var(new LinkedHashMap<>());

      auditLog.set("type", type.getObjectAsString());
      auditLog.set("command", command.getObjectAsString());
      auditLog.set("category", category.getObjectAsString());
      auditLog.set("date", new Date());
      auditLog.set("objectData", data.getObjectAsString());
      if (RestClient.getRestClient() != null) {
        auditLog.set("user", RestClient.getRestClient().getUser() != null ? RestClient.getRestClient().getUser().getUsername() : null);
        auditLog.set("host", RestClient.getRestClient().getHost());
        auditLog.set("agent", RestClient.getRestClient().getAgent());
      }
      auditLog.set("server", HistoryListener.CURRENT_IP);
      auditLog.set("affectedFields", null);
      auditLog.set("application", AppConfig.guid());

      logManager.insert(auditLog);

    }
  }
}
