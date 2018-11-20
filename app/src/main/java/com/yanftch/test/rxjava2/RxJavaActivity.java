package com.yanftch.test.rxjava2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.yanftch.test.R;


/**
 * Author : yanftch
 * Date   : 2017/7/7
 * Time   : 14:23
 * Desc   : RxJava的操作符学习
 */

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "dah_RxJavaActivity";
    private StringBuilder mRxOperatorsText = new StringBuilder();
    private AutoSplitTextView mAutoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        Log.e(TAG, "onCreate: ");
        mAutoTextView = (AutoSplitTextView) findViewById(R.id.tttttttt);
//        mAutoTextView.setText("你弄弄经发局佛爱迪生你懂你今年OS就能发IO积分纳斯就爱风景奥尼就覅哦啊见覅偶阿囧 见覅哦啊见佛耳温计");
    }

    public void btnClickLauncher(View view) {
//        Observable.create(new ObservableOnSubscribe<Integer>() {
//            @Override
//            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
//                mRxOperatorsText.append("Observable emit 1" + "\n");
//                Log.e(TAG, "Observable emit 1" + "\n");
//                e.onNext(1);
//                mRxOperatorsText.append("Observable emit 2" + "\n");
//                Log.e(TAG, "Observable emit 2" + "\n");
//                e.onNext(2);
//                mRxOperatorsText.append("Observable emit 3" + "\n");
//                Log.e(TAG, "Observable emit 3" + "\n");
//                e.onNext(3);
//                e.onComplete();
//                mRxOperatorsText.append("Observable emit 4" + "\n");
//                Log.e(TAG, "Observable emit 4" + "\n");
//                e.onNext(4);
//            }
//        }).subscribe(new Observer<Integer>() {
//            private int i;
//            private Disposable mDisposable;
//
//            @Override
//            public void onSubscribe(@NonNull Disposable d) {
//                mRxOperatorsText.append("onSubscribe : " + d.isDisposed() + "\n");
//                Log.e(TAG, "onSubscribe : " + d.isDisposed() + "\n");
//                mDisposable = d;
//            }
//
//            @Override
//            public void onNext(@NonNull Integer integer) {
//                mRxOperatorsText.append("onNext : value : " + integer + "\n");
//                Log.e(TAG, "onNext : value : " + integer + "\n");
//                i++;
//                if (i == 2) {
//                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
//                    mDisposable.dispose();
//                    mRxOperatorsText.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
//                    Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
//                }
//            }
//
//            @Override
//            public void onError(@NonNull Throwable e) {
//                mRxOperatorsText.append("onError : value : " + e.getMessage() + "\n");
//                Log.e(TAG, "onError : value : " + e.getMessage() + "\n");
//            }
//
//            @Override
//            public void onComplete() {
//                mRxOperatorsText.append("onComplete" + "\n");
//                Log.e(TAG, "onComplete" + "\n");
//            }
//        });
    }
}
