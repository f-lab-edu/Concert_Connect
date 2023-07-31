package io.concertconnect.concerteventmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor // 생성자
@NoArgsConstructor
@Entity
@ApiModel(description = "이벤트 정보를 위한 도메인 객체") //클래스 정보를 띄울 때
public class Event {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Integer id;                    // 이벤트의 id 고유값

    private String name;                // 이벤트 이름
    private String venue;               // 이벤트 장소
    private LocalDate ticketstartTime;       // 예약 시작 날짜
    private LocalDate ticketendTime;         // 예약 끝나는 날짜
    private LocalDateTime startTime;             // 시작 날짜
    private LocalDateTime endTime;               // 끝나는 날짜

    //@JsonIgnore //값을 보고 싶지 않을때 변수에 어노테이션으로 지정하는 방법


}
