package com.example.flash;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.util.Log;

public class MarshmallowTorch implements Torch {

    private static final String TAG = MarshmallowTorch.class.getSimpleName();

    private String mCameraId;
    private CameraManager mCameraManager;

    public MarshmallowTorch(Context context) {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);

        try {
            mCameraId = getCameraId();

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    public void flashOn() {
        try {
            mCameraManager.setTorchMode(mCameraId, true);
        } catch (CameraAccessException e) {
            Log.d(TAG, "flash on fail : " + e.getLocalizedMessage());
        }
    }

    public void flashOff() {
        try {
            mCameraManager.setTorchMode(mCameraId, false);
        } catch (CameraAccessException e) {
            Log.d(TAG, "flash off fail : " + e.getLocalizedMessage());
        }
    }

    public void release() {

    }

    private String getCameraId() throws CameraAccessException {
        String[] cameraIds = mCameraManager.getCameraIdList();
        for (String id : cameraIds) {
            CameraCharacteristics info = mCameraManager.getCameraCharacteristics(id);
            Boolean flashAvailable = info.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
            Integer lensFacing = info.get(CameraCharacteristics.LENS_FACING);
            if (flashAvailable != null && flashAvailable
                    && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                return id;
            }
        }
        return null;
    }
}
