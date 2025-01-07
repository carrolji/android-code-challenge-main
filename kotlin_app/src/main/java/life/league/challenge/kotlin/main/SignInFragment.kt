package life.league.challenge.kotlin.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_sign_in.btnSignIn
import kotlinx.android.synthetic.main.fragment_sign_in.tvPassword
import kotlinx.android.synthetic.main.fragment_sign_in.tvUserName
import life.league.challenge.kotlin.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignInFragment : Fragment() {

    private val viewModel: MainViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnSignIn.setOnClickListener {
            val username = tvUserName.text.toString()
            val password = tvPassword.text.toString()
            Log.d("SignInFrag", "Username: $username, password: $password")
            viewModel.login(username, password)
            findNavController().navigate(R.id.action_signInFragment_to_postListFragment)
        }
    }
}