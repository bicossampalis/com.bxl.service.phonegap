package com.bxl.service.phonegap;

import jpos.JposException;
import jpos.POSPrinter;
import jpos.config.JposEntry;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Set;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

//import com.bxl.jsbridge.JSBridge;

import com.bxl.config.editor.BXLConfigLoader;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

public class BXLService extends CordovaPlugin {

    private static final String TAG = BXLService.class.getSimpleName();
    private final boolean DEBUG = true;

    private final String ACTION_EXECUTE_PRINTER = "executePrinter";

    private final String METHOD_GET_CLAIMED = "getClaimed";
    private final String METHOD_GET_DEVICE_ENABLED = "getDeviceEnabled";
    private final String METHOD_SET_DEVICE_ENABLED = "setDeviceEnabled";
    private final String METHOD_GET_OUTPUT_ID = "getOutputID";
    private final String METHOD_GET_POWER_STATE = "getPowerState";
    private final String METHOD_GET_STATE = "getState";
    private final String METHOD_GET_DEVICE_SERVICE_DESCRIPTION = "getDeviceServiceDescription";
    private final String METHOD_GET_DEVICE_SERVICE_VERSION = "getDeviceServiceVersion";
    private final String METHOD_GET_PHYSICAL_DEVICE_DESCRIPTION = "getPhysicalDeviceDescription";
    private final String METHOD_GET_PHYSICAL_DEVICE_NAME = "getPhysicalDeviceName";
    private final String METHOD_GET_CAP_REC_NEAR_END_SENSOR = "getCapRecNearEndSensor";
    private final String METHOD_GET_CAP_REC_PAPERCUT = "getCapRecPapercut";
    private final String METHOD_GET_CAP_REC_MARK_FEED = "getCapRecMarkFeed";
    private final String METHOD_GET_ASYNC_MODE = "getAsyncMode";
    private final String METHOD_SET_ASYNC_MODE = "setAsyncMode";
    private final String METHOD_GET_CHARACTER_SET = "getCharacterSet";
    private final String METHOD_SET_CHARACTER_SET = "setCharacterSet";
    private final String METHOD_GET_CHARACTER_SET_LIST = "getCharacterSetList";
    private final String METHOD_GET_COVER_OPEN = "getCoverOpen";
    private final String METHOD_GET_ERROR_LEVEL = "getErrorLevel";
    private final String METHOD_GET_ERROR_STATION = "getErrorStation";
    private final String METHOD_GET_ERROR_STRING = "getErrorString";
    private final String METHOD_GET_FONT_TYPEFACE_LIST = "FontTypefaceList";
    private final String METHOD_GET_PAGE_MODE_AREA = "getPageModeArea";
    private final String METHOD_GET_PAGE_MODE_HORIZONTAL_POSITION = "getPageModeHorizontalPosition";
    private final String METHOD_SET_PAGE_MODE_HORIZONTAL_POSITION = "setPageModeHorizontalPosition";
    private final String METHOD_GET_PAGE_MODE_PRINT_AREA = "getPageModePrintArea";
    private final String METHOD_SET_PAGE_MODE_PRINT_AREA = "setPageModePrintArea";
    private final String METHOD_GET_PAGE_MODE_PRINT_DIRECTION = "getPageModePrintDirection";
    private final String METHOD_SET_PAGE_MODE_PRINT_DIRECTION = "setPageModePrintDirection";
    private final String METHOD_GET_PAGE_MODE_VERTICAL_POSITION = "getPageModeVerticalPosition";
    private final String METHOD_SET_PAGE_MODE_VERTICAL_POSITION = "setPageModeVerticalPosition";
    private final String METHOD_GET_REC_EMPTY = "getRecEmpty";
    private final String METHOD_GET_REC_NEAR_END = "getRecNearEnd";

    private final String METHOD_OPEN = "open";
    private final String METHOD_CLOSE = "close";
    private final String METHOD_CLAIM = "claim";
    private final String METHOD_RELEASE = "release";
    private final String METHOD_CHECK_HEALTH = "checkHealth";
    private final String METHOD_CLEAR_PRINT_AREA = "clearPrintArea";
    private final String METHOD_CUT_PAPER = "cutPaper";
    private final String METHOD_MARK_FFED = "markFeed";
    private final String METHOD_PAGE_MODE_PRINT = "pageModePrint";
    private final String METHOD_PRINT_BAR_CODE = "printBarCode";
    private final String METHOD_PRINT_BITMAP = "printBitmap";
    private final String METHOD_PRINT_BITMAP_WITH_URL = "printBitmapWithURL";
    private final String METHOD_PRINT_BITMAP_WITH_BASE64 = "printBitmapWithBase64";
    private final String METHOD_PRINT_NORMAL = "printNormal";
    private final String METHOD_TRANSACTION_PRINT = "transactionPrint";
    private final String METHOD_ADD_ENTRY = "addEntry";

