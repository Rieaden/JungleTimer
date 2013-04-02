package Hooker;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import Util.AppCore;

public class GlobalKeyListener implements NativeKeyListener {
        public void nativeKeyPressed(NativeKeyEvent e) { /*non implemente*/ }

        public void nativeKeyReleased(NativeKeyEvent e) { /*non implemente*/ }

        public void nativeKeyTyped(NativeKeyEvent e) { /*non implemente*/ }

        public static void main(String[] args) {
                try {
                        GlobalScreen.registerNativeHook();
                }
                catch (NativeHookException ex) {
                        System.err.println("There was a problem registering the native hook.");
                        System.err.println(ex.getMessage());

                        System.exit(1);
                }

                //Construct the example object and initialze native hook.
                GlobalScreen.getInstance().addNativeKeyListener(new GlobalKeyListener());
        }
}