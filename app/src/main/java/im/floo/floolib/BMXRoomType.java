/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public enum BMXRoomType {
  Communication,
  Broadcast;

  public final int swigValue() {
    return swigValue;
  }

  public static BMXRoomType swigToEnum(int swigValue) {
    BMXRoomType[] swigValues = BMXRoomType.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (BMXRoomType swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + BMXRoomType.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private BMXRoomType() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private BMXRoomType(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private BMXRoomType(BMXRoomType swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}
