package com.example.fragmentlifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class FragmentOne : Fragment() {

    val TAG = "FragmentOne"

    override fun onAttach(context: Context) {
        d(TAG, "onAttach called")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        d(TAG, "onCreateView called")
        return inflater!!.inflate(R.layout.fragment_one, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        d(TAG, "onActivityCreated called")
        super.onActivityCreated(savedInstanceState)
    }

    override fun onStart() {
        d(TAG, "onStart called")
        super.onStart()
    }

    override fun onResume() {
        d(TAG, "onResume called")
        super.onResume()
    }

    override fun onPause() {
        d(TAG, "onPause called")
        super.onPause()
    }

    override fun onStop() {
        d(TAG, "onStop called")
        super.onStop()
    }

    override fun onDestroyView() {
        d(TAG, "onDestroyView called")
        super.onDestroyView()
    }

    override fun onDestroy() {
        d(TAG, "onDestroy called")
        super.onDestroy()
    }

    override fun onDetach() {
        d(TAG, "onDetach called")
        super.onDetach()
    }
}