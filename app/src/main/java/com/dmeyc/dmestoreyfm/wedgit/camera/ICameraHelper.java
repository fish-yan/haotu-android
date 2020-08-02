package com.dmeyc.dmestoreyfm.wedgit.camera;

import android.hardware.Camera;


public interface ICameraHelper {

    int getNumberOfCameras();

    Camera openCameraFacing(int facing) throws Exception;

    boolean hasCamera(int facing);

    void getCameraInfo(int cameraId, Camera.CameraInfo cameraInfo);
}
