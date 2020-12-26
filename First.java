import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;






public class First {
    
    static int index = 1;

    private static void createAndShowGUI() {





        final JFrame frame = new JFrame("Photo Player");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
       Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

       frame.setSize(screenSize.width/2, screenSize.height/2);
         
        

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        JMenuItem importer = new JMenuItem("Import");
        fileMenu.add(importer);

        

        JMenuItem close = new JMenuItem("Close");
        fileMenu.add(close);

        JMenu fileMenu1 = new JMenu("Images");
        menuBar.add(fileMenu1);

        JMenuItem prevMenu = new JMenuItem("Previous");
        fileMenu1.add(prevMenu);

        JMenuItem nexMenu = new JMenuItem("Next");
        fileMenu1.add(nexMenu);

       
        close.addActionListener((event) -> {
            frame.dispose();
        });
        close.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));

        JLabel label = new JLabel();
        JLabel label1 = new JLabel();

        final JPanel panel = new JPanel();
        BorderLayout layout = new BorderLayout();
        panel.setSize(300,300);
        panel.setLayout(layout);
        panel.setBackground(Color.lightGray);
        layout.setHgap(10);
        layout.setVgap(10);

        

        JFileChooser fileChooser = new JFileChooser();

        importer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int option = fileChooser.showOpenDialog(frame);
                if (option == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();

                    String[] imgExt = new String[] { "png", "jpg", "gif" };

                    FilenameFilter imgFilter = new FilenameFilter() {
                        @Override
                        public boolean accept(final File dir, final String name) {
                            for (final String ext : imgExt) {
                                if (name.endsWith("." + ext)) {
                                    return (true);
                                }
                            }
                            return (false);
                        }
                    };

                    if (file.isDirectory()) {
                        File[] images = file.listFiles(imgFilter);
                        label.setIcon(new ImageIcon(file + File.separator + images[0].getName(), BorderLayout.CENTER));
                        label1.setText("Image Name :" + images[0].getName());

                        System.out.print(images.length);

                        JButton next = new JButton("Next");


                        next.addActionListener(new ActionListener() {

                            public void actionPerformed(ActionEvent e) {
                                if (index == images.length - 1 ) {
                                    label.setIcon(new ImageIcon (file + File.separator + images[images.length].getName(), BorderLayout.CENTER));
                                    
                                    
                                }else{
                                    index++;
                                }
                                
                           label.setIcon(new ImageIcon (file + File.separator + images[index].getName(), BorderLayout.CENTER));
                           label1.setText("Image Name :" + images[index].getName());
                           
                        }                     
                    });

                    JButton prev = new JButton("Prev");

                    prev.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent e) {
                            index--;
                       label.setIcon(new ImageIcon (file + File.separator + images[index].getName()));
                       label1.setText("Image Name :" + images[index].getName());
                       
                      }

                      

                      
                    
                });

                
                nexMenu.addActionListener(new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        if (index == images.length - 1 ) {
                            label.setIcon(new ImageIcon (file + File.separator + images[images.length].getName(), BorderLayout.CENTER));
                            
                            
                        }else{
                            index++;
                        }
                        
                   label.setIcon(new ImageIcon (file + File.separator + images[index].getName(), BorderLayout.CENTER));
                   label1.setText("Image Name :" + images[index].getName());
                   
                }                     
            });

            prevMenu.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    index--;
               label.setIcon(new ImageIcon (file + File.separator + images[index].getName()));
               label1.setText("Image Name :" + images[index].getName());
               
              }

        });

        
        JLabel textNext = new JLabel();
        textNext.setText("===>");

        textNext.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (index == images.length - 1 ) {
                    label.setIcon(new ImageIcon (file + File.separator + images[images.length].getName(), BorderLayout.CENTER));
                    
                    
                }else{
                    index++;
                }
                
           label.setIcon(new ImageIcon (file + File.separator + images[index].getName(), BorderLayout.CENTER));
           label1.setText("Image Name :" + images[index].getName());
            }

        });

        JLabel textPrev = new JLabel();
        textPrev.setText("<===");
        textPrev.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                index--;
                label.setIcon(new ImageIcon (file + File.separator + images[index].getName()));
                label1.setText("Image Name :" + images[index].getName());
                
               }
 
         });


         label.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                if (index == images.length - 1 ) {
                    label.setIcon(new ImageIcon (file + File.separator + images[images.length].getName(), BorderLayout.CENTER));
                    
                }else{
                    index++;
                }
                
           label.setIcon(new ImageIcon (file + File.separator + images[index].getName(), BorderLayout.CENTER));
           label1.setText("Image Name :" + images[index].getName());
                
               }
 
         });





        prevMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0));
        nexMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0));

        
                  


                panel.add(prev, BorderLayout.LINE_START);
                panel.add(next, BorderLayout.LINE_END);

                panel.add(textNext, BorderLayout.NORTH);
                panel.add(textPrev, BorderLayout.SOUTH);


                    
                }
                
                    

                



                     
                    }else{
                       label.setText("No folder was selected");
                    }
                }
               
        });

    



        panel.add(label1, BorderLayout.SOUTH);
        
        panel.add(label, BorderLayout.CENTER);
        
       

       


        frame.setContentPane(panel);
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}