package com.mz.cad;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mz.model.cad.listener.UIBasicListener;
import com.mz.model.weather.WeatherInfo;
import com.mz.model.weather.WeatherManager;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WeatherFragment extends Fragment {


    TextView mCityText;
    TextView mUpdatedText;
    TextView mDetailsText;
    TextView mCurrentTemperatureText;
    ImageView mWeatherIcon;

    private static final HashMap<String, Integer> sIconMap;
    static {
        sIconMap = new HashMap<>();
        sIconMap.put("01d", R.mipmap.weather_clear);
        sIconMap.put("02d", R.mipmap.weather_few);
        sIconMap.put("03d", R.mipmap.weather_few);
        sIconMap.put("04d", R.mipmap.weather_broken);
        sIconMap.put("09d", R.mipmap.weather_shower);
        sIconMap.put("10d", R.mipmap.weather_rain);
        sIconMap.put("11d", R.mipmap.weather_tstorm);
        sIconMap.put("13d", R.mipmap.weather_snow);
        sIconMap.put("50d", R.mipmap.weather_mist);
        sIconMap.put("01n", R.mipmap.weather_moon);
        sIconMap.put("02n", R.mipmap.weather_few_night);
        sIconMap.put("03n", R.mipmap.weather_few_night);
        sIconMap.put("04n", R.mipmap.weather_broken);
        sIconMap.put("09n", R.mipmap.weather_shower);
        sIconMap.put("10n", R.mipmap.weather_rain_night);
        sIconMap.put("11n", R.mipmap.weather_tstorm);
        sIconMap.put("13n", R.mipmap.weather_snow);
        sIconMap.put("50n", R.mipmap.weather_mist);
    }

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WeatherFragment newInstance(String param1, String param2) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public WeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

//        LayoutInflater inflater = this.getActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        mCityText = (TextView)rootView.findViewById(R.id.city_text);
        mUpdatedText = (TextView)rootView.findViewById(R.id.updated_text);
        mDetailsText = (TextView)rootView.findViewById(R.id.details_text);
        mCurrentTemperatureText = (TextView)rootView.findViewById(R.id.current_temperature_text);
        mWeatherIcon = (ImageView)rootView.findViewById(R.id.weather_icon);
        mDetailsText.setText("你好！");

//        return inflater.inflate(R.layout.fragment_weather, null, false);
        return rootView;//inflater.inflate(R.layout.fragment_weather, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;


            WeatherManager.getInstance().fetchCurrentWeather("1795563", new UIBasicListener<WeatherInfo>() {
                @Override
                public void onFinish(WeatherInfo weatherInfo) {
                    updateView(weatherInfo);
                }

                @Override
                public void onFailure(Error error) {

                }

                @Override
                public void onCancel() {

                }
            });

        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }



    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    private void updateView(WeatherInfo weatherInfo) {
        mCityText.setText(weatherInfo.getCityName());
        DateFormat df = DateFormat.getDateTimeInstance();
        mUpdatedText.setText(df.format(new Date(weatherInfo.getLatestUpdateTime() * 1000)));
        mDetailsText.setText(weatherInfo.getMainDesc() + "\n" +
                String.format("湿度: %d ", weatherInfo.getHumidity()) + "%" + "\n" +
                String.format("气压 %d hPa ", weatherInfo.getPressure()) + "\n");
        mCurrentTemperatureText.setText(String.format("%.2f", weatherInfo.getTemperature()) + " ℃");
        mWeatherIcon.setImageDrawable(getResources().getDrawable(sIconMap.get(weatherInfo.getIconId())));

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }




}
