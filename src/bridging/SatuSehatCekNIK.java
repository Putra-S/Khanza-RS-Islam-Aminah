/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bridging;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import fungsi.koneksiDB;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;

/**
 * @author khanzasoft
 */
public class SatuSehatCekNIK {

  private String link = "", json = "";
  public String birthDate = "",
      province = "",
      provincename = "",
      city = "",
      cityname = "",
      district = "",
      districtname = "",
      village = "",
      villagename = "",
      rt = "",
      rw = "",
      line = "",
      postalCode = "",
      gender = "",
      noktp = "",
      idpasien = "",
      maritalStatus = "",
      name = "",
      phone = "",
      email = "";

  private ApiSatuSehat api = new ApiSatuSehat();

  private HttpHeaders headers;

  private HttpEntity requestEntity;

  private ObjectMapper mapper = new ObjectMapper();

  private JsonNode root;

  private JsonNode response;

  private FileReader dataPropinsi, dataKabupaten, dataKecamatan, dataKelurahan;

  /** */
  public SatuSehatCekNIK() {
    super();
    try {
      link = koneksiDB.URLFHIRSATUSEHAT();
    } catch (Exception e) {
      System.out.println("Notif : " + e);
    }

    try {
      dataPropinsi = new FileReader("./cache/propinsi.iyem");
    } catch (FileNotFoundException e) {
      System.out.println("Notif : " + e);
    }
    try {
      dataKabupaten = new FileReader("./cache/kabupaten.iyem");
    } catch (FileNotFoundException e) {
      System.out.println("Notif : " + e);
    }
    try {
      dataKecamatan = new FileReader("./cache/kecamatan.iyem");
    } catch (FileNotFoundException e) {
      System.out.println("Notif : " + e);
    }
    try {
      dataKelurahan = new FileReader("./cache/kelurahan.iyem");
    } catch (FileNotFoundException e) {
      System.out.println("Notif : " + e);
    }
  }

