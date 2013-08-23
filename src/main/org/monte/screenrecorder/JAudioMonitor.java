/*
 * @(#)JAudioMonitor.java  
 *
 * Copyright (c) 2012 Werner Randelshofer, Goldau, Switzerland.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the
 * license agreement you entered into with Werner Randelshofer.
 * For details see accompanying license terms.
 */
package org.monte.screenrecorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * JAudioMonitor.
 *
 * @author Werner Randelshofer
 * @version 1.0 2012-07-11 Created.
 */
public class JAudioMonitor extends javax.swing.JPanel {
    private Timer timer;
    private Color semiTransparent=new Color(0x40ffffff,true);

    private class Handler implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }
    private Handler handler = new Handler();
    private ScreenRecorder recorder;

    /** Creates new form JAudioMonitor */
    public JAudioMonitor() {
        initComponents();
        setOpaque(false);
        setPreferredSize(new Dimension(7, 16));
        setMinimumSize(new Dimension(7, 16));
    }

    public ScreenRecorder getScreenRecorder() {
        return recorder;
    }

    public void setScreenRecorder(ScreenRecorder recorder) {
        this.recorder = recorder;
    }

    public void start() {
        if (timer == null) {
            timer = new Timer(250, handler);

            timer.start();
        }
    }

    public void stop() {
        if (timer != null) {
            timer.stop();
            timer = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int w = getWidth();
        int h = getHeight();
        if (recorder != null) {
            float level = recorder.getAudioLevelLeft();
            if (level != -1) {
                int levelHeight = Math.min(h, (int) (h * 7 * level));
                g.setColor(semiTransparent);
                g.fillRect(0,0,3,h-levelHeight);
                g.setColor(Color.WHITE);
                g.fillRect(0, h - levelHeight, 3, levelHeight);
            }
             level = recorder.getAudioLevelRight();
            if (level != -1) {
                int levelHeight = Math.min(h, (int) (h * 7 * level));
                g.setColor(semiTransparent);
                g.fillRect(4,0,3,h-levelHeight);
                g.setColor(Color.WHITE);
                g.fillRect(4, h - levelHeight, 3, levelHeight);
            }
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
