package com.demo.randomuser.random.presentation.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import com.demo.randomuser.R
import com.demo.randomuser.common.Utils
import com.demo.randomuser.random.data.model.Street
import com.demo.randomuser.random.data.model.Users
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.address_layout.*
import kotlinx.android.synthetic.main.birthday_layout.*
import kotlinx.android.synthetic.main.email_layout.*
import kotlinx.android.synthetic.main.fragment_user_detail.*
import kotlinx.android.synthetic.main.phone_layout.*
import kotlinx.android.synthetic.main.user_detail.*

class UserDetailsFragment :DaggerFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            try {
                val users :Users? = arguments?.getParcelable(TAG_USER)
                users.let {
                    val street = it?.location?.street
                    toolbar.title = it?.name?.title+ ". "+it?.name?.first+ " "+it?.name?.last
                    ivProfileImage.setImageURI(it?.picture?.large)
                    tvEmail.text = it?.email
                    tvMobile.text = it?.cell
                    tvStreet.text = street?.number.toString() +" "+street?.name
                    tvCity.text = it?.location?.city
                    tvState.text = it?.location?.state
                    tvCountry.text = it?.location?.country
                    tvDob.text = Utils().formatDate(it?.dob?.date.toString())
                }
            } finally {
                // This line might execute after Lifecycle is DESTROYED.
                if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                    // Here, since we've checked, it is safe to run any
                    // Fragment transactions.

                }

            }
        }
    }



}