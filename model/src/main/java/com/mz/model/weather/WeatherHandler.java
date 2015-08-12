package com.mz.model.weather;

import com.mz.model.basic.BaseLogicHandler;
import com.mz.model.listener.IListener;
import com.mz.model.request.RequestListener;
import com.mz.model.request.RequestTaskManager;

/**
 * Created by Jamin on 8/5/15.
 */
public class WeatherHandler extends BaseLogicHandler {

    public WeatherHandler() {
        super(true);

    }


    public void requestCurrentWeather(final IListener<WeatherInfo> listener) {

        this.getHandler().post(new Runnable() {
            @Override
            public void run() {
                WeatherCurrentRequest request = new WeatherCurrentRequest(new RequestListener<WeatherCurrentRequest>() {
                    @Override
                    public boolean needRetry(int retryNo) {
                        return super.needRetry(retryNo);
                    }

                    @Override
                    public void onCancel(WeatherCurrentRequest taskRequest) {
                        super.onCancel(taskRequest);
                    }

                    @Override
                    public void onSuccess(WeatherCurrentRequest taskRequest) {
                        super.onSuccess(taskRequest);
                        listener.sendFinishMessage(null);
                    }

                    @Override
                    public void onFailure(WeatherCurrentRequest taskRequest) {
                        super.onFailure(taskRequest);
                        listener.sendFailureMessage(taskRequest.getError());
                    }
                });

                RequestTaskManager.sharedInstance().sendRequest(request);
            }
        });
    }
}
