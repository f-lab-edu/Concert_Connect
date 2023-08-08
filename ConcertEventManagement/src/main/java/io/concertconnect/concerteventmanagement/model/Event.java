package io.concertconnect.concerteventmanagement.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Getter
@Setter
@ApiModel(description = "이벤트 정보를 위한 도메인 객체") //클래스 정보를 띄울 때
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Integer id;                    // 이벤트의 id 고유값
    private String name;                // 이벤트 이름
    private String venue;               // 이벤트 장소
    @Column(name = "ticketstart_Time")
    private LocalDate ticketstartTime;       // 예약 시작 날짜
    @Column(name = "ticketend_Time")
    private LocalDate ticketendTime;         // 예약 끝나는 날짜
    @Column(name = "start_Time")
    private LocalDateTime startTime;             // 시작 날짜
    @Column(name = "end_Time")
    private LocalDateTime endTime;               // 끝나는 날짜

    @Column(columnDefinition = "int default 1000", nullable = false)
    private Integer capacity; // 허용 인원

    //낙관적락은 전에 버젼이랑 비교해서 수정 됐는지 확인하고 수정되지 않았다면 수정함
    @Version
    @JsonIgnore
    @Column(columnDefinition = "Long default 0", nullable = false)
    private Long version; // 비교하기위한 변수


    //@JsonIgnore //값을 보고 싶지 않을때 변수에 어노테이션으로 지정하는 방법


}
