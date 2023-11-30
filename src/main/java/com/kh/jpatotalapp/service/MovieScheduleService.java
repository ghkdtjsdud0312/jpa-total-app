package com.kh.jpatotalapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kh.jpatotalapp.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MovieScheduleService {
    private final RestTemplate restTemplate;
    private final MovieService movieService;
    private final ObjectMapper objectMapper; // Jackson ObjectMapper

    @Scheduled(cron = "0 0 0/1 * * *") // 매 시간마다 실행
//    @Scheduled(cron = "0 * * * * *") // 매분마다 실행
    public void movieSchedule() throws JsonProcessingException {
        movieService.deleteAll();
        String URL = "http://127.0.0.1:5000/api/movie";
        ResponseEntity<String> response = restTemplate.getForEntity(URL, String.class);
        String movieDataJson = response.getBody();
        System.out.println(movieDataJson);
        List<Map<String, String>> movieList = objectMapper.readValue(movieDataJson, new TypeReference<List<Map<String, String>>>() {});
        for (Map<String, String> data : movieList) {
            Movie movie = new Movie();
            // 영화 정보 설정
            movie.setMovieRank(data.get("rank"));
            movie.setImage(data.get("image"));
            movie.setTitle(data.get("title"));
            movie.setScore(data.get("score"));
            movie.setRate(data.get("eval_num"));
            movie.setReservation(data.get("reservation"));
            movie.setDate(data.get("open_date"));

            // 영화 정보 저장
            movieService.saveMovie(movie);
        }
    }
}