  /**
   * @param cari
   */
  public void tampil(String cari) {
    try {
      birthDate = "";
      province = "";
      provincename = "";
      city = "";
      cityname = "";
      district = "";
      districtname = "";
      village = "";
      villagename = "";
      rt = "";
      rw = "";
      line = "";
      postalCode = "";
      gender = "";
      noktp = "";
      idpasien = "";
      maritalStatus = "";
      name = "";
      phone = "";
      email = "";
      headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
      requestEntity = new HttpEntity(headers);
      System.out.println(
          "URL : " + link + "/Patient?identifier=https://fhir.kemkes.go.id/id/nik|" + cari);
      json =
          api.getRest()
              .exchange(
                  link + "/Patient?identifier=https://fhir.kemkes.go.id/id/nik|" + cari,
                  HttpMethod.GET,
                  requestEntity,
                  String.class)
              .getBody();
      System.out.println("JSON : " + json);
      root = mapper.readTree(json);
      for (JsonNode list : root.path("entry")) {
        idpasien = list.path("resource").path("id").asText();
        noktp = cari;
        try {
          headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON);
          headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
          requestEntity = new HttpEntity(headers);
          System.out.println("URL : " + link + "/Patient/" + idpasien);
          json =
              api.getRest()
                  .exchange(
                      link + "/Patient/" + idpasien, HttpMethod.GET, requestEntity, String.class)
                  .getBody();
          System.out.println("JSON : " + json);
          root = mapper.readTree(json);
          gender =
              root.path("gender").asText().toLowerCase().equals("male") ? "Laki-laki" : "Perempuan";
          birthDate = root.path("birthDate").asText();
          maritalStatus =
              root.path("maritalStatus").path("text").asText().toLowerCase().equals("married")
                  ? "Menikah"
                  : "Belum Menikah";
          for (JsonNode listname : root.path("name")) {
            name = listname.path("text").asText();
          }
          for (JsonNode listtelecom : root.path("telecom")) {
            if (listtelecom.path("system").asText().equals("phone")) {
              phone = listtelecom.path("value").asText();
            } else if (listtelecom.path("system").asText().equals("email")) {
              email = listtelecom.path("value").asText();
            }
          }
          for (JsonNode listaddress : root.path("address")) {
            line = listaddress.path("line").get(0).asText();
            postalCode = listaddress.path("postalCode").asText();
            for (JsonNode listextension : listaddress.path("extension")) {
              for (JsonNode listextensionextension : listextension.path("extension")) {
                if (listextensionextension.path("url").asText().equals("province")) {
                  province = listextensionextension.path("valueCode").asText();
                } else if (listextensionextension.path("url").asText().equals("city")) {
                  city = listextensionextension.path("valueCode").asText();
                } else if (listextensionextension.path("url").asText().equals("district")) {
                  district = listextensionextension.path("valueCode").asText();
                } else if (listextensionextension.path("url").asText().equals("village")) {
                  village = listextensionextension.path("valueCode").asText();
                } else if (listextensionextension.path("url").asText().equals("rt")) {
                  rt = listextensionextension.path("valueCode").asText();
                } else if (listextensionextension.path("url").asText().equals("rw")) {
                  rw = listextensionextension.path("valueCode").asText();
                }
              }
            }
          }
        } catch (HttpClientErrorException | HttpServerErrorException ea) {
          // Handle client and server errors
          System.err.println("Error Response Status Code: " + ea.getStatusCode());

          // You can further parse the error response body if needed
          ObjectMapper mapper = new ObjectMapper();
          JsonNode errorResponse = mapper.readTree(ea.getResponseBodyAsString());
          ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
          String prettyErrorResponse = writer.writeValueAsString(errorResponse);
          System.err.println("Error Response JSON: \n" + prettyErrorResponse);
        }
        response = mapper.readTree(dataKelurahan).path("kelurahan");
        for (JsonNode listkelurahan : response) {
          if (listkelurahan.path("id").asText().toLowerCase().equals(village)
              && listkelurahan.path("id_kecamatan").asText().equals(district)) {
            villagename = listkelurahan.path("nama").asText();
          }
        }
        response = mapper.readTree(dataKecamatan).path("kecamatan");
        for (JsonNode listkcamatan : response) {
          if (listkcamatan.path("id").asText().toLowerCase().equals(district)
              && listkcamatan.path("id_kabupaten").asText().equals(city)) {
            districtname = listkcamatan.path("nama").asText();
          }
        }
        response = mapper.readTree(dataKabupaten).path("kabupaten");
        for (JsonNode listkabupaten : response) {
          if (listkabupaten.path("id").asText().toLowerCase().equals(city)
              && listkabupaten.path("id_propinsi").asText().equals(province)) {
            cityname = listkabupaten.path("nama").asText();
          }
        }
        response = mapper.readTree(dataPropinsi).path("propinsi");
        for (JsonNode listpropinsi : response) {
          if (listpropinsi.path("id").asText().toLowerCase().equals(province)) {
            provincename = listpropinsi.path("nama").asText();
          }
        }
      }

      if (name.isEmpty()) {
        try {
          headers = new HttpHeaders();
          headers.setContentType(MediaType.APPLICATION_JSON);
          headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
          requestEntity = new HttpEntity(headers);
          System.out.println("URL : " + link + "/Patient/" + cari);
          json =
              api.getRest()
                  .exchange(link + "/Patient/" + cari, HttpMethod.GET, requestEntity, String.class)
                  .getBody();
          System.out.println("JSON : " + json);
          root = mapper.readTree(json);
          idpasien = cari;
          gender =
              root.path("gender").asText().toLowerCase().equals("male") ? "Laki-laki" : "Perempuan";
          birthDate = root.path("birthDate").asText();
          maritalStatus =
              root.path("maritalStatus").path("text").asText().toLowerCase().equals("married")
                  ? "Menikah"
                  : "Belum Menikah";
          for (JsonNode listname : root.path("name")) {
            name = listname.path("text").asText();
          }
          for (JsonNode listtelecom : root.path("telecom")) {
            if (listtelecom.path("system").asText().equals("phone")) {
              phone = listtelecom.path("value").asText();
            }
            if (listtelecom.path("system").asText().equals("email")) {
              email = listtelecom.path("value").asText();
            }
          }
          for (JsonNode listnoktp : root.path("identifier")) {
            if (listnoktp.path("system").asText().equals("https://fhir.kemkes.go.id/id/nik")) {
              noktp = listnoktp.path("value").asText();
            }
          }
          for (JsonNode listaddress : root.path("address")) {
            line = listaddress.path("line").get(0).asText();
            postalCode = listaddress.path("postalCode").asText();
            for (JsonNode listextension : listaddress.path("extension")) {
              for (JsonNode listextensionextension : listextension.path("extension")) {
                if (listextensionextension.path("url").asText().equals("province")) {
                  province = listextensionextension.path("valueCode").asText();
                }
                if (listextensionextension.path("url").asText().equals("city")) {
                  city = listextensionextension.path("valueCode").asText();
                }
                if (listextensionextension.path("url").asText().equals("district")) {
                  district = listextensionextension.path("valueCode").asText();
                }
                if (listextensionextension.path("url").asText().equals("village")) {
                  village = listextensionextension.path("valueCode").asText();
                }
                if (listextensionextension.path("url").asText().equals("rt")) {
                  rt = listextensionextension.path("valueCode").asText();
                }
                if (listextensionextension.path("url").asText().equals("rw")) {
                  rw = listextensionextension.path("valueCode").asText();
                }
              }
            }
          }
        } catch (HttpClientErrorException | HttpServerErrorException ea) {
          // Handle client and server errors
          System.err.println("Error Response Status Code: " + ea.getStatusCode());

          // You can further parse the error response body if needed
          ObjectMapper mapper = new ObjectMapper();
          JsonNode errorResponse = mapper.readTree(ea.getResponseBodyAsString());
          ObjectWriter writer = mapper.writerWithDefaultPrettyPrinter();
          String prettyErrorResponse = writer.writeValueAsString(errorResponse);
          System.err.println("Error Response JSON: \n" + prettyErrorResponse);
        }
        response = mapper.readTree(dataKelurahan).path("kelurahan");
        for (JsonNode listkelurahan : response) {
          if (listkelurahan.path("id").asText().toLowerCase().equals(village)
              && listkelurahan.path("id_kecamatan").asText().equals(district)) {
            villagename = listkelurahan.path("nama").asText();
          }
        }
        response = mapper.readTree(dataKecamatan).path("kecamatan");
        for (JsonNode listkcamatan : response) {
          if (listkcamatan.path("id").asText().toLowerCase().equals(district)
              && listkcamatan.path("id_kabupaten").asText().equals(city)) {
            districtname = listkcamatan.path("nama").asText();
          }
        }
        response = mapper.readTree(dataKabupaten).path("kabupaten");
        for (JsonNode listkabupaten : response) {
          if (listkabupaten.path("id").asText().toLowerCase().equals(city)
              && listkabupaten.path("id_propinsi").asText().equals(province)) {
            cityname = listkabupaten.path("nama").asText();
          }
        }
        response = mapper.readTree(dataPropinsi).path("propinsi");
        for (JsonNode listpropinsi : response) {
          if (listpropinsi.path("id").asText().toLowerCase().equals(province)) {
            provincename = listpropinsi.path("nama").asText();
          }
        }
      }
    } catch (Exception e) {
      System.out.println("Notifikasi : " + e);
    }

