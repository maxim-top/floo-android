/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

/**
 *  日志级别
 **/
public enum BMXLogLevel {
  Error,
  Warning,
  Debug;

  public final int swigValue() {
    return swigValue;
  }

  public static BMXLogLevel swigToEnum(int swigValue) {
    BMXLogLevel[] swigValues = BMXLogLevel.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (BMXLogLevel swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + BMXLogLevel.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private BMXLogLevel() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private BMXLogLevel(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private BMXLogLevel(BMXLogLevel swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

