package com.example.challenge1

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.challenge1.databinding.FragmentEditProfileBinding
import com.example.challenge1.internal.*
import com.example.challenge1.internal.Utils.ValidationManager
import com.example.challenge1.ui.toBase64
import com.example.challenge1.ui.toBitmap
import com.google.android.material.bottomsheet.BottomSheetDialog

class EditProfileFragment : Fragment() {
    private var _binding: FragmentEditProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->

        if (uri !=null){
            activity?.let { it.saveImage(uri.toBitmap(it).toBase64()) }
            binding.profileImageView.setImageURI(uri)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        val account = activity?.getCurrentAccount()
        binding.emailField.text = account!!.email
        binding.lastNameField.setText(account!!.LastName)
        binding.FirstNameField.setText(account!!.firstName)

        val image = activity?.getImage()
        if (image != null){
            binding.profileImageView.setImageBitmap(image)
        }
        binding.profileImageView.setOnClickListener{
            showBottomSheetDialog()
        }

        binding.submit.setOnClickListener{
            val firstName = binding.FirstNameField.getTextTrimed()
            val lastName = binding.lastNameField.getTextTrimed()
            var firstNameField = false
            var lastNameField = false

            if(ValidationManager.isFieldValid(firstName)){
                firstNameField =true
            }else{
                binding.FirstNameField.error = "Please enter a valid name"
            }

            if(ValidationManager.isFieldValid(lastName)){
                lastNameField =true
            }else{
                binding.lastNameField.error = "Please enter a valid name"
            }
            if(firstNameField && lastNameField ){
                val newAccount:HashMap<String,String> = HashMap()
                newAccount["firstname"] = firstName
                newAccount["lastname"] = lastName
                newAccount["email"] = account.email!!
                newAccount["password"] = account.password!!
                newAccount["isSignIn"] = "true"

                activity?.let {
                    it.addDataToPrefs(newAccount)
                    Toast.makeText(it,"Success! account updated successfully!", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()

                }

            }
        }

    }

    fun showBottomSheetDialog(){
        activity?.let {
            val bottomSheetDialog = BottomSheetDialog(it)
            bottomSheetDialog.setContentView(R.layout.bottom_sheet_dialog_layout)

            val camera = bottomSheetDialog.findViewById<LinearLayout>(R.id.camera)
            val galery = bottomSheetDialog.findViewById<LinearLayout>(R.id.galery)

            bottomSheetDialog.show()

            camera?.setOnClickListener{


                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                try {
                    startActivityForResult(takePictureIntent, 1)
                } catch (e: ActivityNotFoundException) {

                    Toast.makeText(requireActivity(),"Success! account updated successfully!", Toast.LENGTH_SHORT).show()
                }
                bottomSheetDialog.dismiss()
            }
            galery?.setOnClickListener{
                getContent.launch("image/*")
                bottomSheetDialog.dismiss()
            }



        }

    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null) {
            val imageBitmap = data.extras?.get("data") as Bitmap
            activity?.let { it.saveImage(imageBitmap.toBase64()) }
            binding.profileImageView.setImageBitmap(imageBitmap)
        }
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}