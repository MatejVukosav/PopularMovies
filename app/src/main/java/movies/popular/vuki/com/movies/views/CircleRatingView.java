package movies.popular.vuki.com.movies.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.support.annotation.Nullable;
import android.text.BoringLayout;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import movies.popular.vuki.com.movies.R;

/**
 * Created by mvukosav
 */
public class CircleRatingView extends View {

    private float cX;
    private float cY;
    private Paint neutralPaint;
    private Paint lineDividerPaint;
    private Paint background;
    private TextPaint rankTextPaint;
    private boolean isInitialized;
    private float clipRadius;
    private float ratingWidth;
    private float circleRadius;
    private float sectionAngle;
    private int numOfSections = 10;
    private float grade;
    private float left;
    private float right;
    private float top;
    private float bottom;
    private BoringLayout rankBorignLayout;

    public CircleRatingView( Context context ) {
        super( context );
    }

    public CircleRatingView( Context context, @Nullable AttributeSet attrs ) {
        super( context, attrs );
        //xml
        TypedArray arr = context.obtainStyledAttributes( attrs, R.styleable.CircleRatingView );
        ratingWidth = arr.getFloat( R.styleable.CircleRatingView_ratingSize, 0 );
        arr.recycle();
    }

    public CircleRatingView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ) {
        super( context, attrs, defStyleAttr );
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade( float grade ) {
        this.grade = grade;
        invalidate();
    }

    @Override
    protected void onDraw( Canvas canvas ) {
        super.onDraw( canvas );
        if ( !isInitialized ) {
            isInitialized = true;
            init();

            Path clipPath = new Path();
            clipPath.addCircle( cX, cY, clipRadius, Path.Direction.CCW );
            canvas.clipPath( clipPath, Region.Op.DIFFERENCE );

            clipPath.reset();
            clipPath.addCircle( cX, cY, circleRadius, Path.Direction.CCW );
            canvas.clipPath( clipPath );
        }

        canvas.save();
        //canvas.translate( cX - rankBorignLayout.getWidth() / 2, cY + rankBorignLayout.getHeight() / 2 );
        canvas.translate( cX - rankBorignLayout.getWidth() / 2, cY - rankBorignLayout.getHeight() / 2 );
        rankBorignLayout.draw( canvas );
        canvas.restore();

        canvas.save();
        canvas.rotate( -90, cX, cY );
        canvas.drawPaint( background );
        drawSection( canvas, left, top, right, bottom,
                ( ( grade ) * sectionAngle ),
                ( numOfSections - grade ) * sectionAngle,
                true,
                neutralPaint );
        drawLines( canvas );
        canvas.restore();
    }

    private void drawSection( Canvas canvas, float left, float top, float right, float bottom, float startAngle, float sweepAngle, boolean useCenter, Paint paint ) {
        canvas.drawArc( left, top, right, bottom, startAngle, sweepAngle, useCenter, paint );
    }

    private void drawLines( Canvas canvas ) {
        for ( int i = 1; i <= numOfSections; i++ ) {
            PointF endLine = getPointOnCircle( cX, cY, circleRadius, ( ( i - 1 ) * sectionAngle ) + sectionAngle );
            canvas.drawLine( cX, cY, endLine.x, endLine.y, lineDividerPaint );
        }
    }

    private PointF getPointOnCircle( float cX, float cY, float radius, float angle ) {
        float x = (float) ( Math.cos( Math.toRadians( angle ) ) * radius + cX );
        float y = (float) ( Math.sin( Math.toRadians( angle ) ) * radius + cY );
        return new PointF( x, y );
    }

    private void init() {
        neutralPaint = new Paint();
        neutralPaint.setColor( Color.WHITE );
        neutralPaint.setAntiAlias( true );

        lineDividerPaint = new Paint();
        lineDividerPaint.setColor( Color.GRAY );
        lineDividerPaint.setStrokeWidth( 2 );
        lineDividerPaint.setAntiAlias( true );

        rankTextPaint = new TextPaint();
        rankTextPaint.setTextSize( getContext().getResources().getDimension( R.dimen.rank_font_size ) );
        rankTextPaint.setColor( Color.BLACK );
        rankTextPaint.setAntiAlias( true );

        sectionAngle = 360f / numOfSections;

        background = new Paint();
        background.setAntiAlias( true );

        float center = Math.min( getWidth() / 2, getHeight() / 2 );
        cX = center;
        cY = center;

        circleRadius = Math.min( getWidth() / 2, getHeight() / 2 );
        clipRadius = circleRadius - ratingWidth;
        clipRadius = clipRadius >= 0 ? clipRadius : 0;

        left = cX - circleRadius;
        top = cY - circleRadius;
        right = cX + circleRadius;
        bottom = cY + circleRadius;

        int[] colorList = new int[]{ Color.RED, Color.YELLOW, Color.GREEN };

        Shader shader = new SweepGradient( cX, cY, colorList, null );
        background.setShader( shader );

        BoringLayout.Metrics boringMetrics = BoringLayout.isBoring( String.valueOf( grade ), rankTextPaint );
        if ( boringMetrics != null ) {
            rankBorignLayout = BoringLayout.make( String.valueOf( grade ),
                    rankTextPaint,
                    (int) Math.ceil( BoringLayout.getDesiredWidth( String.valueOf( grade ), rankTextPaint ) ),
                    Layout.Alignment.ALIGN_NORMAL,
                    1.0f,
                    0,
                    boringMetrics,
                    true );
        }
    }
}
