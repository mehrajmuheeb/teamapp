package com.baseandroid.ui.modules.splash

import com.baseandroid.R
import com.baseandroid.databinding.ActivitySplashBinding
import com.baseandroid.ui.base.BaseActivity
import pub.devrel.easypermissions.AppSettingsDialog
import pub.devrel.easypermissions.EasyPermissions


class SplashActivity : BaseActivity<SplashNavigator, SplashViewModel, ActivitySplashBinding>(), SplashNavigator {
    override fun getLayout(): Int = R.layout.activity_splash

    override fun init() {
        bind(SplashViewModel::class.java)

    }

    override fun onViewModelCreated(viewModel: SplashViewModel) {
        viewModel.setNavigator(this)

    }

    override fun onViewBound(viewDataBinding: ActivitySplashBinding) {

        /**
         * @author Muheeb Mehraj
         * Request Permission and supply the String explaining why the permissions are needed.
         * Try to make brief and concise statement.
         */
        requestPermissions(
            arrayOf(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ),
            "Give Location and Camera Permission"
        )
    }

    /**
     * @author Muheeb Mehraj
     * Called when all the expected permissions are granted.
     */
    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {

    }

    /**
     * @author Muheeb Mehraj
     * Called when any one of the expected permission is denied
     */
    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //Shows the dialog which redirects the user to App Settings where User can allow the
                // permission
            AppSettingsDialog.Builder(this).build().show()
        }
    }

}
