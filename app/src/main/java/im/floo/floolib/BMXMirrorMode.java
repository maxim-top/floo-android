/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public enum BMXMirrorMode {
  Default,
  Enable,
  Disable;

  public final int swigValue() {
    return swigValue;
  }

  public static BMXMirrorMode swigToEnum(int swigValue) {
    BMXMirrorMode[] swigValues = BMXMirrorMode.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (BMXMirrorMode swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + BMXMirrorMode.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private BMXMirrorMode() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private BMXMirrorMode(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private BMXMirrorMode(BMXMirrorMode swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

