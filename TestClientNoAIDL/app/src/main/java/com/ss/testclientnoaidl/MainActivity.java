package com.ss.testclientnoaidl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import testservernoaidl.simulateAIDL.AMusicStub;
import testservernoaidl.simulateAIDL.IMusicInterface;


public class MainActivity extends AppCompatActivity {

  private IMusicInterface musicInterface;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //bind Service
    Intent serviceIntent = new Intent();
    serviceIntent.setAction("com.ss.testservernoaidl.MusicService");
    serviceIntent.setPackage("com.ss.testservernoaidl");
    bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
  }

  ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName name, IBinder binder) {
      musicInterface = AMusicStub.asInterface(binder);

    }

    public void onServiceDisconnected(ComponentName name) {

    }
  };

  public void play(View view) throws RemoteException {
    musicInterface.play();

  }

  public void pause(View view) throws RemoteException {
    musicInterface.pause();

  }
}
