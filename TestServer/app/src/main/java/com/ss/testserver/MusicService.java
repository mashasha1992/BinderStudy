package com.ss.testserver;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by mashasha on 2018/9/24.
 */

public class MusicService extends Service {
  private AssetManager mAssetManager;
  private MediaPlayer mPlayer;

  @Override
  public void onCreate() {
    super.onCreate();

    mAssetManager = getAssets();
    mPlayer = new MediaPlayer();

  }

  @Override
  public IBinder onBind(Intent intent) {
    Log.e("mss", "mss onBind......");
    return new MusicBinder();
  }

  private class MusicBinder extends IMusicInterface.Stub {


    @Override
    public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws
        RemoteException {

    }

    @Override
    public void play() throws RemoteException {
      try {
        Log.e("mss", "MusicService.play......");
        AssetFileDescriptor afd = mAssetManager.openFd("test.mp3");
        mPlayer.reset();
        mPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
        mPlayer.prepare();
        mPlayer.start();
      } catch (IOException e) {
        e.printStackTrace();
      }

    }

    @Override
    public void pause() throws RemoteException {
      Log.e("mss", "MusicService.pause......");
      mPlayer.pause();
    }
  }
}
