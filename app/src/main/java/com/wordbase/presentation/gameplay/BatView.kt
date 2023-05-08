package com.wordbase.presentation.gameplay
import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.wordbase.R

class BatView(context: Context, attrs: AttributeSet?, private val onHit: (extra: Int) -> Unit) : View(context, attrs) {

    private val backgroundImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.field)
    private val backgroundPaint = Paint()

    private val normalBall: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.baseball)
    private val greenBall: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.green_baseball)
    private val yellowBall: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.yellow_baseball)
    private val redBall: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.red_baseball)
    private var originalBallImage: Bitmap = normalBall
    private var sizeDivider = 100
    private val ballImage: Bitmap = Bitmap.createScaledBitmap(originalBallImage, originalBallImage.width / sizeDivider, originalBallImage.height / sizeDivider, true)
    private val ballPaint = Paint()
    private var ballX = 0f
    private var ballY = 0f

    private var goodHitPopup = false
    private val popupTextPaint = Paint().apply {
        color = Color.WHITE
        textSize = 200f
        textAlign = Paint.Align.CENTER
        typeface = Typeface.DEFAULT_BOLD
    }

    private var ballAnimator: ValueAnimator? = null
    private var ballThrown: Boolean = false
    private var randomThrowTime: Long = 0L
    private var randomThrowDuration: Long = 0L

    private fun scheduleRandomThrow() {
        if (!ballThrown) {
            val minDelay = 5000L // 5 second
            val maxDelay = 9000L // 9 seconds
            randomThrowTime = (minDelay..maxDelay).random()
            val minDuration = 2000L
            val maxDuration = 3000L
            randomThrowDuration = (minDuration..maxDuration).random()

            ballAnimator?.cancel()
            ballThrownAnimation(randomThrowTime, randomThrowDuration)
        }
    }


    private fun ballThrownAnimation(throwDelay: Long, throwDuration: Long) {
        // Cancel any existing animator
        ballAnimator?.cancel()

        // Create a new animator to animate the ball size
        ballAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
            duration = throwDuration
            // Add a delay before starting the animation
            startDelay = throwDelay
            addUpdateListener { valueAnimator ->
                // Update the sizeDivider property with the animated value
                val progress = valueAnimator.animatedValue as Float
                sizeDivider = (100 * (1 - progress)).toInt()

                originalBallImage = when {
                    progress < 0.5 -> redBall
                    progress < 0.75 -> yellowBall
                    progress < 0.93 -> greenBall
                    progress < 0.96 -> yellowBall
                    else -> redBall
                }
                // Redraw the view
                invalidate()
            }

            addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator) {
                    // Animation started
                    sizeDivider = 100
                    originalBallImage = normalBall
                    ballThrown = true
                }

                override fun onAnimationEnd(animation: Animator) {
                    // Animation ended, reset the sizeDivider property
                    sizeDivider = 100
                    originalBallImage = normalBall
                    ballThrown = false
                    scheduleRandomThrow()
                }

                override fun onAnimationCancel(animation: Animator) {
                    // Animation cancelled
                    scheduleRandomThrow()
                }

                override fun onAnimationRepeat(animation: Animator) {
                    // Animation repeated
                }
            })
        }

        // Start the animator
        ballAnimator?.start()
    }

    private val originalBatImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bat)
    private val batImage: Bitmap = Bitmap.createScaledBitmap(originalBatImage, originalBatImage.width / 2, originalBatImage.height / 2, true)
    private val batPaint = Paint()
    private val batMatrix = Matrix()
    private var batRotationAngle = 0f;
    private var batX = 450f
    private var batY = 900f

    private val originalPitcherImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.pitcher)
    private val pitcherImage: Bitmap = Bitmap.createScaledBitmap(originalPitcherImage, originalPitcherImage.width / 3, originalPitcherImage.height / 3, true)
    private val pitcherPaint = Paint()
    private var pitcherX = 0f
    private var pitcherY = 0f

    private val gameLoopHandler = Handler(Looper.getMainLooper())
    private val gameLoopRunnable = object : Runnable {
        override fun run() {
            updateGameState()
            invalidate()
            gameLoopHandler.postDelayed(this, 1000 / 60) // 60 FPS
        }
    }

    init {
        // Initialize game objects, state, and animations
        scheduleRandomThrow()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Set the initial ball position to the center of the screen
        ballX = w / 2f - ballImage.width / 2f
        ballY = h / 2f - ballImage.height / 2f
        pitcherX = w / 2f - pitcherImage.width / 2f
        pitcherY = h / 3f + pitcherImage.height / 3f
        batX = w / 5f
        batY = h - (h / 10f)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        gameLoopHandler.post(gameLoopRunnable)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        gameLoopHandler.removeCallbacks(gameLoopRunnable)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // draw background
        val scaledBackground: Bitmap = Bitmap.createScaledBitmap(backgroundImage, width, height, true)
        canvas.drawBitmap(scaledBackground, 0f, 0f, backgroundPaint)

        // Draw the pitcher
        canvas.drawBitmap(pitcherImage, pitcherX, pitcherY, pitcherPaint)

        // Draw the ball
        if (sizeDivider != 0) {
            val ballImage = Bitmap.createScaledBitmap(
                originalBallImage,
                originalBallImage.width / sizeDivider,
                originalBallImage.height / sizeDivider,
                true
            )
            ballX = width / 2f - ballImage.width / 2f
            ballY = height / 2f - ballImage.height / 2f
            canvas.drawBitmap(ballImage, ballX, ballY, ballPaint)
        }

        // Draw the bat
        batMatrix.reset()
        batMatrix.setRotate(batRotationAngle, batX, batY)
        batMatrix.postTranslate(batX, batY)
        canvas.drawBitmap(batImage, batMatrix, batPaint)

        if (goodHitPopup) {
            val popupText = "Good hit!"
            val centerX = width / 2f
            val centerY = height / 2f
            canvas.drawText(popupText, centerX, centerY, popupTextPaint)
        }

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                // Update the ball's position
                // batX = event.x - batImage.width / 2f
                batY = event.y - batImage.height / 2f

                // Update the bat's rotation angle
                batRotationAngle = -180f * (1f - event.y / height)

                if (sizeDivider != 0) {
                    // Check if the user's Y position is within the middle 1/5 of the screen
                    val touchY = event.y
                    val screenHeight = height.toFloat()
                    val minY = screenHeight * 2 / 7
                    val maxY = screenHeight * 3 / 7

                    if (touchY >= minY && touchY <= maxY) {
                        if (originalBallImage == greenBall || originalBallImage == yellowBall) {
                            // Do something when the user hits the ball with green or yellow color
                            var extra = 0
                            if (originalBallImage == greenBall) {
                                extra = 2
                            } else {
                                extra = 1
                            }
                            println("HIT")
                            goodHitPopup = true
                            invalidate()

                            // Hide the popup and launch another view after a short delay
                            Handler(Looper.getMainLooper()).postDelayed({
                                goodHitPopup = false
                                invalidate()
                                onHit(extra)
                            }, 2000) // 1-second delay

                        } else {
                            // Notify the ViewModel that it's a strike
                        }
                    }
                }


                // Redraw the view
                invalidate()
            }

        }
        return true
    }

    private fun updateGameState() {
        // Update game state based on user interactions and game events
        // ...
    }

}

