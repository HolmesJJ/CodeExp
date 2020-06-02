package com.example.codeexp.ui.fragment.individual.faceidentify;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.arcsoft.face.ErrorInfo;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.enums.DetectFaceOrientPriority;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.RuntimeABI;
import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.constants.Constants;
import com.example.codeexp.databinding.FragmentIndividualFaceIdentifyBinding;
import com.example.codeexp.thread.CustomThreadPool;
import com.example.codeexp.ui.viewmodel.individual.faceidentify.IndividualFaceIdentifyViewModel;
import com.example.codeexp.util.BitmapUtils;
import com.example.codeexp.util.ToastUtils;
import com.otaliastudios.cameraview.controls.Facing;
import com.otaliastudios.cameraview.frame.Frame;
import com.otaliastudios.cameraview.frame.FrameProcessor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class IndividualFaceIdentifyFragment extends BaseFragment<FragmentIndividualFaceIdentifyBinding, IndividualFaceIdentifyViewModel> {

    private static final String TAG = "FaceIdentifyFragment";
    private static final String IS_CHECK_IN = "is_check_in";

    /**
     * 人脸类型
     */
    private static final int TYPE_ENROLLED = 0;
    private static final int TYPE_CAPTURED = 1;

    // Demo 所需的动态库文件
    private static final String[] ARCSOFT_LIBRARIES = new String[]{
            // 人脸相关
            "libarcsoft_face_engine.so",
            "libarcsoft_face.so",
            // 图像库相关
            "libarcsoft_image_util.so"
    };

    private static CustomThreadPool sThreadPoolRGBVerify = new CustomThreadPool(Thread.MAX_PRIORITY);
    private static CustomThreadPool sThreadPoolActiveArcsoftEngine = new CustomThreadPool(Thread.MAX_PRIORITY);

    private FaceEngine faceEngine;

    private boolean isCheckIn = true;
    private boolean isArcsoftEngineActivated = false;
    private int faceEngineCode = -1;
    private int mPreviewW;
    private int mPreviewH;
    private byte[] mRGBCameraVerifyNv21;
    private boolean isFaceIdentify = false;

    public static IndividualFaceIdentifyFragment newInstance(boolean isCheckIn) {

        Bundle args = new Bundle();
        args.putBoolean(IS_CHECK_IN, isCheckIn);
        IndividualFaceIdentifyFragment fragment = new IndividualFaceIdentifyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            isCheckIn = bundle.getBoolean(IS_CHECK_IN);
        }
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_individual_face_identify;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<IndividualFaceIdentifyViewModel> getViewModelClazz() {

        return IndividualFaceIdentifyViewModel.class;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    @Override
    public void initData() {

        super.initData();

        if (isCheckIn) {
            mBinding.toolbar.setTitle(_mActivity.getString(R.string.check_in));
        } else {
            mBinding.toolbar.setTitle(_mActivity.getString(R.string.check_out));
        }

        staticLoadArcsoftLibraries();

        sThreadPoolActiveArcsoftEngine.execute(() -> {
            activeArcsoftEngine();
        });

        mBinding.camera.setFacing(Facing.FRONT);
        mBinding.camera.addFrameProcessor(new FrameProcessor() {
            @Override
            public void process(Frame frame) {
                Log.i(TAG, "Face Identify: Start" + frame.getSize());
                byte[] data = (byte[])frame.getData();
                if (mRGBCameraVerifyNv21 == null) {
                    mRGBCameraVerifyNv21 = new byte[data.length];
                    mPreviewW = frame.getSize().getWidth();
                    mPreviewH = frame.getSize().getHeight();
                }
                if (!isFaceIdentify) {
                    System.arraycopy(data, 0, mRGBCameraVerifyNv21, 0, data.length);
                    isFaceIdentify = true;
                    sThreadPoolRGBVerify.execute(() -> {
                        Log.i(TAG, "Face Identify: Done1");
                        faceIdentify();
                        isFaceIdentify = false;
                        Log.i(TAG, "Face Identify: Done2");
                    });
                }
            }
        });
    }

    // 虹软库静态加载并初始化
    private void staticLoadArcsoftLibraries() {
        boolean load = checkSoFile(ARCSOFT_LIBRARIES);
        if (load) {
            Log.i(TAG, "Arcsoft Libraries loaded...");
        }
    }

    /**
     * 检查能否找到动态链接库，如果找不到，请修改工程配置
     *
     * @param libraries 需要的动态链接库
     * @return 动态库是否存在
     */
    private boolean checkSoFile(String[] libraries) {
        File dir = new File(_mActivity.getApplicationInfo().nativeLibraryDir);
        File[] files = dir.listFiles();
        if (files == null || files.length == 0) {
            return false;
        }
        List<String> libraryNameList = new ArrayList<>();
        for (File file : files) {
            libraryNameList.add(file.getName());
        }
        boolean exists = true;
        for (String library : libraries) {
            exists &= libraryNameList.contains(library);
        }
        return exists;
    }

    /**
     * 激活虹软引擎
     *
     */
    public void activeArcsoftEngine() {
        RuntimeABI runtimeABI = FaceEngine.getRuntimeABI();
        Log.i(TAG, "getRuntimeABI: " + runtimeABI);
        long start = System.currentTimeMillis();
        int activeCode = FaceEngine.activeOnline(_mActivity, Constants.APP_ID, Constants.SDK_KEY);
        Log.i(TAG, "Active Time Cost: " + (System.currentTimeMillis() - start));
        if (activeCode == ErrorInfo.MOK) {
            _mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initEngine();
                    ToastUtils.showShort("Arcsoft Engine Activated Success");
                    isArcsoftEngineActivated = true;
                }
            });
        } else if (activeCode == ErrorInfo.MERR_ASF_ALREADY_ACTIVATED) {
            _mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    initEngine();
                    ToastUtils.showShort("Arcsoft Engine Already Activated");
                    isArcsoftEngineActivated = true;
                }
            });
        } else {
            _mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtils.showShort("Arcsoft Engine Activated Failed");
                    isArcsoftEngineActivated = false;
                }
            });
        }
    }

    private void initEngine() {
        faceEngine = new FaceEngine();
        faceEngineCode = faceEngine.init(_mActivity, DetectMode.ASF_DETECT_MODE_IMAGE, DetectFaceOrientPriority.ASF_OP_0_ONLY,
                16, 6, FaceEngine.ASF_FACE_RECOGNITION | FaceEngine.ASF_AGE | FaceEngine.ASF_FACE_DETECT | FaceEngine.ASF_GENDER | FaceEngine.ASF_FACE3DANGLE);

        Log.i(TAG, "initEngine: " + faceEngineCode);

        if (faceEngineCode != ErrorInfo.MOK) {
            Log.i(TAG, "Face Engine init failed: " + faceEngineCode);
        }
    }

    private void unInitEngine() {
        if (faceEngine != null) {
            faceEngineCode = faceEngine.unInit();
            faceEngine = null;
            Log.i(TAG, "unInitEngine: " + faceEngineCode);
        }
    }

    private void faceIdentify() {
        Bitmap bitmap = BitmapUtils.covertNV21ToBitmap(mRGBCameraVerifyNv21, mPreviewW, mPreviewH);
        Bitmap rotatedBitmap = BitmapUtils.rotateBitmap(bitmap, 270);
        Bitmap flippedBitmap = BitmapUtils.flipBitmap(rotatedBitmap);

        _mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mBinding.ivTest.setImageBitmap(flippedBitmap);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!mBinding.camera.isOpened()) {
            mBinding.camera.open();
        }
    }

    @Override
    public void onPause() {
        if (mBinding.camera.isOpened()) {
            mBinding.camera.close();
        }
        super.onPause();
    }

    @Override
    public void onDestroy() {
        unInitEngine();
        if (mBinding.camera.isOpened()) {
            mBinding.camera.close();
        }
        mBinding.camera.destroy();
        super.onDestroy();
    }
}
