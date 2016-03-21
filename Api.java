package com.github.suzukaze.rxandroidsample;

import com.github.suzukaze.rxandroidsample.model.EpitomeBeam;
import com.github.suzukaze.rxandroidsample.model.EpitomeEntry;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by John on 2016/03/21.
 */
public class Api {
    public static <S> Observable<String> request(Observable<S> ob) {

        return ob.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorReturn()
                .subscribe(current -> {
                    for (EpitomeEntry epitomeEntry : current.sources) {
                        t.add(epitomeEntry.title);
                    }
                });

    }
}
