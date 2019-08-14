package com.kunjal.sample;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ContentTest {

  @Test
  public void requiredFieldExists() throws Exception {

    Set<Person> persons = getPersons();

    persons.forEach(person -> {
      if (person.getName() == null || person.getName().isEmpty()) {
        Assert.fail("name not defined in person ".concat(person.toString()));
      }

      if (person.getAddress() == null || person.getAddress().isEmpty()) {
        Assert.fail("address not defined in person ".concat(person.toString()));
      }
    });

  }

  private Set<Person> getPersons() throws Exception {

    InputStream inputStream =
        Thread.currentThread().getContextClassLoader()
            .getResourceAsStream("content/test1.json");
    Set people = getRulesFromInputStreams(inputStream);
    return people;
  }

  private Set<Person> getRulesFromInputStreams(InputStream inputStream) throws Exception {
    Set<Person> persons = new HashSet<>();

    if (inputStream != null) {
      String result = "";
      try {

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
          result = br.lines().collect(Collectors.joining(System.lineSeparator()));
        }

        JSONArray jsonRules = new JSONArray(result);
        if (jsonRules != null) {
          for (Object eachJson : jsonRules) {
            JSONObject jsonObject = (JSONObject) eachJson;
            persons.add(Person.toPerson(jsonObject));;
          }
        }
      } catch (Exception any) {
        throw new Exception("Exception parsing json from inputstream" + result);
      }
    }
    return persons;
  }

}
