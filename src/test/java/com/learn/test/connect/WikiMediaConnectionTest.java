package com.learn.test.connect;

import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;


class WikiMediaConnectionTest {

  private static String JSON_GERMAN_AUTOMAKERS = "wikimedia/german-automakers.json";

  @Mock
  WikiMediaConnection wikiMediaConnection;

  @Test
  void createRequest() {
  }

  @Test
  void doWikiMediaRequest() {
  }

  @Test
  void doMetaDataRequest() {
  }

  @Test
  void testDoWikiMediaRequest() throws IOException, NoSuchAlgorithmException {

    when(wikiMediaConnection.doGetRequest("", 0))
        .thenReturn(Files.readString(Paths.get("src", "test", "resources", JSON_GERMAN_AUTOMAKERS)));

    wikiMediaConnection.doWikiMediaRequest("", 10, "1");
  }
}