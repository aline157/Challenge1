package com.example.challenge1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.challenge1.databinding.FragmentMoreBinding
import com.example.challenge1.internal.addDataToPrefs
import com.example.challenge1.internal.getCurrentAccount
import com.example.challenge1.internal.getImage
import com.example.challenge1.internal.*

class MoreFragment : Fragment() {
    private var _binding: FragmentMoreBinding? = null

    private val binding get() = _binding!!
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val image = activity?.getImage()
        if (image != null){
            binding.profileImageView.setImageBitmap(image)
        }

        binding.editProfileBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_editProfileFragment)

        }
        binding.changePasswordBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_changePasswordFragment)
        }
        binding.aboutUsBtn.setOnClickListener{
            navController.navigate(R.id.action_moreFragment_to_aboutUsFragment)
        }


        binding.logoutBtn.setOnClickListener{
            val account:HashMap<String,String> = HashMap()

            account["firstname"] = activity?.getCurrentAccount()!!.firstName.toString()
            account["lastname"] = activity?.getCurrentAccount()!!.LastName.toString()
            account["email"] = activity?.getCurrentAccount()!!.email.toString()
            account["password"] = activity?.getCurrentAccount()!!.password.toString()
            account["isSignIn"] = "false"
            activity?.addDataToPrefs(account)
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
            navController.popBackStack()

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}