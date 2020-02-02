/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pareo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 *
 * @author Asus
 */
public class RoundLineBorder extends AbstractBorder{
    
    Color lineColor = null;
    boolean roundedCorners = false;
    RenderingHints antiliasing = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    
    public RoundLineBorder(Color lineColor, boolean roundedCorners){
        this.lineColor = lineColor;
    }
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height){
        
        Graphics2D g2d = (Graphics2D)g;
        Shape outer;
        Shape inner;
        
        if(roundedCorners){
            int offs = 1;
            int size = offs + offs;
            float arc = .2f * offs;
            //g2d.setColor(lineColor);
            outer = new RoundRectangle2D.Float(x + 1, y + 1, width - 2, height - 2, offs * 30, offs * height);
            inner = new RoundRectangle2D.Float(x + offs - 2, y + offs - 2, width - size + 4, height - size + 4, arc, arc);
            Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
            g2d.addRenderingHints(antiliasing);
            path.append(outer, false);
            path.append(inner, false);
            g2d.fill(path);
            
        }
        
        Color oldColor = new Color(47, 53, 93);
        g2d.setColor(oldColor);
        
        int offs = 1;
        int size = offs + offs;
        float arc = .2f * offs;
        //g2d.setColor(lineColor);
        outer = new RoundRectangle2D.Float(x + 1, y + 1, width - 2, height - 2, offs * 30, offs * height);
        inner = new RoundRectangle2D.Float(x + offs - 2, y + offs - 2, width - size + 4, height - size + 4, arc, arc);
        Path2D path = new Path2D.Float(Path2D.WIND_EVEN_ODD);
        g2d.addRenderingHints(antiliasing);
        path.append(outer, false);
        path.append(inner, false);
        g2d.fill(path);
        
    }
    
}