    private static POSPrinter posPrinter;

    BluetoothAdapter mBTAdapter;
    Set<BluetoothDevice> pairedDevices;

    private static BXLConfigLoader bxlConfigLoader = null;

    private String mAddress = "";

    public static void setContext(Context context) {

        if (bxlConfigLoader == null) {
            bxlConfigLoader = new BXLConfigLoader(context);
        }

        posPrinter = new POSPrinter(context);
    }

    @Override
    public void pluginInitialize() {
//		if (allowedNavigations == null) {
//			allowedNavigations = new Whitelist();
//			allowedIntents = new Whitelist();
//			allowedRequests = new Whitelist();
//			new CustomConfigXmlParser().parse(webView.getContext());
//		}
        this.setContext(webView.getContext());
    }


    /**
     * image url을 받아서 bitmap을 생성하고 리턴합니다
     *
     * @param url
     *            얻고자 하는 image url
     * @return 생성된 bitmap
     */

    /**
     * image url을 받아서 bitmap을 생성하고 리턴합니다
     *
     * @param url
     *            얻고자 하는 image url
     * @return 생성된 bitmap
     */
    private Bitmap getBitmap(String url) {
        URL imgUrl = null;
        HttpURLConnection connection = null;
        InputStream is = null;

        Bitmap retBitmap = null;

        try {
            imgUrl = new URL(url);
            connection = (HttpURLConnection) imgUrl.openConnection();
            connection.setDoInput(true); // url로 input받는 flag 허용
            connection.connect(); // 연결
            is = connection.getInputStream(); // get inputstream
            retBitmap = BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            return retBitmap;
        }
    }



    /**
     * image url을 받아서 bitmap을 생성하고 리턴합니다
     *
     * @param url
     *            얻고자 하는 image url
     * @return 생성된 bitmap
     */

    /**
     * base 64로 encoding 된 image data을 받아서 bitmap을 생성하고 리턴합니다
     *
     * @param base64EncodedData
     *            얻고자 하는 image url
     * @return 생성된 bitmap
     */
    private Bitmap getDecodedBitmap(String base64EncodedData) {

        byte[] imageAtBytes = Base64.decode(base64EncodedData.getBytes(), Base64.DEFAULT);
        return BitmapFactory.decodeByteArray(imageAtBytes, 0, imageAtBytes.length);
    }



