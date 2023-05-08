package com.wordbase.presentation.gameplay
import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.text.InputType
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputConnection
import android.view.inputmethod.InputMethodManager
import com.wordbase.R
import java.util.*

class SpellView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private lateinit var textToSpeech: TextToSpeech

    private val backgroundImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.field)
    private val backgroundPaint = Paint()

    private val originalTextToSpeechButtonImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.audio)
    private val textToSpeechButtonImage: Bitmap = Bitmap.createScaledBitmap(originalTextToSpeechButtonImage, originalTextToSpeechButtonImage.width / 3, originalTextToSpeechButtonImage.height / 3, true)
    private val textToSpeechButtonPaint = Paint()

    private val keyWidth = 150
    private val keyHeight = keyWidth
    private val keySpacing = 10

    private val keyboardLayout = arrayOf(
        arrayOf("Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"),
        arrayOf("A", "S", "D", "F", "G", "H", "J", "K", "L"),
        arrayOf("Z", "X", "C", "V", "B", "N", "M")
    )

    private val keyboardPaint = Paint().apply {
        color = Color.WHITE
    }

    private val keyPaint = Paint().apply {
        color = Color.BLACK
        textSize = 70f
    }

    private val underlinePaint = Paint().apply {
        color = Color.WHITE
        strokeWidth = 10f
    }
    private val textPaint = Paint().apply {
        color = Color.WHITE
        textSize = 180f
        typeface = Typeface.DEFAULT_BOLD
    }

    private val gameLoopHandler = Handler(Looper.getMainLooper())
    private val gameLoopRunnable = object : Runnable {
        override fun run() {
            updateGameState()
            invalidate()
            gameLoopHandler.postDelayed(this, 1000 / 60) // 60 FPS
        }
    }

    private val wordToSpell = "EXAMPLE" // Replace this with the word from viewmodel
    private val typedLetters = CharArray(wordToSpell.length) { ' ' }
    private val buttonRect = RectF()
    private var currentLetterIndex = 0

    init {
        // Initialize game objects, state, and animations
//        typedLetters[0] = wordToSpell[0]
//        typedLetters[typedLetters.size - 1] = wordToSpell[wordToSpell.length - 1]

        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.language = Locale.getDefault()
            }
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        gameLoopHandler.post(gameLoopRunnable)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        gameLoopHandler.removeCallbacks(gameLoopRunnable)
        textToSpeech.stop()
        textToSpeech.shutdown()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // draw background
        val scaledBackground: Bitmap = Bitmap.createScaledBitmap(backgroundImage, width, height, true)
        canvas.drawBitmap(scaledBackground, 0f, 0f, backgroundPaint)


        // Draw underlines
        val underlineY = height / 2f - 50
        val underlineSpacing = width / (wordToSpell.length + 1)
        for (i in wordToSpell.indices) {
            val startX = underlineSpacing * (i + 1).toFloat()
            val stopX = startX + underlineSpacing / 2f
            canvas.drawLine(startX, underlineY, stopX, underlineY, underlinePaint)
        }

        // Draw typed letters
        val textY = underlineY - 20
        for (i in typedLetters.indices) {
            val x = underlineSpacing * (i + 1).toFloat() + underlineSpacing / 20
            canvas.drawText(typedLetters[i].toString(), x, textY, textPaint)
        }


        // Draw text to speech button
        val buttonWidth = textToSpeechButtonImage.width.toFloat()
        val buttonHeight = textToSpeechButtonImage.height.toFloat()
        val buttonLeft = 60f
        val buttonTop = 40f
        buttonRect.set(buttonLeft, buttonTop, buttonLeft + buttonWidth, buttonTop + buttonHeight)
        canvas.drawBitmap(textToSpeechButtonImage, buttonLeft, buttonTop, textToSpeechButtonPaint)

        // Draw keyboard
        val topBias = height / 2f
        val leftBias = width / 8f
        for (i in keyboardLayout.indices) {
            for (j2 in keyboardLayout[i].indices) {
                var j = j2.toFloat()
                if (i == 2) {
                    j += 1
                }
                if (i == 1) {
                    j += .5f
                }
                val key = keyboardLayout[i][j2]
                val keyLeft = j * (keyWidth + keySpacing) + leftBias
                val keyTop = i * (keyHeight + keySpacing) + topBias
                val keyRight = keyLeft + keyWidth
                val keyBottom = keyTop + keyHeight
                val keyRect = RectF(keyLeft.toFloat(), keyTop.toFloat(), keyRight.toFloat(), keyBottom.toFloat())
                canvas.drawRect(keyRect, keyboardPaint)
                canvas.drawText(key, keyRect.centerX()-keyPaint.textSize / 3, keyRect.centerY() + keyPaint.textSize / 2, keyPaint)
            }
        }

        // Draw delete button
        val deleteButtonWidth = 300f
        val deleteButtonHeight = 150f
        val deleteButtonLeft = width - deleteButtonWidth - 60f
        val deleteButtonTop = topBias
        val deleteButtonRect = RectF(deleteButtonLeft, deleteButtonTop, deleteButtonLeft + deleteButtonWidth, deleteButtonTop + deleteButtonHeight)
        canvas.drawRect(deleteButtonRect, keyboardPaint)
        canvas.drawText("Delete", deleteButtonRect.centerX()-keyPaint.textSize - (keyPaint.textSize / 3), deleteButtonRect.centerY() + keyPaint.textSize / 2, keyPaint)

        // Draw submit button
        val submitButtonWidth = 300f
        val submitButtonHeight = 150f
        val submitButtonLeft = width - submitButtonWidth - 60f
        val submitButtonTop = topBias + deleteButtonHeight + 10f
        val submitButtonRect = RectF(submitButtonLeft, submitButtonTop, submitButtonLeft + submitButtonWidth, submitButtonTop + submitButtonHeight)
        canvas.drawRect(submitButtonRect, keyboardPaint)
        canvas.drawText("Submit", submitButtonRect.centerX()-keyPaint.textSize - (keyPaint.textSize / 3), submitButtonRect.centerY() + keyPaint.textSize / 2, keyPaint)
    }

    @SuppressLint("ServiceCast")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                if (buttonRect.contains(event.x, event.y)) {
                    playWord()
                    return true
                }
                val topBias = height / 2f
                val leftBias = width / 8f
                for (i in keyboardLayout.indices) {
                    for (j2 in keyboardLayout[i].indices) {
                        var j = j2.toFloat()
                        if (i == 2) {
                            j += 1
                        }
                        if (i == 1) {
                            j += .5f
                        }
                        val key = keyboardLayout[i][j2]
                        val keyLeft = j * (keyWidth + keySpacing) + leftBias
                        val keyTop = i * (keyHeight + keySpacing) + topBias
                        val keyRight = keyLeft + keyWidth
                        val keyBottom = keyTop + keyHeight
                        val keyRect = RectF(keyLeft.toFloat(), keyTop.toFloat(), keyRight.toFloat(), keyBottom.toFloat())
                        if (keyRect.contains(event.x, event.y)) {
                            if (currentLetterIndex < wordToSpell.length) {
                                typedLetters[currentLetterIndex] = key[0]
                                currentLetterIndex++
                            }
                            return true
                        }
                    }
                }
                val deleteButtonWidth = 300f
                val deleteButtonHeight = 150f
                val deleteButtonLeft = width - deleteButtonWidth - 60f
                val deleteButtonTop = topBias
                val deleteButtonRect = RectF(deleteButtonLeft, deleteButtonTop, deleteButtonLeft + deleteButtonWidth, deleteButtonTop + deleteButtonHeight)
                if (deleteButtonRect.contains(event.x, event.y)) {
                    if (currentLetterIndex != 0) {
                        currentLetterIndex--
                        typedLetters[currentLetterIndex] = ' '
                    }
                    return true
                }
                val submitButtonWidth = 300f
                val submitButtonHeight = 150f
                val submitButtonLeft = width - submitButtonWidth - 60f
                val submitButtonTop = topBias + deleteButtonHeight + 10f
                val submitButtonRect = RectF(submitButtonLeft, submitButtonTop, submitButtonLeft + submitButtonWidth, submitButtonTop + submitButtonHeight)
                if (submitButtonRect.contains(event.x, event.y)) {
                    println("Submit button pressed")
                    return true
                }
            }

        }
        return true
    }

    private fun playWord() {
        if (textToSpeech.isSpeaking) {
            textToSpeech.stop()
        }
        textToSpeech.speak(wordToSpell, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onCheckIsTextEditor(): Boolean {
        return true
    }

//    override fun onCreateInputConnection(outAttrs: EditorInfo): InputConnection {
//        outAttrs.actionLabel = null
//        outAttrs.inputType = InputType.TYPE_CLASS_TEXT
//        outAttrs.imeOptions = EditorInfo.IME_ACTION_DONE
//        return SpellInputConnection(this)
//    }


    private fun updateGameState() {
        // Update game state based on user interactions and game events
        // ...
    }

}

