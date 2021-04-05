package com.example.bluetooth_conntroller;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    static final UUID mUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bluthoth part

        BluetoothAdapter btadapter = BluetoothAdapter.getDefaultAdapter();
//        System.out.println(btadapter.getBondedDevices());

        BluetoothDevice hc05 = btadapter.getRemoteDevice("98:D3:31:F9:4C:EF");

        // sockets connection build througth bluethooth
        BluetoothSocket socket = null;
        int counter = 0;
        do {


            try {
                socket = hc05.createRfcommSocketToServiceRecord(mUUID);
                socket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            counter +=1;
        } while (!socket.isConnected() && counter > 5);
//        System.out.println(socket);

    // buttton on the app
        Button foward, back, right, left,stop;


        // finding the button with the respedted id
        foward = findViewById(R.id.forward);
        back = findViewById(R.id.backwards);
        right = findViewById(R.id.right);
        left = findViewById(R.id.left);
        stop = findViewById(R.id.stop);

        //onclick event for forward button
        BluetoothSocket finalSocket = socket;
        foward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("move forward");
                try
                {
                    finalSocket.getOutputStream().write("f".toString().getBytes());
                }
                catch (IOException e)
                {
                    System.out.print(e);
                }
            }
        });


        //onclick event for right button
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("MOVE RIGHT");
                try
                {
                    finalSocket.getOutputStream().write("r".toString().getBytes());
                }
                catch (IOException e)
                {
                    System.out.print(e);
                }
            }
        });


        // onclick event for left button
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("move left");
                try
                {
                    finalSocket.getOutputStream().write("l".toString().getBytes());
                }
                catch (IOException e)
                {
                    System.out.print(e);
                }
            }
        });


        //onclick event for backwards button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("back");
                try
                {
                    finalSocket.getOutputStream().write("b".toString().getBytes());
                }
                catch (IOException e)
                {
                    System.out.print(e);
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.print("stop");
                try
                {
                    finalSocket.getOutputStream().write("s".toString().getBytes());
                }
                catch (IOException e)
                {
                    System.out.print(e);
                }
            }
        });

    }
}