package com.demo.randomuser.random.presentation.ui.fragments


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.randomuser.R
import com.demo.randomuser.common.Status
import com.demo.randomuser.common.Utils
import com.demo.randomuser.common.ViewModelFactory
import com.demo.randomuser.random.data.model.Users
import com.demo.randomuser.random.presentation.ui.adapter.UsersRecyclerAdapter
import com.demo.randomuser.random.presentation.viewmodel.RandomListViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_random_list.*
import javax.inject.Inject

var TAG_USER = "USER"
class RandomListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var usersRecyclerAdapter: UsersRecyclerAdapter

    private lateinit var userViewModel: RandomListViewModel

    private var loading = true
    private var pastVisiblesItems: Int = 0
    private var visibleItemCount:Int = 0
    private var totalItemCount:Int = 0
    private var page = 1
    private var isFromPagination = false

    private var users: ArrayList<Users> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launchWhenStarted {
            try {

                initViews()

                showSnack()

                userViewModel = ViewModelProviders.of(activity!!, viewModelFactory)
                    .get(RandomListViewModel::class.java)

                userViewModel.userResult.observe(this@RandomListFragment, Observer {
                    when (it.status) {
                        Status.LOADING -> {
                            if (!isFromPagination) showLoading()
                        }
                        Status.ERROR -> hideLoading()
                        Status.SUCCESS -> {
                            hideLoading()
                            it.data?.let { articles ->
                                usersRecyclerAdapter.populateUsers(articles,isFromPagination)
                                users = usersRecyclerAdapter.getUsers()
                            }
                        }

                    }
                })

            } finally {

                // This line might execute after Lifecycle is DESTROYED.
                if (lifecycle.currentState >= Lifecycle.State.STARTED) {
                    // Here, since we've checked, it is safe to run any
                    // Fragment transactions.

                }

            }
        }
    }

    private fun initViews() {
        val mLayoutManager = LinearLayoutManager(context)
        rvUsers.layoutManager = mLayoutManager
        rvUsers.adapter = usersRecyclerAdapter
        swipeRefresh.setOnRefreshListener {
            showSnack()
            isFromPagination = false
            swipeRefresh.isRefreshing = false
            userViewModel.loadUsers(page, true)
        }

        usersRecyclerAdapter.addClickListener {it->
            view?.let { it1 -> Navigation.findNavController(it1).navigate(R.id.userDetails,prepareBundleForUser(it))}
        }


        initListeners(mLayoutManager)
    }

    private fun initListeners(mLayoutManager: LinearLayoutManager) {
        etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //empty
            }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                //empty
            }

            override fun afterTextChanged(editable: Editable) {
                filter(editable.toString())

            }
        })

        rvUsers.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0)
                //check for scroll down
                {
                    visibleItemCount = mLayoutManager.childCount
                    totalItemCount = mLayoutManager.itemCount
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition()
                    if (loading && context?.let { Utils().isNetworkAvailable(it) }!!) {
                        if (visibleItemCount + pastVisiblesItems >= totalItemCount) {
                            isFromPagination = true
                            userViewModel.loadUsers(page++, true)

                        } else {
                            showSnack()
                        }
                    }
                }
            }
        })
    }


    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        rvUsers.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        rvUsers.visibility = View.VISIBLE
        if (swipeRefresh.isRefreshing)
            swipeRefresh.isRefreshing = false
    }

    private fun prepareBundleForUser(user: Users): Bundle {
        val bundle = Bundle()
        bundle.putParcelable(TAG_USER, user)
        return bundle
    }

    fun filter(text: String) {
        val filterUser : ArrayList<Users> = ArrayList()
        for (tempUser in users) {
            if (tempUser.gender.toLowerCase().contains(text.toLowerCase())
                || tempUser.name.first.toLowerCase().contains(text.toLowerCase())
                || tempUser.location.country.toLowerCase().contains(text.toLowerCase())
                || tempUser.name.last.toLowerCase().contains(text.toLowerCase())
                || Utils().formatDate(tempUser.dob.date).toLowerCase().contains(text.toLowerCase())) {
                filterUser.add(tempUser)
            }
        }
        usersRecyclerAdapter.updateList(filterUser)
    }

    private fun showSnack() {
        if (!context?.let { Utils().isNetworkAvailable(it) }!!) {
            val snack = view?.let {
                Snackbar.make(
                    it,
                    getString(R.string.no_internet),
                    Snackbar.LENGTH_LONG
                )
            }
            snack?.show()
        }
    }



}
