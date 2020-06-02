package com.example.codeexp.ui.fragment.enterprise.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeexp.BR;
import com.example.codeexp.R;
import com.example.codeexp.base.BaseFragment;
import com.example.codeexp.constants.Constants;
import com.example.codeexp.databinding.FragmentEnterpriseDetailBinding;
import com.example.codeexp.ui.viewmodel.enterprise.detail.EnterpriseDetailViewModel;
import com.example.codeexp.ui.widget.dialog.FilePickerDialog;
import com.example.codeexp.util.ToastUtils;
import com.github.angads25.filepicker.model.DialogConfigs;
import com.github.angads25.filepicker.model.DialogProperties;
import com.snatik.storage.Storage;

import java.io.File;

public class EnterpriseDetailFragment extends BaseFragment<FragmentEnterpriseDetailBinding, EnterpriseDetailViewModel> {

    private static final String ENTERPRISE_ID = "enterprise_id";

    private DialogProperties properties;
    private Storage storage;

    private int mId;

    public static EnterpriseDetailFragment newInstance(int id){

        Bundle args = new Bundle();
        args.putInt(ENTERPRISE_ID, id);
        EnterpriseDetailFragment fragment = new EnterpriseDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mId = bundle.getInt(ENTERPRISE_ID);
        }
    }

    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return R.layout.fragment_enterprise_detail;
    }

    @Override
    public int initVariableId() {

        return BR.viewModel;
    }

    @Override
    public Class<EnterpriseDetailViewModel> getViewModelClazz() {

        return EnterpriseDetailViewModel.class;
    }

    @Override
    public void initData() {

        super.initData();

        mBinding.toolbar.setTitle(_mActivity.getString(R.string.detail));
        mBinding.ivAvatar.setImageResource(R.mipmap.ic_launcher);
        mBinding.tvName.setText(String.valueOf(mId));

        mBinding.target.setLeftText(R.string.target).setRightText(R.string.enterprise).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.position.setLeftText(R.string.position).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.numberOfEmployees.setLeftText(R.string.number_of_employees).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.workplace.setLeftText(R.string.workplace).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.jobNature.setLeftText(R.string.job_nature).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.expirationDate.setLeftText(R.string.expiration_date).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.startDate.setLeftText(R.string.start_date).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.endDate.setLeftText(R.string.end_date).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.minOfSalary.setLeftText(R.string.min_of_salary).setBottomLineVisible(false).setRightImage(android.R.color.transparent);
        mBinding.maxOfSalary.setLeftText(R.string.max_of_salary).setBottomLineVisible(true).setRightImage(android.R.color.transparent);

        mBinding.upload.setLeftText(R.string.upload).setBottomLineVisible(true)
                .setItemClickListener(
                        this::showUploadDialog
                );

        initProperties();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {

        super.onLazyInitView(savedInstanceState);
    }

    private void initProperties() {
        properties = new DialogProperties();
        properties.selection_mode = DialogConfigs.SINGLE_MODE;
        properties.selection_type = DialogConfigs.FILE_SELECT;
        properties.root = new File("/");
        properties.error_dir = new File("/");
        properties.offset = new File("/");
        properties.extensions = new String[]{"xlsx", "xls", "csv"};
        storage = new Storage(_mActivity);
    }

    private void showUploadDialog() {

        FilePickerDialog dialog = new FilePickerDialog(_mActivity, properties);
        dialog.setTitle("Select a File");
        dialog.setDialogSelectionListener(new FilePickerDialog.DialogSelectionListener() {
            @Override
            public void onSelectedFilePaths(String[] files) {
                File dir = new File(Constants.EMPLOYEES_INFO_PATH);
                if (!dir.exists()) {
                    dir.mkdirs();
                } else {
                    String[] children = dir.list();
                    for (int i = 0; i < children.length; i++) {
                        new File(dir, children[i]).delete();
                    }
                }
                String filename = files[0].substring(files[0].lastIndexOf("/") + 1);
                File targetFile = new File(Constants.EMPLOYEES_INFO_PATH + File.separator + filename);
                File sourceFile = new File(files[0]);
                if (storage.copy(sourceFile.getAbsolutePath(), targetFile.getAbsolutePath())){
                    ToastUtils.showShortSafe(R.string.upload_success);
                } else {
                    ToastUtils.showShortSafe(R.string.upload_failed);
                }
            }

            @Override
            public void onCancel() {

            }
        });
        dialog.show();
    }
}
