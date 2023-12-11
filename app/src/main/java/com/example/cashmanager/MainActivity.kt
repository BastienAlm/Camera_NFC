package com.example.cashmanager

import android.Manifest
import android.app.Activity
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScannerOptions
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.barcode.common.Barcode
import com.google.mlkit.vision.common.InputImage

class MainActivity : AppCompatActivity() {

    private lateinit var scanBtn: MaterialButton
    private lateinit var imageApp: ImageView
    private lateinit var productApp: TextView

    companion object {
        private const val CAMERA_REQUEST_CODE = 100

        private const val TAG = "MAIN_TAG"
    }

    private lateinit var cameraPermission: Array<String>

    private var imageUri:Uri? = null

    private var barcodeScannerOptions: BarcodeScannerOptions? = null
    private var barcodeScanner: BarcodeScanner? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        scanBtn = findViewById(R.id.scanBtn)
        imageApp = findViewById(R.id.imageApp)
        productApp = findViewById(R.id.productApp)

        cameraPermission = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)

        barcodeScannerOptions = BarcodeScannerOptions.Builder()
            .setBarcodeFormats(Barcode.FORMAT_ALL_FORMATS)
            .build()

        barcodeScanner = BarcodeScanning.getClient(barcodeScannerOptions!!)


        scanBtn.setOnClickListener {

            if (checkCameraPermissions()){

                pickImageCamera()
            }
            else{

                requestCameraPermission()
            }

        }


        productApp.setOnClickListener {
            if (imageUri == null){
                showToast("Pick image first")
            }
            else {

                detectResultFromImage()
            }

        }

    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun pickImageCamera() {
        val contentValues = ContentValues()
        contentValues.put(MediaStore.Images.Media.TITLE, "sample Image")
        contentValues.put(MediaStore.Images.Media.DESCRIPTION, "sample Image Description")

        imageUri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        cameraActivityResultLauncher.launch(intent)
    }

    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(this, cameraPermission, CAMERA_REQUEST_CODE)
    }

    private fun checkCameraPermissions(): Boolean {

        val resultCamera = (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED)

        return resultCamera
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        when (requestCode) {
            CAMERA_REQUEST_CODE -> {

                if (grantResults.isNotEmpty()) {

                    val cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED

                    if (cameraAccepted) {

                        pickImageCamera()
                    }
                    else {
                        showToast("Camera permissions are required")
                    }
                }
            }
        }
    }

    private val cameraActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){result ->
        if (result.resultCode == Activity.RESULT_OK){

            val data = result.data

            Log.d(TAG, "cameraActivityResultLauncher: imageUri: $imageUri")

            imageApp.setImageURI(imageUri)
        }
    }

    private fun detectResultFromImage(){
        Log.d(TAG, "detectResultFromImage: ")

        try {

            val inputImage = InputImage.fromFilePath(this, imageUri!!)

            val barcodeResult = barcodeScanner!!.process(inputImage)
                .addOnSuccessListener {barcodes ->

                    extractBarcodeQrCodeInfo(barcodes)
                }
                .addOnFailureListener {e ->
                    Log.e(TAG, "detectResultFromImage: ", e)
                    showToast("Failed scanning due to ${e.message}")
                }
        }
        catch (e: Exception){
            Log.e(TAG, "detectResultFromImage: ", e)
            showToast("Failed scanning due to ${e.message}")
        }
    }

    private fun extractBarcodeQrCodeInfo(barcodes: List<Barcode>) {

        for (barcode in barcodes){
            val bound = barcode.boundingBox
            val corners = barcode.cornerPoints

            val rawValue = barcode.rawValue
            Log.d(TAG, "extractBarcodeQrCodeInfo: rawValue: $rawValue")

            when(barcode.valueType){
                Barcode.TYPE_WIFI -> {

                    val typeWifi = barcode.wifi

                    val ssid = "${typeWifi?.ssid}"
                    val password = "${typeWifi?.password}"
                    var encryptionType = "${typeWifi?.encryptionType}"

                    when (encryptionType) {
                        "1" -> {
                            encryptionType = "OPEN"
                        }
                        "2" -> {
                            encryptionType = "WPA"
                        }
                        "3" -> {
                            encryptionType = "WEP"
                        }
                    }

                    Log.d(TAG, "extractBarcodeQrCodeInfo: TYPE_WIFI")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: ssid: $ssid")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: password: $password")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: encryptionType: $encryptionType")

                    productApp.text = "TYPE_WIFI \nssid: $ssid \npassword: $password \nencryptionType: $encryptionType \n\n"
            }
                Barcode.TYPE_URL -> {
                    val typeUrl = barcode.url

                    val title = "${typeUrl?.title}"
                    val url = "${typeUrl?.url}"

                    Log.d(TAG, "extractBarcodeQrCodeInfo: TYPE_URL")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: title: $title")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: url: $url")

                    productApp.text = "TYPE_URL \ntitle: $title \nurl: $url \n\nrawValue: $rawValue"
                }
                Barcode.TYPE_EMAIL -> {

                    val typeEmail = barcode.email

                    val address= "${typeEmail?.address}"
                    val body= "${typeEmail?.body}"
                    val subject= "${typeEmail?.subject}"

                    Log.d(TAG, "extractBarcodeQrCodeInfo: TYPE_EMAIL")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: address: $address")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: body: $body")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: subject: $subject")

                    productApp.text = "TYPE_EMAIL \nEmail: $address \nbody: $body \nsubject: $subject \n\nrawValue: $rawValue"
                }
                Barcode.TYPE_CONTACT_INFO -> {

                    val typeContact = barcode.contactInfo

                    val title = "${typeContact?.title}"
                    val organization = "${typeContact?.organization}"
                    val name = "${typeContact?.name?.first} ${typeContact?.name?.last}"
                    val phone = "${typeContact?.name?.first} ${typeContact?.phones?.get(0)?.number}"

                    Log.d(TAG, "extractBarcodeQrCodeInfo: TYPE_CONTACT_INFO")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: title: $title")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: organization: $organization")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: name: $name")
                    Log.d(TAG, "extractBarcodeQrCodeInfo: phone: $phone")

                    productApp.text = "TYPE_CONTACT_INFO \ntitle: $title \norganization: $organization \nname: $name \nphone: $phone \n\nrawValue: $rawValue"
                }
                else -> {
                    productApp.text = "rawValue: $rawValue"
                }
        }
    }
}}