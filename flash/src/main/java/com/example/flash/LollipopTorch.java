package com.example.flash;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraMetadata;
import android.hardware.camera2.CaptureRequest;
import android.os.Build;
import android.util.Size;
import android.view.Surface;
import android.widget.CompoundButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class LollipopTorch implements Torch {

    private CameraCaptureSession mSession;
    private CaptureRequest.Builder mBuilder;
    private CameraDevice mCameraDevice;

    private CameraManager mCameraManager;

    private SurfaceTexture mSurfaceTexture;
    private Surface mSurface;
    private MyCheckedChangeListener mFlashListener;

    public LollipopTorch(Context context) {
        try {
            init(context);
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }

    private void init(Context context) throws CameraAccessException {
        mCameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
        // here to judge if flash is available
        CameraCharacteristics cameraCharacteristics = mCameraManager.getCameraCharacteristics("0");
        boolean flashAvailable = cameraCharacteristics
                .get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
        if (flashAvailable) {
            // noinspection MissingPermission
            mCameraManager.openCamera("0", new MyCameraDeviceStateCallback(), null);
        } else {
            Toast.makeText(context, "Flash not available", Toast.LENGTH_SHORT).show();
            // todo: throw Exception
        }
        // noinspection MissingPermission
        mCameraManager.openCamera("0", new MyCameraDeviceStateCallback(), null);

        mFlashListener = new MyCheckedChangeListener();
    }

    /**
     * camera device callback
     */
    class MyCameraDeviceStateCallback extends CameraDevice.StateCallback {

        @Override
        public void onOpened(CameraDevice camera) {
            mCameraDevice = camera;
            // get builder
            try {
                mBuilder = camera.createCaptureRequest(CameraDevice.TEMPLATE_MANUAL);
                // flash on, default is on
                mBuilder.set(CaptureRequest.CONTROL_AE_MODE, CameraMetadata.CONTROL_AF_MODE_AUTO);
                mBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);
                List<Surface> list = new ArrayList<Surface>();
                mSurfaceTexture = new SurfaceTexture(1);
                Size size = getSmallestSize(mCameraDevice.getId());
                mSurfaceTexture.setDefaultBufferSize(size.getWidth(), size.getHeight());
                mSurface = new Surface(mSurfaceTexture);
                list.add(mSurface);
                mBuilder.addTarget(mSurface);
                camera.createCaptureSession(list, new MyCameraCaptureSessionStateCallback(), null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onDisconnected(CameraDevice camera) {

        }

        @Override
        public void onError(CameraDevice camera, int error) {

        }
    }

    private Size getSmallestSize(String cameraId) throws CameraAccessException {
        Size[] outputSizes = mCameraManager.getCameraCharacteristics(cameraId)
                .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)
                .getOutputSizes(SurfaceTexture.class);
        if (outputSizes == null || outputSizes.length == 0) {
            throw new IllegalStateException(
                    "Camera " + cameraId + "doesn't support any outputSize.");
        }
        Size chosen = outputSizes[0];
        for (Size s : outputSizes) {
            if (chosen.getWidth() >= s.getWidth() && chosen.getHeight() >= s.getHeight()) {
                chosen = s;
            }
        }
        return chosen;
    }

    /**
     * session callback
     */
    class MyCameraCaptureSessionStateCallback extends CameraCaptureSession.StateCallback {

        @Override
        public void onConfigured(CameraCaptureSession session) {
            mSession = session;
            try {
                mSession.setRepeatingRequest(mBuilder.build(), null, null);
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onConfigureFailed(CameraCaptureSession session) {

        }
    }

    /**
     * switch listener
     */
    class MyCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            try {
                if (isChecked) {
                    mBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_TORCH);
                    mSession.setRepeatingRequest(mBuilder.build(), null, null);
                } else {
                    mBuilder.set(CaptureRequest.FLASH_MODE, CameraMetadata.FLASH_MODE_OFF);
                    mSession.setRepeatingRequest(mBuilder.build(), null, null);
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private void close() {
        if (mCameraDevice == null || mSession == null) {
            return;
        }
        mSession.close();
        mCameraDevice.close();
        mCameraDevice = null;
        mSession = null;
    }

    @Override
    public void flashOn() {
        mFlashListener.onCheckedChanged(null, true);
    }

    @Override
    public void flashOff() {
        mFlashListener.onCheckedChanged(null, false);
    }

    @Override
    public void release() {
        close();
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
