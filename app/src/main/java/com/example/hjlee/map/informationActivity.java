package com.example.hjlee.map;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import lecho.lib.hellocharts.formatter.SimpleAxisValueFormatter;
import lecho.lib.hellocharts.listener.ComboLineColumnChartOnValueSelectListener;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.ComboLineColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.ComboLineColumnChartView;
import lecho.lib.hellocharts.view.LineChartView;


public class informationActivity extends FragmentActivity {


            @Override
            public void onCreate(Bundle savedInstanceState) {

                super.onCreate(savedInstanceState);
                setContentView(R.layout.information);


                if (savedInstanceState == null) {
                    getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
                }

                Intent intent = getIntent();
                String text = intent.getStringExtra("text");
        Log.d("d", text);

    }//oncreate


    public static class PlaceholderFragment extends Fragment {

       // public ArrayList degreeArray = new ArrayList();
       // public ArrayList azimuthArray = new ArrayList();

        ArrayList<Float> shadow_degreeArray = new ArrayList<Float>();
        ArrayList<Float> shadow_azimuthArray = new ArrayList<Float>();
        ArrayList<Float> sunpath_degreeArray = new ArrayList<Float>();
        ArrayList<Float> sunpath_azimuthArray = new ArrayList<Float>();

        private LineChartView chart;
        private LineChartData data;

///////////////////////////////////////////////////////////////////////////////////////////////////////

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


            String serverURL = "http://1-dot-servertest-1019.appspot.com/apis/getdata/placename=ga";
            new HttpTask().execute(serverURL);

            View rootView = inflater.inflate(R.layout.fragment_tempo_chart, container, false);
            chart = (LineChartView) rootView.findViewById(R.id.chart);

            return rootView;
        }

        class HttpTask extends AsyncTask<String, Void, String> {
            String result = "";
            InputStream inputStream = null;

            protected void onPreExecute() {
                //display progress dialog.
                // NOTE: You can call UI Element here.
            }

