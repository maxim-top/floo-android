/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class BMXRTCService {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;
  private static BMXRTCEngine bmxrtcEngine;

  protected BMXRTCService(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXRTCService obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_BMXRTCService(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setupRTCEngine(BMXRTCEngine engine) {
    bmxrtcEngine = engine;
    flooJNI.BMXRTCService_setupRTCEngine(swigCPtr, this, BMXRTCEngine.getCPtr(engine), engine);
  }

  public BMXRTCEngine getRTCEngine() {
    return bmxrtcEngine;
  }

  public BMXRTCSignalService getBMXRTCSignalService() {
    return new BMXRTCSignalService(flooJNI.BMXRTCService_getBMXRTCSignalService(swigCPtr, this), false);
  }

  public void sendRTCMessage(BMXMessage msg) {
    flooJNI.BMXRTCService_sendRTCMessage(swigCPtr, this, BMXMessage.getCPtr(msg), msg);
  }

  public void addRTCServiceListener(BMXRTCServiceListener listener) {
    flooJNI.BMXRTCService_addRTCServiceListener(swigCPtr, this, BMXRTCServiceListener.getCPtr(listener), listener);
  }

  public void removeRTCServiceListener(BMXRTCServiceListener listener) {
    flooJNI.BMXRTCService_removeRTCServiceListener(swigCPtr, this, BMXRTCServiceListener.getCPtr(listener), listener);
  }

}
