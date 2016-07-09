#include "core_wave_WaveView.h"

/*
 * Class:     core_wave_WaveView
 * Method:    nativeCalcValue
 * Signature: (FF)D
 */
JNIEXPORT jdouble

JNICALL Java_core_wave_WaveView_nativeCalcValue(JNIEnv *env, jobject jobj, jfloat mapX, jfloat offset) {
    double sinFunc = sin(0.75 * M_PI * mapX - offset * M_PI);
    double recessionFunc = pow(4 / (4 + pow(mapX, 4)), 2.5);
    return (jdouble) sinFunc * recessionFunc;
}