/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.databinding.basicsample.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.android.practice.kotlin_basic.R
import com.android.practice.kotlin_basic.databinding.PlainActivityBinding
import com.example.android.databinding.basicsample.data.Popularity
import com.example.android.databinding.basicsample.data.SimpleViewModel

/**
 * Plain old activity with lots of problems to fix.
 */
class PlainOldActivity : AppCompatActivity() {

    // Obtain ViewModel from ViewModelProviders
    private val viewModel by lazy { ViewModelProviders.of(this).get(SimpleViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: PlainActivityBinding =
            DataBindingUtil.setContentView(this, R.layout.plain_activity)

        binding.lifecycleOwner = this

        binding.vm = viewModel
    }

    /**
     * This method is triggered by the `android:onclick` attribute in the layout. It puts business
     * logic in the activity, which is not ideal. We should do something about that.
     */

    private fun getAssociatedColor(popularity: Popularity, context: Context): Int {
        return when (popularity) {
            Popularity.NORMAL -> context.theme.obtainStyledAttributes(
                intArrayOf(android.R.attr.colorForeground)
            ).getColor(0, 0x000000)

            Popularity.POPULAR -> ContextCompat.getColor(context, R.color.popular)
            Popularity.STAR -> ContextCompat.getColor(context, R.color.star)
        }
    }

    private fun getDrawablePopularity(popularity: Popularity, context: Context): Drawable? {
        return when (popularity) {
            Popularity.NORMAL -> {
                ContextCompat.getDrawable(context, R.drawable.ic_person_black_96dp)
            }

            Popularity.POPULAR -> {
                ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp)
            }

            Popularity.STAR -> {
                ContextCompat.getDrawable(context, R.drawable.ic_whatshot_black_96dp)
            }
        }
    }
}