    if (name.isEmpty()) {
      JOptionPane.showMessageDialog(null, "Maaf, Belum Ada data di Server Satu Sehat");
    }
  }

  /**
   * @param cari
   * @return
   */
  public String tampilIDPasien(String cari) {
    idpasien = "";
    try {
      headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
      requestEntity = new HttpEntity(headers);
      System.out.println(
          "URL : " + link + "/Patient?identifier=https://fhir.kemkes.go.id/id/nik|" + cari);
      json =
          api.getRest()
              .exchange(
                  link + "/Patient?identifier=https://fhir.kemkes.go.id/id/nik|" + cari,
                  HttpMethod.GET,
                  requestEntity,
                  String.class)
              .getBody();
      System.out.println("JSON : " + json);
      root = mapper.readTree(json);
      for (JsonNode list : root.path("entry")) {
        idpasien = list.path("resource").path("id").asText();
      }
    } catch (Exception e) {
      idpasien = "";
      System.out.println("Notifikasi : " + e);
    }
    return idpasien;
  }

  /**
   * @param cari
   * @return
   */
  public String tampilIDParktisi(String cari) {
    idpasien = "";
    try {
      headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      headers.add("Authorization", "Bearer " + api.TokenSatuSehat());
      requestEntity = new HttpEntity(headers);
      System.out.println(
          "URL : " + link + "/Practitioner?identifier=https://fhir.kemkes.go.id/id/nik|" + cari);
      json =
          api.getRest()
              .exchange(
                  link + "/Practitioner?identifier=https://fhir.kemkes.go.id/id/nik|" + cari,
                  HttpMethod.GET,
                  requestEntity,
                  String.class)
              .getBody();
      System.out.println("JSON : " + json);
      root = mapper.readTree(json);
      response = root.path("entry");
      for (JsonNode list : response) {
        idpasien = list.path("resource").path("id").asText();
      }
    } catch (IOException
        | KeyManagementException
        | NoSuchAlgorithmException
        | RestClientException e) {
      idpasien = "";
      System.out.println("Notifikasi : " + e);
    }
    return idpasien;
  }

  private static final Logger LOG = Logger.getLogger(SatuSehatCekNIK.class.getName());
}
