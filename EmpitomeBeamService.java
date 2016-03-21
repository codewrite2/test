package com.github.suzukaze.rxandroidsample.api;

import com.github.suzukaze.rxandroidsample.model.EpitomeBeam;

import java.util.Observable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmpitomeBeamService {

    public static final String ENDPOINT = "https://ja.epitomeup.com";

    @GET("feed/beam")
    rx.Observable<EpitomeBeam> getBeam();
}
