package com.teamapp.ui.base

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.teamapp.BR
import com.teamapp.R

import com.teamapp.utils.factory.IntentProviderFactory
import com.teamapp.utils.factory.ViewModelProviderFactory
import com.teamapp.utils.httpErrorMap
import com.teamapp.utils.showError
import com.google.gson.Gson
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import pub.devrel.easypermissions.EasyPermissions
import retrofit2.HttpException
import javax.inject.Inject

abstract class BaseActivity<N, V : BaseViewModel<N>, VB : ViewDataBinding> : AppCompatActivity(),
    View.OnClickListener, EasyPermissions.PermissionCallbacks, BaseNavigator {

    lateinit var viewDataBinding: VB
    lateinit var viewModel: V

    @Inject
    lateinit var intentProviderFactory: IntentProviderFactory
    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory
    @Inject
    lateinit var mFragmentInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var moshi: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        init()
    }

    @LayoutRes
    abstract fun getLayout(): Int
    abstract fun init()

    abstract fun onViewModelCreated(viewModel: V)

    abstract fun onViewBound(viewDataBinding: VB)

    override fun onClick(p0: View?) {

    }

    fun <T : BaseViewModel<N>> getViewModel(viewModel: Class<T>): T {
        return ViewModelProvider(this, viewModelFactory).get(viewModel)
    }

    override fun onBackPress() {
        onBackPressed()
    }

    override fun onError(t: Throwable, showAction: Boolean, function: () -> Unit) {
        if (t is HttpException) {
            when (val code = t.code()) {
                401 -> {
                    showError(httpErrorMap[code] ?: return, showAction = false)
                    Handler().postDelayed({

                        onSessionExpired()
                    }, 2000)
                }
                else -> showError(httpErrorMap[code] ?: return, showAction = true) {
                    function()
                }
            }

        } else {
            showError(t.message ?: return, showAction = showAction) {
                function()
            }
        }
    }

    fun onSessionExpired() {
        //Call activity on Session Expire
    }

    fun bind(viewModelClass: Class<V>) {
        viewModel = getViewModel(viewModelClass)
        onViewModelCreated(viewModel)
        viewDataBinding = DataBindingUtil.setContentView(this, getLayout())
        viewDataBinding.setVariable(BR.viewModel, viewModel)
        viewDataBinding.executePendingBindings()
        onViewBound(viewDataBinding)
        viewModel.setData(intent.extras ?: return)
        createGeneralNotificationChannel()
    }

    fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        window.decorView.systemUiVisibility = (
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }

    private fun createGeneralNotificationChannel() {
        val sound: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        //Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.general_channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                getString(R.string.general_notification_channel_id),
                name,
                importance
            ).apply {
                description = descriptionText
            }
            val attributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()
            // Register the channel with the system

            channel.setSound(sound, attributes)
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * @author Muheeb Mehraj
     * @param Required Permission details, Permission Description
     * @description Used to request multiple permissions at once which are handled by Easy Permssion
     * library.
     */
    fun requestPermissions(rcList: Array<String>, reqRational: String) {
        if (EasyPermissions.hasPermissions(this, *rcList)) {
            afterPermissionTask()
        } else {
            EasyPermissions.requestPermissions(this, reqRational, 100, *rcList)
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
    }

    /**
     * @author Muheeb Mehraj
     * @description Callback invoked after the user performs any action on Permission Dialog
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    fun afterPermissionTask() {
        Toast.makeText(this, "Permission exists", Toast.LENGTH_LONG).show()
    }
}