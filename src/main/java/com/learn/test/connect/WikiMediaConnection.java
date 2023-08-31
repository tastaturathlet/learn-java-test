package com.learn.test.connect;

import com.learn.test.config.ApplicationConfiguration;
import jakarta.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * An HTTP Request to MetaData API An MetaDataConnection which connects via HTTP Get Request to the MetaData API.
 */
public class WikiMediaConnection {

  private static final Logger LOGGER = LogManager.getLogger(WikiMediaConnection.class);

  public static final String CONTENT_TYPE = "Content-Type";
  public static final String APPLICATION_JSON = "application, json";

  public static final int HTTP_STATUS_OK = 200;

  /**
   * Retry times after connection from MetaData API or response failed.
   */
  private static final int HTTP_RETRY = 3;
  /**
   * Retry connect to MetaData API after 5 minutes
   */
  public static final long RETRY_DURATION = 300000L;
  private ApplicationConfiguration applicationConfiguration;
  private static final long HTTP_TIMEOUT = 10L;

  private HttpClient httpClient;

  public WikiMediaConnection() {
    applicationConfiguration = ApplicationConfiguration.getInstance();
    httpClient = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_1_1)
        .connectTimeout(Duration.ofSeconds(HTTP_TIMEOUT))
        .build();
  }

  public HttpRequest createRequest(String metaDataItem, int skip, String id) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    return null;
   /* URI metaDataUri = URI.create(createMetaDataRequestUri(metaDataItem, skip, id));
    LOGGER.debug("[CONNECT] MetaData URI: {}", metaDataUri);

    return HttpRequest.newBuilder()
        .uri(metaDataUri)
        .timeout(Duration.ofSeconds(HTTP_TIMEOUT))
        .header(CONTENT_TYPE, APPLICATION_JSON)
        .GET()
        .build();*/
  }


  public String doWikiMediaRequest(HttpRequest request) {
    Integer retryCounter = 1;
    boolean isRequestSuccessfull = false;
    do {

      try {
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (HTTP_STATUS_OK == response.statusCode()) {
          isRequestSuccessfull = true;
          return response.body();
        } else {
          LOGGER.error("[HTTP_REQUEST] request to WikiMedia API failed! HTTP status:{}", response.statusCode());
        }
      } catch (IOException | InterruptedException e) {
        LOGGER.error("[HTTP_REQUEST] request to WikiMedia API failed! retry {}", retryCounter);
        if (retryCounter >= HTTP_RETRY) {
          throw new RuntimeException(e);
        }
        retryCounter++;

        try {
          Thread.sleep(RETRY_DURATION);
        } catch (InterruptedException ex) {
          LOGGER.error("[HTTP_REQUEST] retry wait failed!");
          throw new RuntimeException(ex);
        }

      }
    } while (!isRequestSuccessfull && retryCounter <= HTTP_RETRY);

    throw new RuntimeException("[HTTP_REQUEST] request to WikiMedia API failed!");
  }

  /**
   * @param metaDataItem
   * @param skip
   * @return
   * @throws IOException
   * @throws InterruptedException
   * @throws NoSuchAlgorithmException
   */
  public String doMetaDataRequest(String metaDataItem, int skip) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    HttpRequest request = createRequest(metaDataItem, skip, null);
    return doWikiMediaRequest(request);
  }

  public String doWikiMediaRequest(String metaDataItem, int skip, String id) throws NoSuchAlgorithmException, UnsupportedEncodingException {

    HttpRequest request = createRequest(metaDataItem, skip, id);
    return doWikiMediaRequest(request);
  }


  /*private String createMetaDataRequestUri(String metaDataItem, int skip, String id) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    StringBuilder uri = new StringBuilder();
    uri.append(applicationConfiguration.getMetadataUrl())
        // Example: /publication
        .append(MetaDataConstans.URI_SLASH).append(metaDataItem)
        // Example: ?token=<generated-token>
        .append(MetaDataConstans.URI_QUESTION_MARK).append(MetaDataConstans.URI_TOKEN).append(createSecurityToken(applicationConfiguration.getMetadatepassword()))
        // Example: &limit=2
        .append(MetaDataConstans.URI_AND).append(MetaDataConstans.URI_LIMIT).append(applicationConfiguration.getMetadataPageLimit())
        // Example: &skip=1
        .append(MetaDataConstans.URI_AND).append(MetaDataConstans.URI_SKIP).append(skip);

    if (id != null && !id.isEmpty()) {
      uri.append(MetaDataConstans.URI_AND).append("id=").append(id.replace(" ", "%20"));
    }

    return uri.toString();
  }*/

}
