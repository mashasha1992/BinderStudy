package testservernoaidl.simulateAIDL;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * Created by mashasha on 2018/9/25.
 */

public abstract class AMusicStub extends Binder implements IMusicInterface {
  // 唯一binder标示,可以看到就是 IMusicInterface 的全路径名;
  private static final String DESCRIPTOR = "com.ss.testservernoaidl.simulateAIDL.IMusicInterface";

  public AMusicStub() {
    this.attachInterface(this, DESCRIPTOR);
  }

  public static IMusicInterface asInterface(IBinder obj) {
    if ((obj == null)) {
      return null;
    }
    android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
    if (((iin != null) && (iin instanceof IMusicInterface))) {
      return ((IMusicInterface) iin);
    }
    return new AMusicStub.Proxy(obj);
  }

  @Override
  public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws
      RemoteException {
    switch (code) {
      case INTERFACE_TRANSACTION: {
        reply.writeString(DESCRIPTOR);
        return true;
      }
      case TRANSACTION_play: {
        data.enforceInterface(DESCRIPTOR);
        this.play();
        reply.writeNoException();
        return true;
      }
      case TRANSACTION_pause: {
        data.enforceInterface(DESCRIPTOR);
        this.pause();
        reply.writeNoException();
        return true;
      }
    }
    return super.onTransact(code, data, reply, flags);
  }

  private static class Proxy implements IMusicInterface {
    private IBinder mRemote;

    Proxy(IBinder remote) {
      mRemote = remote;
    }

    @Override
    public IBinder asBinder() {
      return mRemote;
    }

    public String getInterfaceDescriptor() {
      return DESCRIPTOR;
    }

    @Override
    public void play() {
      android.os.Parcel _data = android.os.Parcel.obtain();
      android.os.Parcel _reply = android.os.Parcel.obtain();
      try {
        _data.writeInterfaceToken(DESCRIPTOR);
        mRemote.transact(AMusicStub.TRANSACTION_play, _data, _reply, 0);
        _reply.readException();
      } catch (RemoteException e) {
        e.printStackTrace();
      } finally {
        _reply.recycle();
        _data.recycle();
      }
    }

    @Override
    public void pause() {
      android.os.Parcel _data = android.os.Parcel.obtain();
      android.os.Parcel _reply = android.os.Parcel.obtain();
      try {
        _data.writeInterfaceToken(DESCRIPTOR);
        mRemote.transact(AMusicStub.TRANSACTION_pause, _data, _reply, 0);
        _reply.readException();
      } catch (RemoteException e) {
        e.printStackTrace();
      } finally {
        _reply.recycle();
        _data.recycle();
      }
    }
  }

  static final int TRANSACTION_play = (IBinder.FIRST_CALL_TRANSACTION + 1);
  static final int TRANSACTION_pause = (IBinder.FIRST_CALL_TRANSACTION + 2);

  @Override
  public IBinder asBinder() {
    return this;
  }

}
