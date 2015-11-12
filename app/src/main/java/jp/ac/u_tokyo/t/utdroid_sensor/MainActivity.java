package jp.ac.u_tokyo.t.utdroid_sensor;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.List;

/**
 * センサイベントのリスナを実装することを宣言している。具体的には、
 *      onSensorChanged(SensorEvent event)
 *      onAccuracyChanged(Sensor sensor, int accuracy)
 * の2つのメソッド。
 */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    /* Viewを格納する変数 */
    private TextView accelerometerX;
    private TextView accelerometerY;
    private TextView accelerometerZ;
    private TextView azimuth;
    private TextView pitch;
    private TextView roll;
    private TextView proximity;
    private TextView light;
    private TextView temperature;

    /* センサを制御するための変数 */
    private SensorManager sensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* それぞれの名前に対応するViewを取得する */
        accelerometerX = (TextView)findViewById(R.id.accelerometerX);
        accelerometerY = (TextView)findViewById(R.id.accelerometerY);
        accelerometerZ = (TextView)findViewById(R.id.accelerometerZ);
        azimuth = (TextView)findViewById(R.id.azimuth);
        pitch = (TextView)findViewById(R.id.pitch);
        roll = (TextView)findViewById(R.id.roll);
        proximity = (TextView)findViewById(R.id.proximity);
        light = (TextView)findViewById(R.id.light);
        temperature = (TextView)findViewById(R.id.temperature);

        /* センサマネージャの初期化 */
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        /* 加速度センサの取得 */
        List<Sensor> sensorList = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensorList.size() > 0) {
            /* あれば1番目を取得 */
            Sensor sensor = sensorList.get(0);
            /* 加速度センサの利用登録。第3引数は要求する更新頻度 */
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }

        /* 方位・ピッチ角・ロール角センサの取得 */
        sensorList = sensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
        if (sensorList.size() > 0) {
            /* あれば1番目を取得 */
            Sensor sensor = sensorList.get(0);
            /* 方位・ピッチ角・ロール角センサの利用登録 */
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }

        /* 近接センサの取得 */
        sensorList = sensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
        if (sensorList.size() > 0) {
            /* あれば1番目を取得 */
            Sensor sensor = sensorList.get(0);
            /* 近接センサの利用登録 */
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }

        /* 照度センサの取得 */
        sensorList = sensorManager.getSensorList(Sensor.TYPE_LIGHT);
        if (sensorList.size() > 0) {
            /* あれば1番目を取得 */
            Sensor sensor = sensorList.get(0);
            /* 照度センサの利用登録 */
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }

        /* 温度センサの取得 */
        sensorList = sensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);
        if (sensorList.size() > 0) {
            /* あれば1番目を取得 */
            Sensor sensor = sensorList.get(0);
            /* 温度センサの利用登録 */
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_UI);
        }
    }

    /**
     * アプリが終了して、画面が破棄される時に呼ばれるメソッド。
     * 必ず呼ばれるとは限らないので注意が必要（必要に応じて、onPause, onStop, onDestroyを使い分ける）。
     */
    @Override
    public void onDestroy() {
        /* センサの利用登録を解除する */
        sensorManager.unregisterListener(this);

        /* 継承元の処理を実行させる */
        super.onDestroy();
    }

    /**
     * 利用登録したセンサの内、最低1つのセンサ値が変化した時に呼ばれる
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        /* センサの種類で条件分岐 */
        switch(event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                /* 画面に表示 */
                accelerometerX.setText( getString(R.string.accelerometer_x) + event.values[0] );
                accelerometerY.setText( getString(R.string.accelerometer_y) + event.values[1] );
                accelerometerZ.setText( getString(R.string.accelerometer_z) + event.values[2] );
                break;
            case Sensor.TYPE_ORIENTATION:
                /* 画面に表示 */
                azimuth.setText( getString(R.string.azimuth) + event.values[0] );
                pitch.setText( getString(R.string.pitch) + event.values[1] );
                roll.setText( getString(R.string.roll) + event.values[2] );
                break;
            case Sensor.TYPE_PROXIMITY:
                /* 画面に表示 */
                proximity.setText( getString(R.string.proximity) + event.values[0] );
                break;
            case Sensor.TYPE_LIGHT:
                /* 画面に表示 */
                light.setText( getString(R.string.light) + event.values[0] );
                break;
            case Sensor.TYPE_TEMPERATURE:
                /* 画面に表示 */
                temperature.setText( getString(R.string.temperature) + event.values[0] );
                break;
        }
    }

    /**
     * 利用登録したセンサの内、最低1つの精度が変化した時に呼ばれる
     */
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