            // Call after onPreExecute method
            protected String doInBackground(String... urls) {
                HttpResponse response = null;

                try {
                    HttpClient client = new DefaultHttpClient();
                    HttpGet request = new HttpGet();
                    //"http://1-dot-servertest-1019.appspot.com/apis/sample/hello/name=insert"
                    request.setURI(new URI(urls[0]));
                    response = client.execute(request);
                    inputStream = response.getEntity().getContent();
                    result = convertStreamToString(inputStream);

                } catch (URISyntaxException e) {
                    e.printStackTrace();
                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return result;
            }


            protected void onPostExecute(String result) {
                super.onPostExecute(result);


                JSONObject object = null;
                try {
                    object = new JSONObject(result);
                    Log.i("result", object.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JSONArray Array_shadow = null;
                JSONArray Array_sunpath = null;
                try {
                    Array_shadow = new JSONArray(object.getString("shadow"));
                    Array_sunpath = new JSONArray(object.getString("sunpath"));
                    //Sting a= new sting(Array.length());
                    //Log.i("result2", object.getString("data"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
///////////////shadow data////////////////////////////////////////////////////////
                for (int i = 0; i < Array_shadow.length(); i++) {
                    //Log.i("test", String.valueOf(i));

                    JSONObject insideObject = null;
                    try {
                        insideObject = Array_shadow.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String degree = null;
                    String azimuth = null;
                    try {
                        degree = insideObject.getString("degree");
                        shadow_degreeArray.add(Float.parseFloat(degree));
                        Log.e("test", String.valueOf(shadow_degreeArray.get(i)));
                        azimuth = insideObject.getString("azimuth");
                        shadow_azimuthArray.add(Float.parseFloat(azimuth));
                        Log.e("test2", String.valueOf(shadow_azimuthArray.get(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
///////////////////////////////sunpath data//////////////////////////////////////////////
                for (int i = 0; i <  Array_sunpath.length(); i++) {
                    //Log.i("test", String.valueOf(i));

                    JSONObject insideObject_ = null;
                    try {
                        insideObject_ = Array_sunpath.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    String degree_ = null;
                    String azimuth_ = null;
                    try {
                        degree_ = insideObject_.getString("degree");
                        sunpath_degreeArray.add(Float.parseFloat(degree_));
                        Log.e("Sunpath test1", String.valueOf(sunpath_degreeArray.get(i)));

                        azimuth_ = insideObject_.getString("azimuth");
                        sunpath_azimuthArray.add(Float.parseFloat(azimuth_));
                        Log.e("Sunpath test2", String.valueOf(sunpath_azimuthArray.get(i)));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                generateTempoData();
            }

            public String convertStreamToString(InputStream inputStream) throws IOException {
                if (inputStream != null) {
                    Writer writer = new StringWriter();

                    char[] buffer = new char[1024];
                    try {
                        Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"), 1024);
                        int n;
                        while ((n = reader.read(buffer)) != -1) {
                            writer.write(buffer, 0, n);
                        }
                    } finally {
                        inputStream.close();
                    }
                    return writer.toString();
                } else {
                    return "";
                }
            }
        }
//////////////////////////////////////////////////////////////////////////
        private void generateTempoData() {


            float minHeight = 0;
            float maxHeight = 90;
            float tempoRange = 90; // from 0min/km to 15min/km

            float scale = tempoRange / maxHeight;//   0.05
            float sub = (minHeight * scale) / 2;//    10/2

            int numValues = 24;

            Line line;
           // List<PointValue> values;
            List<Line> lines = new ArrayList<Line>();

            List<PointValue> values = new ArrayList<PointValue>();
            //Log.d("testgggg", String.valueOf(azimuthArray.get(1)));
            for(int i=0; i<shadow_azimuthArray.size(); ++i) {
               // Log.d("test", String.valueOf(degreeArray.get(i)));
               // Log.d("test2", String.valueOf(azimuthArray.get(i)));
               // Log.d("d", String.valueOf(azimuthArray.get(i)));
                values.add(new PointValue(shadow_azimuthArray.get(i), shadow_degreeArray.get(i)));
            }

            line = new Line(values);
            line.setColor(Color.GRAY);
            line.setHasPoints(false);
            line.setFilled(true);
            line.setStrokeWidth(1);
            lines.add(line);



            // Tempo line is a little tricky because worse tempo means bigger value for example 11min per km is worse
            // than 2min per km but the second should be higher on the chart. So you need to know max tempo and
            // tempoRange and set
            // chart values to minTempo - realTempo.

           /* values = new ArrayList<PointValue>();
            for (int i = 0; i < numValues; ++i) {
                // Some random raw tempo values.
                float realTempo = (float) 76;
                float revertedTempo = tempoRange - realTempo;
                values.add(new PointValue(i, revertedTempo));
            }*/

            List<PointValue> values_ = new ArrayList<PointValue>();
            //Log.d("testgggg", String.valueOf(azimuthArray.get(1)));
            for(int i=0; i<sunpath_azimuthArray.size(); ++i) {
                //Log.d("test", String.valueOf(degreeArray_sunpath.get(i)));
               // Log.d("test2", String.valueOf(azimuthArray_sunpath.get(i)));
               // Log.d("d", String.valueOf(azimuthArray_sunpath.get(i)));
                values_.add(new PointValue(sunpath_azimuthArray.get(i), sunpath_degreeArray.get(i)));
            }



            line = new Line(values_);
            line.setColor(ChartUtils.COLOR_RED);
            line.setHasPoints(false);
            line.setStrokeWidth(1);
            lines.add(line);


            //////////////////////////////////////////////////////////////////
            // Data and axes
            data = new LineChartData(lines);

            // Distance axis(bottom X) with formatter that will ad [km] to values, remember to modify max label charts
            // value.
            Axis distanceAxis = new Axis();
            distanceAxis.setName("Azimuth");
            distanceAxis.setTextColor(Color.BLACK);
            distanceAxis.setMaxLabelChars(3);
            distanceAxis.setFormatter(new SimpleAxisValueFormatter().setAppendedText(".".toCharArray()));
            distanceAxis.setHasLines(true);
            distanceAxis.setHasTiltedLabels(true);
            data.setAxisXBottom(distanceAxis);

            // Tempo uses minutes so I can't use auto-generated axis because auto-generation works only for decimal
            // system. So generate custom axis values for example every 15 seconds and set custom labels in format
            // minutes:seconds(00:00), you could do it in formatter but here will be faster.
           /* List<AxisValue> axisValues = new ArrayList<AxisValue>();
            for (float i = 0; i < tempoRange; i += 0.25f) {
                // I'am translating float to minutes because I don't have data in minutes, if You store some time data
                // you may skip translation.
                axisValues.add(new AxisValue(i).setLabel(formatMinutes(tempoRange - i)));
            }

            Axis tempoAxis = new Axis(axisValues).setName("height").setHasLines(true).setMaxLabelChars(4)
                    .setTextColor(ChartUtils.COLOR_RED);*/
            data.setAxisYLeft(new Axis().setName("Elevation").setMaxLabelChars(4).setTextColor(Color.BLACK)
                    .setFormatter(new HeightValueFormatter(scale, sub, 0)));


            // *** Same as in Speed/Height chart.
            // Height axis, this axis need custom formatter that will translate values back to real height values.
            //  data.setAxisYRight(new Axis().setName("Height [m]").setMaxLabelChars(3)
            //   .setFormatter(new HeightValueFormatter(scale, sub, 0)));

            // Set data
            chart.setLineChartData(data);

            // Important: adjust viewport, you could skip this step but in this case it will looks better with custom
            // viewport. Set
            // viewport with Y range 0-12;
            Viewport v = chart.getMaximumViewport();
            v.set(v.left, tempoRange, v.right, 0);
            chart.setMaximumViewport(v);
            chart.setCurrentViewport(v);

        }

        private String formatMinutes(float value) {
            StringBuilder sb = new StringBuilder();

            // translate value to seconds, for example
            int valueInSeconds = (int) (value * 60);
            int minutes = (int) Math.floor(valueInSeconds / 60);
            int seconds = (int) valueInSeconds % 60;

            sb.append(String.valueOf(minutes)).append(':');
            if (seconds < 10) {
                sb.append('0');
            }
            sb.append(String.valueOf(seconds));
            return sb.toString();
        }

        /**
         * Recalculated height values to display on axis. For this example I use auto-generated height axis so I
         * override only formatAutoValue method.
         */
        private static class HeightValueFormatter extends SimpleAxisValueFormatter {

            private float scale;
            private float sub;
            private int decimalDigits;

            public HeightValueFormatter(float scale, float sub, int decimalDigits) {
                this.scale = scale;
                this.sub = sub;
                this.decimalDigits = decimalDigits;
            }

            @Override
            public int formatValueForAutoGeneratedAxis(char[] formattedValue, float value, int autoDecimalDigits) {
                float scaledValue = (value + sub) / scale;
                return super.formatValueForAutoGeneratedAxis(formattedValue, scaledValue, this.decimalDigits);
            }
        }

    }
}


