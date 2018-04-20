package rajansinh.sttl.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import rajansinh.sttl.com.BR
import rajansinh.sttl.com.R
import rajansinh.sttl.model.User


/**
 * Created by rajan.bhavsar on 3/22/2018.
 */
public class UserAdapter(var dataitems: MutableList<User>) : RecyclerView.Adapter<UserAdapter.CustomUserViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): CustomUserViewModel {
        val inflater = parent?.getContext()?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val databind: ViewDataBinding = DataBindingUtil.inflate(inflater, R.layout.user_layout_item, parent, false)
        return CustomUserViewModel(databind)
    }

    override fun onBindViewHolder(holder: CustomUserViewModel?, position: Int) {
        holder?.bind(dataitems[position])
    }


    override fun getItemCount(): Int {
        return dataitems.size
    }

    class CustomUserViewModel(val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root) {
        fun bind(data: Any) {
            dataBinding.setVariable(BR.data, data)
            dataBinding.executePendingBindings()
        }
    }

    fun chageData(list: List<User>) {
        dataitems.addAll(list)
        notifyDataSetChanged()
    }

    fun clearData() {
        dataitems.clear()
    }
}