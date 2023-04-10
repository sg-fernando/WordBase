package com.wordbase.presentation.gameplay
import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.os.Handler
import android.os.Looper
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.wordbase.R

class GameView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    // Game objects and state
    // ...

    private val backgroundImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.field)
    private val backgroundPaint = Paint()

    private val originalBallImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.baseball)

    private val ballImage: Bitmap = Bitmap.createScaledBitmap(originalBallImage, originalBallImage.width / 8, originalBallImage.height / 8, true)
    private val ballPaint = Paint()
    private var ballX = 0f
    private var ballY = 0f
    private var ballRadius = 0f
    private val ballGrowAnimator = ValueAnimator.ofFloat(0f, 1f).apply {
        addUpdateListener { animator ->
            val progress = animator.animatedValue as Float
            updateBallSizeAndColor(progress)
            invalidate()
        }
    }

    private fun updateBallSizeAndColor(progress: Float) {
        // Update the ball radius
        val minRadius = ballImage.width / 4f
        val maxRadius = ballImage.width / 2f
        ballRadius = minRadius + (maxRadius - minRadius) * progress

        // Update the ball color
        val color = Color.RED
//        val yellow = Color.YELLOW
//        val green = Color.GREEN
//        val startColor = if (progress < 0.5f) red else yellow
//        val endColor = if (progress < 0.5f) yellow else green
//        val colorProgress = if (progress < 0.5f) progress * 2 else (progress - 0.5f) * 2
//        val color = interpolateColor(startColor, endColor, colorProgress)

        // Set the radial gradient shader for the ball
        ballPaint.shader = RadialGradient(ballX + ballRadius, ballY + ballRadius, ballRadius, color, color, Shader.TileMode.CLAMP)
    }


    fun startBallAnimation(durationSeconds: Int) {
        ballGrowAnimator.apply {
            duration = (durationSeconds * 1000).toLong()
            start()
        }
    }


    private val originalBatImage: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.bat)
    private val batImage: Bitmap = Bitmap.createScaledBitmap(originalBatImage, originalBatImage.width / 2, originalBatImage.height / 2, true)
    private val batPaint = Paint()
    private val batMatrix = Matrix()
    private var batRotationAngle = 0f;
    private var batX = 450f
    private var batY = 900f

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
        // ...
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        // Set the initial ball position to the center of the screen
        ballX = w / 2f - ballImage.width / 2f
        ballY = h / 2f - ballImage.height / 2f
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
        val scaledBackground = Bitmap.createScaledBitmap(backgroundImage, width, height, true)
        canvas.drawBitmap(scaledBackground, 0f, 0f, backgroundPaint)

        // Draw the ball
        canvas.drawBitmap(ballImage, ballX, ballY, ballPaint)

        // Draw the bat
        batMatrix.reset()
        batMatrix.setRotate(batRotationAngle, batX, batY)
        batMatrix.postTranslate(batX, batY)
        canvas.drawBitmap(batImage, batMatrix, batPaint)




    }

//    override fun onTouchEvent(event: MotionEvent): Boolean {
//        // Handle touch events for game interaction
//        // ...
//
//        return super.onTouchEvent(event)
//    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                // Update the ball's position
//                batX = event.x - batImage.width / 2f
                batY = event.y - batImage.height / 2f

                // Update the bat's rotation angle
                batRotationAngle = -180f * (1f - event.y / height)

                startBallAnimation(4)

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

    // Additional methods for managing game objects, animations, and game logic
    // ...
}

