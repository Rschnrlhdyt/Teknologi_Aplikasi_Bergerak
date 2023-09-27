package com.d121201068.qriscode

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.google.zxing.MultiFormatWriter

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.edit_text)
        imageView = findViewById(R.id.image_view)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val number = editText.text.toString().toInt()
            val bitmap = generateQRCode(number)
            imageView.setImageBitmap(bitmap)
        }
    }

    private fun generateQRCode(number: Int): Bitmap {
        val hints = hashMapOf(
            EncodeHintType.CHARACTER_SET to "UTF-8",
            EncodeHintType.MARGIN to 0
        )

        val bitMatrix = MultiFormatWriter().encode(number.toString(), BarcodeFormat.QR_CODE, 400, 400, hints)

        val bitmap = Bitmap.createBitmap(bitMatrix.width, bitMatrix.height, Bitmap.Config.ARGB_8888)
        for (i in 0 until bitMatrix.width) {
            for (j in 0 until bitMatrix.height) {
                bitmap.setPixel(i, j, if (bitMatrix.get(i, j)) Color.BLACK else Color.WHITE)
            }
        }

        return bitmap
    }
}