# History

+ 1996, Netscape created a GUI(Graphical user interface) library, called the IFC(Internet Foundation  Classes).

+ Swing was available as an extension to Java 1.1 and become a part of the standard library in Java SE 1.2.

+ Swing is not a compete replacement for the AWT(Abstract Window Toolkit).

+ Swing more mean the "painted" user interface, while AWT mean the mechanisms of the windows, such as event handling.

+ JavaFX, as a replacement for Swing.

# Swing

+ Frame is in AWT components. JFrame is in Swing components.

  ```java
  int n = 15;
  
          int width = 30 * (n + 3);
          int height = 30 * (n + 3);
          JFrame frame = new JFrame();
          BufferedImage offscreenImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          BufferedImage onscreenImage  = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
          Graphics2D offscreen = offscreenImage.createGraphics();
          Graphics2D onscreen  = onscreenImage.createGraphics();
          RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                  RenderingHints.VALUE_ANTIALIAS_ON);
          hints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
          offscreen.addRenderingHints(hints);
  
          frame.setVisible(true);
          ImageIcon icon = new ImageIcon(onscreenImage);
          JLabel draw = new JLabel(icon);
  
          frame.setContentPane(draw);
  
  
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);            // closes all windows
          // frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);      // closes only current window
          frame.setTitle("Standard Draw");
  //        frame.setJMenuBar(createMenuBar());
          frame.pack();
          frame.requestFocusInWindow();
          frame.setVisible(true);
          offscreen.setColor(Color.BLACK);
          FontMetrics metrics = offscreen.getFontMetrics();
  
          int ws = metrics.stringWidth("a[ ]");
          int hs = metrics.getDescent();
          offscreen.drawString("a[ ]", (float) (15 * (n + 3) - ws/2.0), (float) (15 * (n + 3) + hs));
          onscreen.drawImage(offscreenImage, 0, 0, null);
          frame.repaint();
  ```

  

