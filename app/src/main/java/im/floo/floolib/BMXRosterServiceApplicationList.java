/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class BMXRosterServiceApplicationList {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected BMXRosterServiceApplicationList(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(BMXRosterServiceApplicationList obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_BMXRosterServiceApplicationList(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public BMXRosterServiceApplicationList() {
    this(flooJNI.new_BMXRosterServiceApplicationList__SWIG_0(), true);
  }

  public BMXRosterServiceApplicationList(long n) {
    this(flooJNI.new_BMXRosterServiceApplicationList__SWIG_1(n), true);
  }

  public long size() {
    return flooJNI.BMXRosterServiceApplicationList_size(swigCPtr, this);
  }

  public long capacity() {
    return flooJNI.BMXRosterServiceApplicationList_capacity(swigCPtr, this);
  }

  public void reserve(long n) {
    flooJNI.BMXRosterServiceApplicationList_reserve(swigCPtr, this, n);
  }

  public boolean isEmpty() {
    return flooJNI.BMXRosterServiceApplicationList_isEmpty(swigCPtr, this);
  }

  public void clear() {
    flooJNI.BMXRosterServiceApplicationList_clear(swigCPtr, this);
  }

  public void add(BMXRosterService.Application x) {
    flooJNI.BMXRosterServiceApplicationList_add(swigCPtr, this, BMXRosterService.Application.getCPtr(x));
  }

  public BMXRosterService.Application get(int i) {
    long cPtr = flooJNI.BMXRosterServiceApplicationList_get(swigCPtr, this, i);
    return (cPtr == 0) ? null : new BMXRosterService.Application(cPtr, true);
  }

  public void set(int i, BMXRosterService.Application val) {
    flooJNI.BMXRosterServiceApplicationList_set(swigCPtr, this, i, BMXRosterService.Application.getCPtr(val));
  }

}
