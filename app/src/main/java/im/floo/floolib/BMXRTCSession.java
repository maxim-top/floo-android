/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class BMXRTCSession extends BMXBaseObject {
  private transient long swigCPtr;
  private transient boolean swigCMemOwnDerived;

  protected BMXRTCSession(long cPtr, boolean cMemoryOwn) {
    super(flooJNI.BMXRTCSession_SWIGSmartPtrUpcast(cPtr), true);
    swigCMemOwnDerived = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXRTCSession obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwnDerived) {
        swigCMemOwnDerived = false;
        flooJNI.delete_BMXRTCSession(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public long sessionId() {
    return flooJNI.BMXRTCSession_sessionId(swigCPtr, this);
  }

  public long pubHandlerId() {
    return flooJNI.BMXRTCSession_pubHandlerId(swigCPtr, this);
  }

  public long subHandlerId() {
    return flooJNI.BMXRTCSession_subHandlerId(swigCPtr, this);
  }

}