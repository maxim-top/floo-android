/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public class GroupApplicationPage extends BMXBaseObject {
  public transient long swigCPtr;

  protected GroupApplicationPage(long cPtr, boolean cMemoryOwn) {
    super(flooJNI.GroupApplicationPage_SWIGUpcast(cPtr), cMemoryOwn);
    swigCPtr = cPtr;
  }

  protected static long getCPtr(GroupApplicationPage obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        flooJNI.delete_GroupApplicationPage(swigCPtr);
      }
      swigCPtr = 0;
    }
    super.delete();
  }

  public GroupApplicationPage() {
    this(flooJNI.new_GroupApplicationPage__SWIG_0(), true);
  }

  public GroupApplicationPage(BMXGroupApplicationList result, long offset) {
    this(flooJNI.new_GroupApplicationPage__SWIG_1(BMXGroupApplicationList.getCPtr(result), result, offset), true);
  }

  public GroupApplicationPage(BMXGroupApplicationList result, String cursor) {
    this(flooJNI.new_GroupApplicationPage__SWIG_2(BMXGroupApplicationList.getCPtr(result), result, cursor), true);
  }

  public GroupApplicationPage(GroupApplicationPage from) {
    this(flooJNI.new_GroupApplicationPage__SWIG_3(GroupApplicationPage.getCPtr(from), from), true);
  }

  public long count() {
    return flooJNI.GroupApplicationPage_count(swigCPtr, this);
  }

  public long offset() {
    return flooJNI.GroupApplicationPage_offset(swigCPtr, this);
  }

  public String cursor() {
    return flooJNI.GroupApplicationPage_cursor(swigCPtr, this);
  }

  public BMXGroupApplicationList result() {
    return new BMXGroupApplicationList(flooJNI.GroupApplicationPage_result(swigCPtr, this), false);
  }

}
