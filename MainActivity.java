package com.github.suzukaze.rxandroidsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.github.suzukaze.rxandroidsample.api.EmpitomeBeamService;
import com.github.suzukaze.rxandroidsample.api.ServiceGenerator;
import com.github.suzukaze.rxandroidsample.model.EpitomeBeam;
import com.github.suzukaze.rxandroidsample.model.EpitomeEntry;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class MainActivity extends ActionBarActivity {

    private static String TAG = MainActivity.class.getSimpleName();

    private List<String> titles;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titles = new ArrayList<>();

        listView = (ListView) findViewById(R.id.listView);

        final MainActivity finalMainActivity = this;


        /*

        Observable<List<String>> observable = Observable.create(new Observable.OnSubscribe<List<String>>() {
            @Override
            public void call(Subscriber<? super List<String>> subscriber) {
                EmpitomeBeamService empitomeBeamService = ServiceGenerator.createService(
                        EmpitomeBeamService.class, EmpitomeBeamService.ENDPOINT);
                Call<EpitomeBeam> epitomeBeam = empitomeBeamService.getBeam();
                List<String> t = new ArrayList<>(epitomeBeam.sources.size());
                for (EpitomeEntry epitomeEntry : epitomeBeam.sources) {
                    t.add(epitomeEntry.title);
                }
                subscriber.onNext(t);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io());
*/
]
        // main
        //http://qiita.com/suzukaze/items/3393cdb8582fd4c98f0b
        //http://kirimin.hatenablog.com/entry/2015/02/11/221228
        //http://y-anz-m.blogspot.jp/2016_03_01_archive.html
        //http://stackoverflow.com/questions/26201420/retrofit-with-rxjava-handling-network-exceptions-globally
        //http://www.600000300.com/2016/01/06/RxJava-Retrofit2-0-%E4%BD%BF%E7%94%A8/
        EmpitomeBeamService empitomeBeamService = ServiceGenerator.createService(
                EmpitomeBeamService.class, EmpitomeBeamService.ENDPOINT);
        Observable<EpitomeBeam> epitomeBeam = empitomeBeamService.getBeam();

        Api.request(epitomeBeam)
                .subscribe(current -> {
                    for (EpitomeEntry epitomeEntry : current.sources) {
                        t.add(epitomeEntry.title);
                    }
                });


/*        ViewObservable.bindView(listView, observable);

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onCompleted() {
                        ArrayAdapter<String> adapter = new ArrayAdapter(finalMainActivity,
                                android.R.layout.simple_expandable_list_item_1, titles);
                        listView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "error", e);
                    }

                    @Override
                    public void onNext(List<String> titleArray) {
                        titles.addAll(titleArray);
                    }
                });*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
