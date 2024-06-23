/*
 * Copyright (c) 2020 The LineageOS Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.lineageos.settings.logo;

import org.lineageos.settings.utils.FileUtils;
import android.os.Handler;

public class LogoUtil {
    public static final String RED_LED = "/sys/rgb/leds/green_1/brightness";
    public static final String GREEN_LED = "/sys/rgb/leds/red_1/brightness";
    public static final String BLUE_LED = "/sys/rgb/leds/blue_1/brightness";

    public static final String RED_LED_BLINK = "/sys/rgb/leds/green_1/blink";
    public static final String GREEN_LED_BLINK = "/sys/rgb/leds/red_1/blink";
    public static final String BLUE_LED_BLINK = "/sys/rgb/leds/blue_1/blink";

    public static void turnOff() {
        FileUtils.writeLine(RED_LED_BLINK, "0");
        FileUtils.writeLine(GREEN_LED_BLINK, "0");
        FileUtils.writeLine(BLUE_LED_BLINK, "0");
        FileUtils.writeLine(RED_LED, "0");
        FileUtils.writeLine(GREEN_LED, "0");
        FileUtils.writeLine(BLUE_LED, "0");
    }

    public static void setRGBStill(int r, int g, int b) {
        FileUtils.writeLine(RED_LED, String.valueOf(r));
        FileUtils.writeLine(GREEN_LED, String.valueOf(g));
        FileUtils.writeLine(BLUE_LED, String.valueOf(b));
    }

    public static void setStillRed(int value) {
        FileUtils.writeLine(RED_LED, String.valueOf(value));
    }

    public static void setStillGreen(int value) {
        FileUtils.writeLine(GREEN_LED, String.valueOf(value));
    }

    public static void setStillBlue(int value) {
        FileUtils.writeLine(BLUE_LED, String.valueOf(value));
    }

    public static void setBlinkRed(boolean state) {
        FileUtils.writeLine(RED_LED_BLINK, state ? "1" : "0");
    }

    public static void setBlinkBlue(boolean state) {
        FileUtils.writeLine(BLUE_LED_BLINK, state ? "1" : "0");
    }

    public static void setBlinkGreen(boolean state) {
        FileUtils.writeLine(GREEN_LED_BLINK, state ? "1" : "0");
    }

    public static void enableBreathingEffect() {
        final Handler handler = new Handler();
        setBlinkRed(true);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setBlinkGreen(true);
            }
        }, 1000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                setBlinkBlue(true);
            }
        }, 2000);
    }
}
