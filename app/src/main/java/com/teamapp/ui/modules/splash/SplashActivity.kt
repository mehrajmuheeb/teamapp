package com.teamapp.ui.modules.splash

import com.teamapp.R
import com.teamapp.databinding.ActivitySplashBinding
import com.teamapp.ui.base.BaseActivity
import com.teamapp.ui.bindingAdapters.halt
import com.teamapp.ui.modules.dashboard.MainActivity


class SplashActivity : BaseActivity<SplashNavigator, SplashViewModel, ActivitySplashBinding>(),
    SplashNavigator {
    override fun getLayout(): Int = R.layout.activity_splash

    override fun init() {
        bind(SplashViewModel::class.java)

    }

    override fun onViewModelCreated(viewModel: SplashViewModel) {
        viewModel.setNavigator(this)
        halt(2000) {
            startActivity(
                intentProviderFactory.create(MainActivity::class.java, null, 0)
            )
            finish()
        }
    }

    override fun onViewBound(viewDataBinding: ActivitySplashBinding) {


    }

}
