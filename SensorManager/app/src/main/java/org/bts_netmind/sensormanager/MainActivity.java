package org.bts_netmind.sensormanager;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SensorEventListener
{
    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";

    private SensorManager mSensorManager;
    private Sensor mLight, mAccelerometer;
    private long lastSensorUpdate;
    private RelativeLayout mMainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        this.mMainLayout = (RelativeLayout) this.findViewById(R.id.relLayoutMain);
        // Getting a 'SensorManager' instance
        this.mSensorManager = (SensorManager) this.getSystemService(Context.SENSOR_SERVICE);
        // Acquiring an available sensor list
        List<Sensor> myDeviceSensors = this.mSensorManager.getSensorList(Sensor.TYPE_ALL);
        // Showing the sensor list on a log message
        String mSensorString = "";
        for (Sensor aSensor : myDeviceSensors)
            mSensorString += aSensor.getName() + "\n";

        Toast.makeText(this, mSensorString, Toast.LENGTH_LONG).show();
        Log.i(MainActivity.TAG_MAIN_ACTIVITY, mSensorString);

        this.mLight = this.mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        this.mAccelerometer = this.mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Getting the current timestamp to be able to perform a first comparison in the accelerometer event
        this.lastSensorUpdate = System.currentTimeMillis();
    }

    // As a best practice any sensor should always be disabled if not needed, especially when the 'Activity' is paused
    @Override
    protected void onResume()
    {
        super.onResume();

        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Registering sensor listeners...");

        this.mSensorManager.registerListener(this, this.mLight, SensorManager.SENSOR_DELAY_NORMAL);
        this.mSensorManager.registerListener(this, this.mAccelerometer, SensorManager.SENSOR_DELAY_UI);
    }

    // The system will not disable sensors automatically when the screen turns off
    @Override
    protected void onPause()
    {
        super.onPause();

        Log.i(MainActivity.TAG_MAIN_ACTIVITY, "UN-Registering sensor listeners...");

        this.mSensorManager.unregisterListener(this, this.mLight);
        this.mSensorManager.unregisterListener(this, this.mAccelerometer);
    }

    // Called when sensor values have changed
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        // Getting the current sensor event timestamp
        long eventTime = event.timestamp;

        // If the event relates to the 'TYPE_LIGHT' one, prompts values on the log prompt (Android Monitor)
        if (event.sensor.getType() == Sensor.TYPE_LIGHT)
        {
            float[] lightSensorValues = event.values;
            for (float aValue : lightSensorValues)
                Log.i(MainActivity.TAG_MAIN_ACTIVITY, String.valueOf(aValue));
        }
        // If the event relates to the 'TYPE_ACCELEROMETER' one, tries to swap the screen color
        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            float[] accSensorValues = event.values;   // Getting values for all three axis

            float x = accSensorValues[0]; float y = accSensorValues[1]; float z = accSensorValues[2];
            // Getting the acceleration module power 2 over G
            float accTimesG = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH);
            // If the shake is big enough and the event is old enough (at least 1 second), the screen color is swapped and a toast is prompted
            if (accTimesG > 5.0 && (eventTime - this.lastSensorUpdate) > 1000000000)
            {
                this.lastSensorUpdate =  eventTime;

                Log.i(MainActivity.TAG_MAIN_ACTIVITY, "Device was shuffled");
                Toast.makeText(this, "Device was shuffled", Toast.LENGTH_SHORT).show();

                if (this.mMainLayout.getBackground() instanceof ColorDrawable)
                {
                    if (((ColorDrawable) this.mMainLayout.getBackground()).getColor() == ContextCompat.getColor(this, R.color.lightBlue))
                        this.mMainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lightRed));
                    else
                        this.mMainLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.lightBlue));
                }
            }
        }
    }

    // Called when the accuracy of the registered sensor has changed.
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
}
