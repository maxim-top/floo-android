/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package im.floo.floolib;

public enum BMXStreamRole {
  Publish,
  Subscribe,
  Both;

  public final int swigValue() {
    return swigValue;
  }

  public static BMXStreamRole swigToEnum(int swigValue) {
    BMXStreamRole[] swigValues = BMXStreamRole.class.getEnumConstants();
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (BMXStreamRole swigEnum : swigValues)
      if (swigEnum.swigValue == swigValue)
        return swigEnum;
    throw new IllegalArgumentException("No enum " + BMXStreamRole.class + " with value " + swigValue);
  }

  @SuppressWarnings("unused")
  private BMXStreamRole() {
    this.swigValue = SwigNext.next++;
  }

  @SuppressWarnings("unused")
  private BMXStreamRole(int swigValue) {
    this.swigValue = swigValue;
    SwigNext.next = swigValue+1;
  }

  @SuppressWarnings("unused")
  private BMXStreamRole(BMXStreamRole swigEnum) {
    this.swigValue = swigEnum.swigValue;
    SwigNext.next = this.swigValue+1;
  }

  private final int swigValue;

  private static class SwigNext {
    private static int next = 0;
  }
}

