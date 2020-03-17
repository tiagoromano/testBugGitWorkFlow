package cronapi.pushnotification;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirebasePushNotificationService {

  private String FIREBASE_SERVER_KEY;
  private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

  public FirebasePushNotificationService() {
  }

  public FirebasePushNotificationService(String serverKEY) {
    this.FIREBASE_SERVER_KEY = serverKEY;
  }

  @Async
  public CompletableFuture<String> send(HttpEntity<String> entity) {

    RestTemplate restTemplate = new RestTemplate();

    ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
    interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
    restTemplate.setInterceptors(interceptors);
    restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
    String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);

    return CompletableFuture.completedFuture(firebaseResponse);
  }

}
