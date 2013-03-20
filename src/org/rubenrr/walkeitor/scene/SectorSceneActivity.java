package org.rubenrr.walkeitor.scene;

import android.util.Log;
import android.widget.Toast;
import org.andengine.engine.camera.BoundCamera;
import org.andengine.engine.camera.ZoomCamera;
import org.andengine.engine.handler.IUpdateHandler;
import org.andengine.engine.handler.timer.ITimerCallback;
import org.andengine.engine.handler.timer.TimerHandler;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.Entity;
import org.andengine.entity.IEntity;
import org.andengine.entity.modifier.IEntityModifier;
import org.andengine.entity.modifier.MoveModifier;
import org.andengine.entity.primitive.Rectangle;
import org.andengine.entity.scene.IOnSceneTouchListener;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.extension.tmx.*;
import org.andengine.extension.tmx.TMXLoader.ITMXTilePropertiesListener;
import org.andengine.extension.tmx.util.exception.TMXLoadException;
import org.andengine.input.touch.TouchEvent;
import org.andengine.input.touch.detector.PinchZoomDetector;
import org.andengine.input.touch.detector.ScrollDetector;
import org.andengine.input.touch.detector.SurfaceScrollDetector;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.Constants;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;
import org.andengine.util.modifier.IModifier;

import java.io.IOException;
import java.io.InputStream;

/**
 * (c) 2013 Ruben Rubio Rey
 *
 * Main scene of the game. Also used as base for testing and develop
 *
 *
 * @author Ruben Rubio
 * @since 21:22:50 - 20.03.2013
 */
public class SectorSceneActivity extends SimpleBaseGameActivity implements IOnSceneTouchListener, ScrollDetector.IScrollDetectorListener, PinchZoomDetector.IPinchZoomDetectorListener  {
	// ===========================================================
	// Constants
	// ===========================================================

	private static final int CAMERA_WIDTH = 480;
	private static final int CAMERA_HEIGHT = 320;

	// ===========================================================
	// Fields
	// ===========================================================

	private BitmapTextureAtlas mBitmapTextureAtlas;
    private TMXTiledMap mTMXTiledMap;

    private Scene mScene;

    //Pinch and zoom
    private ZoomCamera mZoomCamera;
    private SurfaceScrollDetector mScrollDetector;
    private PinchZoomDetector mPinchZoomDetector;
    private float mPinchZoomStartedCameraZoomFactor;

	@Override
	public EngineOptions onCreateEngineOptions() {

		this.mZoomCamera = new ZoomCamera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);
		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), this.mZoomCamera);
	}

	@Override
	public void onCreateResources() {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");

		this.mBitmapTextureAtlas = new BitmapTextureAtlas(this.getTextureManager(), 72, 128, TextureOptions.DEFAULT);

		this.mBitmapTextureAtlas.load();
	}

	@Override
	public Scene onCreateScene() {

        Log.d("GameSample", "Starting generating scene");

		this.mEngine.registerUpdateHandler(new FPSLogger());

		this.mScene = new Scene();

        try {
            final TMXLoader tmxLoader = new TMXLoader(this.getAssets(), this.mEngine.getTextureManager(), TextureOptions.BILINEAR_PREMULTIPLYALPHA, this.getVertexBufferObjectManager());
            this.mTMXTiledMap = tmxLoader.loadFromAsset("tmx/desert.tmx");
        } catch (final TMXLoadException e) {
            Debug.e(e);
        }

		final TMXLayer tmxLayer = this.mTMXTiledMap.getTMXLayers().get(0);
        this.mScene.attachChild(tmxLayer);

		// avoid camera move away from bounds
        this.mZoomCamera.setBounds(0, 0, tmxLayer.getHeight(), tmxLayer.getWidth());
        this.mZoomCamera.setBoundsEnabled(true);

        // Configure the pich and the zoom
        this.mScrollDetector = new SurfaceScrollDetector(this);
        this.mPinchZoomDetector = new PinchZoomDetector(this);

        this.mScene.setOnSceneTouchListener(this);
        this.mScene.setTouchAreaBindingOnActionDownEnabled(true);


		return this.mScene;
	}

    @Override
    public void onScrollStarted(final ScrollDetector pScollDetector, final int pPointerID, final float pDistanceX, final float pDistanceY) {
        final float zoomFactor = this.mZoomCamera.getZoomFactor();
        this.mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
    }

    @Override
    public void onScroll(final ScrollDetector pScollDetector, final int pPointerID, final float pDistanceX, final float pDistanceY) {
        final float zoomFactor = this.mZoomCamera.getZoomFactor();
        this.mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
    }

    @Override
    public void onScrollFinished(final ScrollDetector pScollDetector, final int pPointerID, final float pDistanceX, final float pDistanceY) {
        final float zoomFactor = this.mZoomCamera.getZoomFactor();
        this.mZoomCamera.offsetCenter(-pDistanceX / zoomFactor, -pDistanceY / zoomFactor);
    }

    @Override
    public void onPinchZoomStarted(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent) {
        this.mPinchZoomStartedCameraZoomFactor = this.mZoomCamera.getZoomFactor();
    }

    @Override
    public void onPinchZoom(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
        this.mZoomCamera.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor * pZoomFactor);
    }

    @Override
    public void onPinchZoomFinished(final PinchZoomDetector pPinchZoomDetector, final TouchEvent pTouchEvent, final float pZoomFactor) {
        this.mZoomCamera.setZoomFactor(this.mPinchZoomStartedCameraZoomFactor * pZoomFactor);
    }


    @Override
    public boolean onSceneTouchEvent(final Scene pScene, final TouchEvent pSceneTouchEvent) {
        this.mPinchZoomDetector.onTouchEvent(pSceneTouchEvent);

        if(this.mPinchZoomDetector.isZooming()) {
            this.mScrollDetector.setEnabled(false);
        } else {
            if(pSceneTouchEvent.isActionDown()) {
                this.mScrollDetector.setEnabled(true);
            }
            this.mScrollDetector.onTouchEvent(pSceneTouchEvent);
        }

        return true;
    }


	// ===========================================================
	// Methods
	// ===========================================================



	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
