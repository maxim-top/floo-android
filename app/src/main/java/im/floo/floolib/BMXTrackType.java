/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public enum BMXTrackType {
  Audio,
  Video;

  public final int swigValue() {
    return swigValue;
  }

  public static BMXTrackType swigToEnum(int swigValue) {
    BMXTrackType[] swigValues = BMXTrackType.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (BMXTrackType swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + BMXTrackType.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private BMXTrackType() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private BMXTrackType(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private BMXTrackType(BMXTrackType swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}
