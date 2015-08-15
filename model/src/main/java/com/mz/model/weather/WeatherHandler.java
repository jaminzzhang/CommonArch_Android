package com.mz.model.weather;

import com.mz.model.cad.basic.BaseLogicHandler;
import com.mz.model.cad.listener.IListener;
import com.mz.model.cad.request.RequestListener;
import com.mz.model.cad.request.RequestTaskManager;

/**
 * Created by Jamin on 8/5/15.
 */
public class WeatherHandler extends BaseLogicHandler {

    public WeatherHandler() {
        super(true);

    }


    public void requestCurrentWeather(final String cityId, final IListener<WeatherInfo> listener) {

        this.getHandler().post(new Runnable() {
            @Override
            public void run() {
                WeatherCurrentRequest request = new WeatherCurrentRequest(cityId, new RequestListener<WeatherCurrentRequest>() {
                    @Override
                    public boolean needRetry(int retryNo) {
                        return super.needRetry(retryNo);
                    }

                    @Override
                    public void onCancel(WeatherCurrentRequest taskRequest) {
                        super.onCancel(taskRequest);
                        if (null != listener){
                            listener.sendCancelMessage();
                        }
                    }

                    @Override
                    public void onSuccess(WeatherCurrentRequest taskRequest) {
                        super.onSuccess(taskRequest);
                        if (null != listener){
                            listener.sendFinishMessage(taskRequest.getWeatherInfo());
                        }
                    }

                    @Override
                    public void onFailure(WeatherCurrentRequest taskRequest) {
                        super.onFailure(taskRequest);
                        if (null != listener){
                            listener.sendFailureMessage(taskRequest.getError());
                        }
                    }
                });

                RequestTaskManager.sharedInstance().sendRequest(request);
            }
        });
    }
}
