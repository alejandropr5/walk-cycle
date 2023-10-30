/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduino;

import com.google.gson.Gson;
import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import com.panamahitek.*;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author alejandro
 */
public class Aplicacion extends javax.swing.JFrame {

    /**
     * Creates new form Aplicacion
     */
    private int xMouse, yMouse;
    private boolean btnFlag = false;
    private PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
    private final SerialPortEventListener listener = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (ino.isMessageAvailable()) {
                    String mensaje = ino.printMessage();

                    Gson gson = new Gson();
                    Data data = gson.fromJson(mensaje, Data.class);

                    lbPrint.setText(data.toString());

                    switch (data.getFase()) {
                        case "CI": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1_CI.jpg")));
                            break;
                        }
                        case "AI": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/2_AI.jpg")));
                            break;
                        }
                        case "AM": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/3_AM.jpg")));
                            break;
                        }
                        case "AF": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/4_AF.jpg")));
                            break;
                        }
                        case "OP": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/5_OP.jpg")));
                            break;
                        }
                        case "OI": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/6_OI.jpg")));
                            break;
                        }
                        case "OM": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/7_OM.jpg")));
                            break;
                        }
                        case "OF": {
                            jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/8_OF.jpg")));
                            break;
                        }
                    }
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public Aplicacion() {
        initComponents();
        setLocationRelativeTo(null);
        jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1_CI.jpg")));

        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/red.png")));
        Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
        conIcon.setIcon(new ImageIcon(img2));

        ImageIcon img3 = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/restart.png")));
        Image img4 = img3.getImage().getScaledInstance(jRestart.getWidth() - 15, jRestart.getHeight() - 15, Image.SCALE_SMOOTH);
        jRestart.setIcon(new ImageIcon(img4));

        jcPuertos.removeAllItems();
        jcPuertos.addItem("Puerto");
        List<String> list = ino.getSerialPorts();
        for (int i = 0; i < list.size(); i++) {
            jcPuertos.addItem(list.get(i));
        }
    }

    public void alerta(String msg) {
        javax.swing.JOptionPane.showMessageDialog(this, msg, "ALERTA", javax.swing.JOptionPane.WARNING_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        jPanel1 = new javax.swing.JPanel();
        bg = new javax.swing.JPanel();
        header = new javax.swing.JPanel();
        exit = new javax.swing.JPanel();
        exitTxt = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        conIcon = new javax.swing.JLabel();
        jcPuertos = new javax.swing.JComboBox<>();
        jRestart = new javax.swing.JLabel();
        panelIzq = new javax.swing.JPanel();
        boton = new javax.swing.JPanel();
        btnTxt = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jImagen = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lbPrint = new javax.swing.JLabel();

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        header.setBackground(new java.awt.Color(15, 43, 70));
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                headerMousePressed(evt);
            }
        });
        header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exit.setBackground(new java.awt.Color(15, 43, 70));
        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitMouseExited(evt);
            }
        });

        exitTxt.setBackground(new java.awt.Color(15, 43, 70));
        exitTxt.setFont(new java.awt.Font("Roboto Black", 1, 24)); // NOI18N
        exitTxt.setForeground(new java.awt.Color(255, 255, 255));
        exitTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitTxt.setText("X");

        javax.swing.GroupLayout exitLayout = new javax.swing.GroupLayout(exit);
        exit.setLayout(exitLayout);
        exitLayout.setHorizontalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exitLayout.createSequentialGroup()
                .addComponent(exitTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        exitLayout.setVerticalGroup(
            exitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(exitTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        header.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Roboto Medium", 1, 20)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Fase de Marcha");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        header.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 180, 30));

        conIcon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        conIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                conIconMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                conIconMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                conIconMouseExited(evt);
            }
        });
        header.add(conIcon, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 0, 30, 30));

        jcPuertos.setBackground(new java.awt.Color(204, 204, 204));
        jcPuertos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Puerto" }));
        jcPuertos.setBorder(null);
        jcPuertos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jcPuertos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jcPuertosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jcPuertosMouseEntered(evt);
            }
        });
        jcPuertos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcPuertosActionPerformed(evt);
            }
        });
        header.add(jcPuertos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 0, 60, 30));

        jRestart.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jRestart.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRestartMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jRestartMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jRestartMouseExited(evt);
            }
        });
        header.add(jRestart, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 0, 30, 30));

        bg.add(header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 30));

        panelIzq.setBackground(new java.awt.Color(204, 204, 204));

        boton.setBackground(new java.awt.Color(15, 43, 70));
        boton.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                botonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                botonMouseExited(evt);
            }
        });

        btnTxt.setFont(new java.awt.Font("Roboto", 1, 18)); // NOI18N
        btnTxt.setForeground(new java.awt.Color(255, 255, 255));
        btnTxt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnTxt.setText("INICIAR");

        javax.swing.GroupLayout botonLayout = new javax.swing.GroupLayout(boton);
        boton.setLayout(botonLayout);
        botonLayout.setHorizontalGroup(
            botonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botonLayout.createSequentialGroup()
                .addComponent(btnTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                .addContainerGap())
        );
        botonLayout.setVerticalGroup(
            botonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Roboto Condensed", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(15, 43, 70));
        jLabel1.setText("<html> La fase de apoyo se compone de 5 etapas:  <p>-Fase de contacto inicial (CI).   <p>-Fase inicial del apoyo o de respuesta a la carga (AI).  <p>-Fase media del apoyo (AM).   <p>-Fase final del apoyo (AF).   <p>-Fase previa a la oscilación (OP).  <p>La fase de oscilación se compone de 3 etapas:<p>-Fase inicial de oscilación (OI).<p>-Fase media de oscilación (OM).<p>-Fase final de oscilación (OF).</html>");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout panelIzqLayout = new javax.swing.GroupLayout(panelIzq);
        panelIzq.setLayout(panelIzqLayout);
        panelIzqLayout.setHorizontalGroup(
            panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelIzqLayout.createSequentialGroup()
                .addGroup(panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelIzqLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelIzqLayout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        panelIzqLayout.setVerticalGroup(
            panelIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelIzqLayout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(boton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        bg.add(panelIzq, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 450));

        jImagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/1_CI.jpg"))); // NOI18N
        bg.add(jImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 490, 360));

        jPanel3.setBackground(new java.awt.Color(247, 247, 247));

        lbPrint.setBackground(new java.awt.Color(15, 43, 70));
        lbPrint.setFont(new java.awt.Font("Roboto", 0, 18)); // NOI18N
        lbPrint.setForeground(new java.awt.Color(15, 43, 70));
        lbPrint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbPrint, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
        );

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 490, 60));

        getContentPane().add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 450));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMouseExited
        btnTxt.setForeground(Color.WHITE);
        boton.setBackground(new Color(15, 43, 70));
    }//GEN-LAST:event_botonMouseExited

    private void botonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMouseEntered
        btnTxt.setForeground(new Color(192, 192, 192));
        boton.setBackground(new Color(15, 33, 60));
    }//GEN-LAST:event_botonMouseEntered

    private void botonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonMouseClicked
        if (btnFlag) {
            if (btnTxt.getText().equals("INICIAR")) {
                try {
                    ino.sendData("1");
                    btnTxt.setText("DETENER");
                } catch (ArduinoException | SerialPortException ex) {
                    Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    alerta("Primero realice la conexión al puerto.");
                }

            } else if (btnTxt.getText().equals("DETENER")) {
                try {
                    ino.sendData("2");
                    btnTxt.setText("INICIAR");
                    lbPrint.setText("");
                } catch (ArduinoException | SerialPortException ex) {
                    Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    alerta("Primero realice la conexión al puerto.");
                }
            }
        } else {
            alerta("Realice la conexión a Arduino.");
        }

    }//GEN-LAST:event_botonMouseClicked

    private void headerMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_headerMousePressed

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_headerMouseDragged

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel2MouseClicked

    private void exitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseExited
        exitTxt.setForeground(Color.white);
    }//GEN-LAST:event_exitMouseExited

    private void exitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseEntered
        exitTxt.setForeground(new Color(192, 192, 192));
    }//GEN-LAST:event_exitMouseEntered

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void conIconMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conIconMouseEntered
        if (btnFlag) {
            ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/greenD.png")));
            Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
            conIcon.setIcon(new ImageIcon(img2));
        } else if (!btnFlag) {
            ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/redD.png")));
            Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
            conIcon.setIcon(new ImageIcon(img2));
        }
    }//GEN-LAST:event_conIconMouseEntered

    private void conIconMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conIconMouseExited
        if (btnFlag) {
            ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/green.png")));
            Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
            conIcon.setIcon(new ImageIcon(img2));
        } else if (!btnFlag) {
            ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/red.png")));
            Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
            conIcon.setIcon(new ImageIcon(img2));
        }
    }//GEN-LAST:event_conIconMouseExited

    private void conIconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_conIconMouseClicked
        String puerto = jcPuertos.getSelectedItem() + "";
        if (!btnFlag) {
            if (!puerto.equals("Puerto")) {
                try {
                    ino.arduinoRXTX(puerto, 9600, listener);
                    ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/green.png")));
                    Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
                    conIcon.setIcon(new ImageIcon(img2));
                    javax.swing.JOptionPane.showMessageDialog(this, "Puerto " + puerto + " conectado correctamente.", "INFORMACIÓN", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                    btnFlag = true;
                } catch (ArduinoException ex) {
                    Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
                    this.alerta(ex.getMessage().substring(81, ex.getMessage().length()));
                }
            } else {
                alerta("Seleccione un puerto disponible.");
            }
        } else if (btnFlag) {
            try {
                ino.killArduinoConnection();
                ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/red.png")));
                Image img2 = img.getImage().getScaledInstance(conIcon.getWidth() - 15, conIcon.getHeight() - 15, Image.SCALE_SMOOTH);
                conIcon.setIcon(new ImageIcon(img2));
                javax.swing.JOptionPane.showMessageDialog(this, "Conexión con Arduino finalizada correctamente.", "INFORMACIÓN", javax.swing.JOptionPane.INFORMATION_MESSAGE);
                btnFlag = false;
                jcPuertos.removeAllItems();
                jcPuertos.addItem("Puerto");
                List<String> list = ino.getSerialPorts();
                for (int i = 0; i < list.size(); i++) {
                    jcPuertos.addItem(list.get(i));
                }
            } catch (ArduinoException ex) {
                Logger.getLogger(Aplicacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


    }//GEN-LAST:event_conIconMouseClicked

    private void jcPuertosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcPuertosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcPuertosActionPerformed

    private void jcPuertosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcPuertosMouseClicked

    }//GEN-LAST:event_jcPuertosMouseClicked

    private void jcPuertosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jcPuertosMouseEntered

    }//GEN-LAST:event_jcPuertosMouseEntered

    private void jRestartMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRestartMouseEntered
        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/restartD.png")));
        Image img2 = img.getImage().getScaledInstance(jRestart.getWidth() - 15, jRestart.getHeight() - 15, Image.SCALE_SMOOTH);
        jRestart.setIcon(new ImageIcon(img2));
    }//GEN-LAST:event_jRestartMouseEntered

    private void jRestartMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRestartMouseExited
        ImageIcon img = new ImageIcon(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/restart.png")));
        Image img2 = img.getImage().getScaledInstance(jRestart.getWidth() - 15, jRestart.getHeight() - 15, Image.SCALE_SMOOTH);
        jRestart.setIcon(new ImageIcon(img2));
    }//GEN-LAST:event_jRestartMouseExited

    private void jRestartMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRestartMouseClicked
        jcPuertos.removeAllItems();
        jcPuertos.addItem("Puerto");
        List<String> list = ino.getSerialPorts();
        for (int i = 0; i < list.size(); i++) {
            jcPuertos.addItem(list.get(i));
        }
    }//GEN-LAST:event_jRestartMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Aplicacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Aplicacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JPanel boton;
    private javax.swing.JLabel btnTxt;
    private javax.swing.JLabel conIcon;
    private javax.swing.JPanel exit;
    private javax.swing.JLabel exitTxt;
    private javax.swing.JPanel header;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JLabel jImagen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel jRestart;
    private javax.swing.JComboBox<String> jcPuertos;
    private javax.swing.JLabel lbPrint;
    private javax.swing.JPanel panelIzq;
    // End of variables declaration//GEN-END:variables
}
