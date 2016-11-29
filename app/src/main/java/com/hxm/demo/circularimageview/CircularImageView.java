package com.hxm.demo.circularimageview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.hxm.demo.R;

public class CircularImageView extends ImageView {

    private int borderWidth;
    private int canvasSize;
    private Bitmap image;
    private Paint paint;
    private Paint paintBorder;

    private boolean showRoundRect = false;
    private int roundRectPadding = 20;
    private int roundRectXRadius = 30;
    private int roundRectYRadius = 30;

    private boolean isShowGradient = false;
    private static final int[] mColors = new int[] {
            Color.TRANSPARENT, Color.TRANSPARENT, Color.WHITE
    };
    private static final float[] mPositions = new float[] {
            0, 0.6f, 1f
    };

    public CircularImageView(final Context context) {
        this(context, null);
    }

    public CircularImageView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.circularImageViewStyle);
    }

    public CircularImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        // init paint
        paint = new Paint();
        paint.setAntiAlias(true);

        paintBorder = new Paint();
        paintBorder.setAntiAlias(true);

        // load the styled attributes and set their properties
        TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.CircularImageView,
                defStyle,
                0);

        if(attributes.getBoolean(R.styleable.CircularImageView_border, true)) {
            int defaultBorderSize = (int) (4 * getContext().getResources().getDisplayMetrics().density + 0.5f);
            setBorderWidth(
                    attributes.getDimensionPixelOffset(R.styleable.CircularImageView_border_width,
                    defaultBorderSize));
            setBorderColor(attributes.getColor(R.styleable.CircularImageView_border_color, Color.WHITE));
        }

        if(attributes.getBoolean(R.styleable.CircularImageView_shadow, false)) {
            addShadow();
        }

        showRoundRect = attributes.getBoolean(R.styleable.CircularImageView_show_round_rect,false);
        roundRectPadding = attributes.getDimensionPixelOffset(R.styleable.CircularImageView_round_rect_padding,roundRectPadding);
        roundRectXRadius = attributes.getDimensionPixelOffset(R.styleable.CircularImageView_round_rect_x_radius,roundRectXRadius);
        roundRectYRadius = attributes.getDimensionPixelOffset(R.styleable.CircularImageView_round_rect_y_radius,roundRectYRadius);
        isShowGradient = attributes.getBoolean(R.styleable.CircularImageView_is_show_gradient,false);
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        this.requestLayout();
        this.invalidate();
    }

    public void setBorderColor(int borderColor) {
        if (paintBorder != null) {
            paintBorder.setColor(borderColor);
        }
        this.invalidate();
    }

    public void addShadow() {
        setLayerType(LAYER_TYPE_SOFTWARE, paintBorder);
        paintBorder.setShadowLayer(4.0f, 0.0f, 2.0f, Color.BLACK);
    }

    @Override
    public void onDraw(Canvas canvas) {
        // load the bitmap
        image = drawableToBitmap(getDrawable());

        // init shader
        if (image != null) {

            canvasSize = canvas.getWidth();
            if(canvas.getHeight()<canvasSize){
                canvasSize = canvas.getHeight();
            }

            BitmapShader shader = new BitmapShader(
                    Bitmap.createScaledBitmap(image, canvasSize, canvasSize, false),
                    Shader.TileMode.CLAMP,
                    Shader.TileMode.CLAMP);
            paint.setShader(shader);

            //draw Rect image
            if(showRoundRect){
                canvas.drawRoundRect(
                        new RectF(
                                roundRectPadding,
                                roundRectPadding,
                                canvasSize-roundRectPadding,
                                canvasSize-roundRectPadding),
                        roundRectXRadius,
                        roundRectYRadius,
                        paint);
            }else {
                // circleCenter is the x or y of the view's center
                // radius is the radius in pixels of the cirle to be drawn
                // paint contains the shader that will texture the shape
                int circleCenter = (canvasSize - (borderWidth * 2)) / 2;
            //draw border circle
            canvas.drawCircle(
                    circleCenter + borderWidth,
                    circleCenter + borderWidth,
                    ((canvasSize - (borderWidth * 2)) / 2) + borderWidth - 4.0f,
                    paintBorder);

            if(isShowGradient){
                RadialGradient radialGradient = new RadialGradient(
                        circleCenter,
                        circleCenter,
                        circleCenter,
                        mColors,
                        mPositions,
                        Shader.TileMode.CLAMP);

                ComposeShader composeShader = new ComposeShader(
                        shader,
                        radialGradient,
                        PorterDuff.Mode.SRC_OVER);

                paint.setShader(composeShader);
            }

            //draw circle image
            canvas.drawCircle(
                    circleCenter + borderWidth,
                    circleCenter + borderWidth,
                    ((canvasSize - (borderWidth * 2)) / 2) - 4.0f,
                    paint);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = measureWidth(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            // The parent has determined an exact size for the child.
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // The parent has not imposed any constraint on the child.
            result = canvasSize;
        }

        return result;
    }

    private int measureHeight(int measureSpecHeight) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpecHeight);
        int specSize = MeasureSpec.getSize(measureSpecHeight);

        if (specMode == MeasureSpec.EXACTLY) {
            // We were told how big to be
            result = specSize;
        } else if (specMode == MeasureSpec.AT_MOST) {
            // The child can be as large as it wants up to the specified size.
            result = specSize;
        } else {
            // Measure the text (beware: ascent is a negative number)
            result = canvasSize;
        }

        return (result + 2);
    }

    public Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable == null) {
            return null;
        } else if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }
}