package com.yunusbedir.pokem.util

import android.view.View
import androidx.annotation.IntDef
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@IntDef(*[View.VISIBLE, View.INVISIBLE, View.GONE])
@Retention(RetentionPolicy.SOURCE)
annotation class Visibility
