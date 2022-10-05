package com.example.mybatistest.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Weather {
    private double pop;//강수확율
    private double pcp;//1시간 강수량
    private double reh;//습도
    private double tmn;//일 최저기온
    private double tmx;//일 최고기온

}
