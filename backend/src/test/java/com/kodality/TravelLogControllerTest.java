package com.kodality;

import com.kodality.dtos.TravelLog;
import com.kodality.dtos.User;
import com.kodality.dtos.UserLogSearchCriteria;
import io.micronaut.core.type.Argument;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class TravelLogControllerTest {

    @Inject
    @Client("/")
    HttpClient httpClient;

    @Test
    void search_user_by_search_critera() {
        UserLogSearchCriteria requestedSearchCriteria = new UserLogSearchCriteria();
        requestedSearchCriteria.setVehicleOwnerName("Soumen Banerjee");
        requestedSearchCriteria.setVehicleRegNumber("EST14016");

        HttpRequest<UserLogSearchCriteria> request = HttpRequest.POST("/api/travel-log-search", requestedSearchCriteria);
        HttpResponse<List<User>> response = httpClient.toBlocking().exchange(request, Argument.listOf(User.class));

        User searchedUser = response.body().stream().findFirst().get();

        assertTrue(searchedUser.getLogs().stream().anyMatch(getLog -> getLog.getVehicleRegNumber().equals("EST14016")));
        assertEquals("Soumen Banerjee", searchedUser.getName());
    }

    @Test
    void search_user_by_search_critera_but_not_found() {
        UserLogSearchCriteria requestedSearchCriteria = new UserLogSearchCriteria();
        requestedSearchCriteria.setVehicleOwnerName("Alex");
        requestedSearchCriteria.setVehicleRegNumber("3EST14016");

        HttpRequest<UserLogSearchCriteria> request = HttpRequest.POST("/api/travel-log-search", requestedSearchCriteria);
        HttpResponse<List<User>> response = httpClient.toBlocking().exchange(request, Argument.listOf(User.class));

        User getUser = response.body().stream().findFirst().orElseGet(User::new);

        assertNull(getUser.getName());
        assertNull(getUser.getLogs());
    }

    @Test
    void delete_travel_log_by_travel_log_id() {
        int deleteTravelLog = 1;

        HttpRequest<Void> requestToDeleteTravelLog = HttpRequest.DELETE("/api/delete-travel-log?travellog=" + deleteTravelLog);
        httpClient.toBlocking().exchange(requestToDeleteTravelLog);

        UserLogSearchCriteria requestedSearchCriteria = new UserLogSearchCriteria();
        requestedSearchCriteria.setVehicleOwnerName("Soumen Banerjee");

        HttpRequest<UserLogSearchCriteria> request = HttpRequest.POST("/api/travel-log-search", requestedSearchCriteria);
        HttpResponse<List<User>> response = httpClient.toBlocking().exchange(request, Argument.listOf(User.class));

        assertTrue(response.body().stream().flatMap(userData -> userData.getLogs().stream()
                        .filter(getLog -> getLog.getId() == deleteTravelLog)).collect(Collectors.toList()).isEmpty());
    }

    @Test
    void modify_travel_log_data() {
        TravelLog travelLog = new TravelLog();

        travelLog.setId(5L);
        travelLog.setUserId(2L);
        travelLog.setLogdate(new Date());
        travelLog.setVehicleRegNumber("ESTA0016");
        travelLog.setVehicleOdometerStart(322110L);
        travelLog.setVehicleOdometerEnd(399102L);
        travelLog.setTravelRoute("Parnu to Kuressaare and back to Parnu");
        travelLog.setTravelShortDescription("Travelling Estonia");

        HttpRequest<TravelLog> requestToDeleteTravelLog = HttpRequest.PUT("/api/edit-travel-log", travelLog);
        httpClient.toBlocking().exchange(requestToDeleteTravelLog);

        UserLogSearchCriteria requestedSearchCriteria = new UserLogSearchCriteria();
        requestedSearchCriteria.setVehicleOwnerName("alex");

        HttpRequest<UserLogSearchCriteria> request = HttpRequest.POST("/api/travel-log-search", requestedSearchCriteria);
        HttpResponse<List<User>> response = httpClient.toBlocking().exchange(request, Argument.listOf(User.class));

        boolean recordFound = response.body().stream()
                .flatMap(getUser -> getUser
                        .getLogs().stream()
                        .filter(getTravelLog -> getTravelLog.getTravelRoute().equals("Parnu to Kuressaare and back to Parnu")))
                .findFirst().isPresent();

        assertTrue(recordFound);
    }

    @Test
    void add_new_travel_log() {
        TravelLog travelLog = new TravelLog();

        travelLog.setUserId(1L);
        travelLog.setLogdate(new Date());
        travelLog.setVehicleRegNumber("EST00016");
        travelLog.setVehicleOdometerStart(722110L);
        travelLog.setVehicleOdometerEnd(802211L);
        travelLog.setTravelRoute("Kuressaare to Tallinn");
        travelLog.setTravelShortDescription("Tour");

        HttpRequest<TravelLog> requestToDeleteTravelLog = HttpRequest.POST("/api/add-travel-log", travelLog);
        httpClient.toBlocking().exchange(requestToDeleteTravelLog);

        UserLogSearchCriteria requestedSearchCriteria = new UserLogSearchCriteria();
        requestedSearchCriteria.setVehicleOwnerName("soum");

        HttpRequest<UserLogSearchCriteria> request = HttpRequest.POST("/api/travel-log-search", requestedSearchCriteria);
        HttpResponse<List<User>> response = httpClient.toBlocking().exchange(request, Argument.listOf(User.class));

        boolean newTravelLogCreated = response.body().stream()
                .flatMap(getUser -> getUser.getLogs()
                        .stream()
                        .filter(getLog -> getLog.getVehicleRegNumber().equals("EST00016")))
                .findFirst().isPresent();

        assertTrue(newTravelLogCreated);
    }
}