    /**
     * Executes the request.
     *
     * This method is called from the WebView thread. To do a non-trivial amount
     * of work, use: cordova.getThreadPool().execute(runnable);
     *
     * To run on the UI thread, use:
     * cordova.getActivity().runOnUiThread(runnable);
     *
     * @param action
     *            The action to execute.
     * @param args
     *            The exec() arguments.
     * @param callbackContext
     *            The callback context used when calling back into JavaScript.
     * @return Whether the action was valid.
     */
    @Override
    public boolean execute(final String action, final JSONArray args,
                           final CallbackContext callbackContext) throws JSONException {
        if (DEBUG) {
            Log.d(TAG, "execute(" + action + ", " + args + ", "
                    + callbackContext + ")");
        }

        if (!action.equals(ACTION_EXECUTE_PRINTER)) {
            callbackContext.error("Action is not matched");
            return false;
        }

        String method = args.getString(0);
        try {
            if (method.equals(METHOD_GET_CLAIMED)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getClaimed()));
            } else if (method.equals(METHOD_GET_DEVICE_ENABLED)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getDeviceEnabled()));
            } else if (method.equals(METHOD_SET_DEVICE_ENABLED)) {
                posPrinter.setDeviceEnabled(args.getBoolean(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_OUTPUT_ID)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getOutputID()));
            } else if (method.equals(METHOD_GET_POWER_STATE)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getPowerState()));
            } else if (method.equals(METHOD_GET_STATE)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getState()));
            } else if (method.equals(METHOD_GET_DEVICE_SERVICE_DESCRIPTION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getDeviceServiceDescription()));
            } else if (method.equals(METHOD_GET_DEVICE_SERVICE_VERSION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getDeviceServiceVersion()));
            } else if (method.equals(METHOD_GET_PHYSICAL_DEVICE_DESCRIPTION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getPhysicalDeviceDescription()));
            } else if (method.equals(METHOD_GET_PHYSICAL_DEVICE_NAME)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getPhysicalDeviceName()));
            } else if (method.equals(METHOD_GET_CAP_REC_NEAR_END_SENSOR)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getCapRecNearEndSensor()));
            } else if (method.equals(METHOD_GET_CAP_REC_PAPERCUT)) {
                callbackContext
                        .sendPluginResult(new PluginResult(
                                PluginResult.Status.OK, posPrinter
                                .getCapRecPapercut()));
            } else if (method.equals(METHOD_GET_CAP_REC_MARK_FEED)) {
                callbackContext
                        .sendPluginResult(new PluginResult(
                                PluginResult.Status.OK, posPrinter
                                .getCapRecMarkFeed()));
            } else if (method.equals(METHOD_GET_ASYNC_MODE)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getAsyncMode()));
            } else if (method.equals(METHOD_SET_ASYNC_MODE)) {
                posPrinter.setAsyncMode(args.getBoolean(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_CHARACTER_SET)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getCharacterSet()));
            } else if (method.equals(METHOD_SET_CHARACTER_SET)) {
                posPrinter.setCharacterSet(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_CHARACTER_SET_LIST)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getCharacterSetList()));
            } else if (method.equals(METHOD_GET_COVER_OPEN)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getCoverOpen()));
            } else if (method.equals(METHOD_GET_ERROR_LEVEL)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getErrorLevel()));
            } else if (method.equals(METHOD_GET_ERROR_STATION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getErrorStation()));
            } else if (method.equals(METHOD_GET_ERROR_STRING)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getErrorString()));
            } else if (method.equals(METHOD_GET_FONT_TYPEFACE_LIST)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getFontTypefaceList()));
            } else if (method.equals(METHOD_GET_PAGE_MODE_AREA)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getPageModeArea()));
            } else if (method.equals(METHOD_GET_PAGE_MODE_HORIZONTAL_POSITION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getPageModeHorizontalPosition()));
            } else if (method.equals(METHOD_SET_PAGE_MODE_HORIZONTAL_POSITION)) {
                posPrinter.setPageModeHorizontalPosition(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_PAGE_MODE_PRINT_AREA)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getPageModePrintArea()));
            } else if (method.equals(METHOD_SET_PAGE_MODE_PRINT_AREA)) {
                posPrinter.setPageModePrintArea(args.getString(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_PAGE_MODE_PRINT_DIRECTION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getPageModePrintDirection()));
            } else if (method.equals(METHOD_SET_PAGE_MODE_PRINT_DIRECTION)) {
                posPrinter.setPageModePrintDirection(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_PAGE_MODE_VERTICAL_POSITION)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter
                        .getPageModeVerticalPosition()));
            } else if (method.equals(METHOD_SET_PAGE_MODE_VERTICAL_POSITION)) {
                posPrinter.setPageModeVerticalPosition(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_GET_REC_EMPTY)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getRecEmpty()));
            } else if (method.equals(METHOD_GET_REC_NEAR_END)) {
                callbackContext.sendPluginResult(new PluginResult(
                        PluginResult.Status.OK, posPrinter.getRecNearEnd()));
            } else if (method.equals(METHOD_OPEN)) {
                posPrinter.open(args.getString(1));
                callbackContext.success();
            } else if (method.equals(METHOD_CLOSE)) {
                posPrinter.close();
                callbackContext.success();
            } else if (method.equals(METHOD_CLAIM)) {
                posPrinter.claim(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_RELEASE)) {
                posPrinter.release();
                callbackContext.success();
            } else if (method.equals(METHOD_CHECK_HEALTH)) {
                posPrinter.checkHealth(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_CLEAR_PRINT_AREA)) {
                posPrinter.clearPrintArea();
                callbackContext.success();
            } else if (method.equals(METHOD_CUT_PAPER)) {
                posPrinter.cutPaper(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_MARK_FFED)) {
                posPrinter.markFeed(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_PAGE_MODE_PRINT)) {
                posPrinter.pageModePrint(args.getInt(1));
                callbackContext.success();
            } else if (method.equals(METHOD_PRINT_BAR_CODE)) {
                posPrinter.printBarCode(args.getInt(1), args.getString(2),
                        args.getInt(3), args.getInt(4), args.getInt(5),
                        args.getInt(6), args.getInt(7));
                callbackContext.success();
            } else if (method.equals(METHOD_PRINT_BITMAP)) {
                posPrinter.printBitmap(args.getInt(1), args.getString(2),
                        args.getInt(3), args.getInt(4));
                callbackContext.success();
            }else if (method.equals(METHOD_PRINT_BITMAP_WITH_URL)) {
                // BitmapDrawable bmpDrawAble = (BitmapDrawable)
                // context.getResources().getDrawable(R.drawable.sample);
                // Bitmap image = bmpDrawAble.getBitmap();
                String imageURL = args.getString(2);
                Bitmap image = getBitmap(imageURL);
                ByteBuffer bitmapbuffer = ByteBuffer.allocate(4);
                bitmapbuffer.put((byte) args.getInt(1));
                bitmapbuffer.put((byte) 80);
                bitmapbuffer.put((byte) 0x00);
                bitmapbuffer.put((byte) 0x00);

                posPrinter.printBitmap(bitmapbuffer.getInt(0), image, args.getInt(3), args.getInt(4));
            }else if (method.equals(METHOD_PRINT_BITMAP_WITH_BASE64)) {
                // BitmapDrawable bmpDrawAble = (BitmapDrawable)
                // context.getResources().getDrawable(R.drawable.sample);
                // Bitmap image = bmpDrawAble.getBitmap();
                String base64EncodedData= args.getString(2);
                Bitmap image = getDecodedBitmap(base64EncodedData); //getBitmap(imageURL);
                ByteBuffer bitmapbuffer = ByteBuffer.allocate(4);
                bitmapbuffer.put((byte) args.getInt(1));
                bitmapbuffer.put((byte) 80);
                bitmapbuffer.put((byte) 0x00);
                bitmapbuffer.put((byte) 0x00);

                posPrinter.printBitmap(bitmapbuffer.getInt(0), image, args.getInt(3), args.getInt(4));
            }
            else if (method.equals(METHOD_PRINT_NORMAL)) {
                posPrinter.printNormal(args.getInt(1), args.getString(2));
                callbackContext.success();
            } else if (method.equals(METHOD_TRANSACTION_PRINT)) {
                posPrinter.transactionPrint(args.getInt(1), args.getInt(2));
                callbackContext.success();
            }else if (method.equals(METHOD_ADD_ENTRY)) {
                ///////////////////////////////

                try {
//					JSONArray args = objs.getJSONArray("data");
                    String productName = args.getString(1);
                    String categoryType = "2";//args.getString(2);
                    String ifType = args.getString(2);
                    String address = args.getString(3);
                    String ldn = null;
                    if (args.length() > 4)
                        ldn = args.getString(4);

                    if (productName == null || categoryType == null || ifType == null || address == null
                            || productName.length() <= 0 || categoryType.length() <= 0 || ifType.length() <= 0
                            || address.length() <= 0) {


                        callbackContext.error("Argument Error");
                    }

                    try {
                        bxlConfigLoader.openFile();
                    } catch (Exception e) {
                        bxlConfigLoader.newFile();
                    }

                    for (Object entry : bxlConfigLoader.getEntries()) {
                        JposEntry jposEntry = (JposEntry) entry;
                        bxlConfigLoader.removeEntry(jposEntry.getLogicalName());
                    }

                    bxlConfigLoader.addEntry((ldn == null || ldn.length() <= 0) ? productName : ldn,
                            Integer.parseInt(categoryType),
                            productName,
                            Integer.parseInt(ifType),
                            address);

                    bxlConfigLoader.saveFile();

                } catch (JSONException e) {
                    e.printStackTrace();
                    callbackContext.error(e.getMessage());
                } catch (Exception e) {
                    e.printStackTrace();
                    e.printStackTrace();
                    callbackContext.error(e.getMessage());
                }
                ///////////////////////////////
                callbackContext.success();
            }else {
                callbackContext.error("Requested function is not defined.");
                return false;
            }

            return true;
        } catch (JposException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
            return false;
        } catch (JSONException e) {
            e.printStackTrace();
            callbackContext.error(e.getMessage());
            return false;
        }
    }
}
