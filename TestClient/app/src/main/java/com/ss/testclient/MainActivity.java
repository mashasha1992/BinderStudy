package com.ss.testclient;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.ss.testserver.IMusicInterface;

public class MainActivity extends Activity {

  private IMusicInterface musicInterface;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    //bind Service
    Intent serviceIntent = new Intent();
    serviceIntent.setAction("com.ss.testserver.MusicService");
    serviceIntent.setPackage("com.ss.testserver");
    bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
  }

  ServiceConnection mConnection = new ServiceConnection() {
    public void onServiceConnected(ComponentName name, IBinder binder) {
      musicInterface = IMusicInterface.Stub.asInterface(binder);

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
