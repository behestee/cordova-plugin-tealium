package com.phonegap.plugins.tealium;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.tealium.library.Tealium;
import android.util.Log;
import java.util.Iterator;
import java.util.Map;

/**
 * This class echoes a string called from JavaScript.
 */
public class TealiumPlugin extends CordovaPlugin {
	private Map<String, String> JsonToMap(JSONObject jdata)
			throws JSONException {
		if (jdata == null) {
			Log.d("TealiumPlugin", "not a json");
			return null;
		}
		@SuppressWarnings("unchecked")
		Iterator<String> nameItr = jdata.keys();
		String[] params = new String[(jdata.length() * 2)];
		int count = 0;
		while (nameItr.hasNext()) {
			String name = nameItr.next();
			params[count] = name;
			params[++count] = jdata.getString(name);
		}
		return Tealium.map(params);
	}

	@Override
	public boolean execute(String action, JSONArray args,
			final CallbackContext callbackContext) throws JSONException {
		try {
			Log.d("TealiumPlugin", action);
			if (action.equals("track")) {
				this.track(args.optJSONObject(0), args.getString(1),
						callbackContext);
			} else if (action.equals("addGlobalCustomData")) {
				this.addGlobalCustomData(args.optJSONObject(0), callbackContext);
			} else if (action.equals("addCustomData")) {
				this.addCustomData(args.optJSONObject(0), callbackContext);
			} else {
				Log.d("TealiumPlugin", "invalid TealiumPlugin method: "
						+ action);
				callbackContext
						.error("invalid TealiumPlugin method: " + action);
				return false;
			}
			return true;
		} catch (JSONException e) {
			Log.d("TealiumPlugin exception: ", e.getMessage());
			callbackContext.error("TealiumPlugin json exception: "
					+ e.getMessage());
			return false;
		}
	}

	private void track(JSONObject options, String eventName,
			final CallbackContext callbackContext) throws JSONException {
		final String _eventName = eventName != null && eventName.equals("view") ? Tealium.EVENT_NAME_VIEW
				: Tealium.EVENT_NAME_LINK;
		if (options != null) {
			final Map<String, String> params = this.JsonToMap(options);
			if (params != null) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						Tealium.track(cordova.getActivity(), params, _eventName);
						callbackContext.success("");
					}
				});
			}
		} else {
			cordova.getActivity().runOnUiThread(new Runnable() {
				public void run() {
					Tealium.track(cordova.getActivity(), null, _eventName);
					callbackContext.success("");
				}
			});
		}
	}

	private void addGlobalCustomData(JSONObject options,
			final CallbackContext callbackContext) throws JSONException {
		if (options != null) {
			final Map<String, String> params = this.JsonToMap(options);
			if (params != null) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						Tealium.addGlobalCustomData(params);
						callbackContext.success("");
					}
				});
			}
		} else {
			throw new JSONException(
					"Invalid params for method: addGlobalCustomData");
		}
	}

	private void addCustomData(JSONObject options,
			final CallbackContext callbackContext) throws JSONException {
		if (options != null) {
			final Map<String, String> params = this.JsonToMap(options);
			if (params != null) {
				cordova.getActivity().runOnUiThread(new Runnable() {
					public void run() {
						Tealium.addCustomData(cordova.getActivity(), params);
						callbackContext.success("");
					}
				});
			}
		} else {
			throw new JSONException("Invalid params for method: addCustomData");
		}
	}

}