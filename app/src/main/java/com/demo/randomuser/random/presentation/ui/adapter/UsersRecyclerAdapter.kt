package com.demo.randomuser.random.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.randomuser.common.Utils
import com.demo.randomuser.random.data.model.Users
import kotlinx.android.synthetic.main.item_user.view.*
import javax.inject.Inject


class UsersRecyclerAdapter @Inject constructor() :
    RecyclerView.Adapter<UsersRecyclerAdapter.ArticleViewHolder>() {

    private var usersList: MutableList<Users> = ArrayList()
    private var function: ((Users) -> Unit)? = null


    fun populateUsers(users: List<Users>, isFromPaginaton: Boolean) {
        if (!isFromPaginaton) usersList.clear()
        usersList.addAll(users)
        notifyDataSetChanged()
    }

    fun addClickListener(function: (Users) -> Unit) {
        this.function = function
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(
            inflater.inflate(com.demo.randomuser.R.layout.item_user, parent, false)
        )
    }

    fun updateList(list: ArrayList<Users>) {
        usersList = list
        notifyDataSetChanged()
    }

    override fun getItemCount() = usersList.size

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) =
        holder.bind(usersList[position])

    fun getUsers(): ArrayList<Users> {
        return usersList as ArrayList<Users>
    }


    inner class ArticleViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Users) {
            with(itemView) {
                tvName.text = user.name.title + ". " + user.name.first + " " + user.name.last
                tvGender.text = user.gender.capitalize()
                tvEmail.text = user.email
                tvBirthday.text = Utils().formatDate(user.dob.date)
                ivImage.setImageURI(user.picture.large)

                setOnClickListener {
                    function?.let { function ->
                        function(user)
                    }
                }
            }
        }


    }


}