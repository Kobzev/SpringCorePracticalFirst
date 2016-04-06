package ua.kobzev.theatre.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import ua.kobzev.theatre.domain.Event;
import ua.kobzev.theatre.domain.User;
import ua.kobzev.theatre.enums.EventRate;

import java.io.IOException;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by kkobziev on 4/6/16.
 */
public class TestRestClient {

    private RestTemplate restTemplate;

    public static void main(String ... args){
        TestRestClient testRestClient = new TestRestClient();
        testRestClient.userCRUD();
        testRestClient.eventCRUD();
        testRestClient.bookingService();
        testRestClient.bookingPDFService();
    }

    public TestRestClient() {
        restTemplate = new RestTemplate();
        restTemplate.setMessageConverters(getMessageConverters());
    }

    private List<HttpMessageConverter<?>> getMessageConverters() {
        List<HttpMessageConverter<?>> converters = new ArrayList<>();
        converters.add(new MappingJackson2HttpMessageConverter());
        return converters;
    }

    private void userCRUD(){
        User testUser = new User("testAdmin@admin.com", "testAdmin", LocalDateTime.now());
        testUser.setPassword("test");
        saveUser(testUser);
        List<User> userList = findAllUsers();
        User existUser = userList.stream().filter(user -> testUser.getEmail().equals(user.getEmail())).findFirst().orElse(null);
        if (existUser == null) throw new NullPointerException();
        getUserById(existUser);
        updateUser(existUser);
        deleteUser(existUser);
    }

    private void eventCRUD(){
        Event testEvent = new Event();
        testEvent.setName("testEvent");
        testEvent.setBasePrice(33.5d);
        testEvent.setRate(EventRate.HIGH);

        saveEvent(testEvent);
        List<Event> eventList = findAllEvents();
        Event existEvent = eventList.stream().filter(event -> testEvent.getName().equals(event.getName())).findFirst().orElse(null);
        if (existEvent == null) throw new NullPointerException();
        getEventByName(existEvent);
        updateEvent(existEvent);
        deleteEvent(existEvent);

    }

    private void bookingService(){

    }

    private void bookingPDFService(){

    }

    //User
    private void saveUser(User user){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        URI location = restTemplate.postForLocation("http://localhost:8080/rest/users", entity);
    }

    private List<User> findAllUsers(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:8080/rest/users", String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(response.getBody());
            User[] users = mapper.treeToValue(rootNode, User[].class);
            return Arrays.asList(users);
        } catch (IOException e) {

        }

        return null;

    }

    private void getUserById(User existUser){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:8080/rest/users/"+existUser.getId(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(response.getBody());
            User user = mapper.treeToValue(rootNode, User.class);
            System.out.println(user);
        } catch (IOException e) {

        }
    }

    private void updateUser(User existUser){
        User user = new User();
        user.setName("deleteName");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestUpdate = new HttpEntity<>(user, headers);
        restTemplate.exchange("http://localhost:8080/rest/users/"+existUser.getId(), HttpMethod.PUT, requestUpdate, User.class);

    }

    private void deleteUser(User existUser){
        restTemplate.delete("http://localhost:8080/rest/users/"+existUser.getId());
    }

    //Event
    private void saveEvent(Event event){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Event> entity = new HttpEntity<>(event, headers);
        URI location = restTemplate.postForLocation("http://localhost:8080/rest/events", entity);
    }

    private List<Event> findAllEvents(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:8080/rest/events", String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(response.getBody());
            Event[] events = mapper.treeToValue(rootNode, Event[].class);
            return Arrays.asList(events);
        } catch (IOException e) {

        }

        return null;

    }

    private void getEventByName(Event existEvent){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response =
                restTemplate.getForEntity("http://localhost:8080/rest/events/"+existEvent.getName(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(response.getBody());
            Event event = mapper.treeToValue(rootNode, Event.class);
            System.out.println(event);
        } catch (IOException e) {

        }
    }

    private void updateEvent(Event existEvent){
        Event event = new Event();
        event.setRate(EventRate.LOW);
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Event> requestUpdate = new HttpEntity<>(event, headers);
        restTemplate.exchange("http://localhost:8080/rest/events/"+existEvent.getName(), HttpMethod.PUT, requestUpdate, Event.class);

    }

    private void deleteEvent(Event existEvent){
        restTemplate.delete("http://localhost:8080/rest/events/"+existEvent.getName());
    }

}
