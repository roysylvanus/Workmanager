package com.example.background.workers

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.text.TextUtils

import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.background.KEY_IMAGE_URI
import com.example.background.R

class BlurWorker(context: Context, params:WorkerParameters) : Worker(context,params) {
    private val TAG = "BlurWorker"
    override fun doWork(): Result {


        val appContext = applicationContext

        val resourceUri = inputData.getString(KEY_IMAGE_URI)

       makeStatusNotification("Blurring the image",appContext)
         return try {

             if (TextUtils.isEmpty(resourceUri)) {
                 Log.e(TAG, "Invalid input uri")
                 throw IllegalArgumentException("Invalid input uri")
             }

             val resolver = appContext.contentResolver

             val picture = BitmapFactory.decodeStream(resolver.openInputStream(Uri.parse(resourceUri)))

             val output = blurBitmap(picture,appContext)

             val outputUri = writeBitmapToFile(appContext,output)

             makeStatusNotification("$outputUri",appContext)
             val outputData = workDataOf(KEY_IMAGE_URI to outputUri.toString())
             Result.success(outputData)


        }catch (e:Throwable){

            Log.e(TAG,"Error applying blur")
             Result.failure()
        }
    }
}