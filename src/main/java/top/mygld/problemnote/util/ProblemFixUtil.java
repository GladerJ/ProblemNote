package top.mygld.problemnote.util;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ProblemFixUtil extends JFrame {
    public static String fix(String s) {
        String[] lines = s.split("\n");
        StringBuilder sb = new StringBuilder();
        boolean inBlockFormula = false;

        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            String trimmed = line.trim();

            // 空行：直接保留
            if (trimmed.isEmpty()) {
                sb.append(line).append("\n");
                continue;
            }

            // 判断是否是 block 开始
            if (trimmed.startsWith("\\[") || trimmed.startsWith("$$")) {
                inBlockFormula = true;
                sb.append(line).append("\n");
                continue;
            }

            // 判断是否是 block 结束
            if (trimmed.endsWith("\\]") || trimmed.endsWith("$$")) {
                inBlockFormula = false;
                sb.append(line).append("\n");
                continue;
            }

            // 判断下一行是否是 block 开始
            boolean nextStartsBlock = false;
            if (i + 1 < lines.length) {
                String nextLine = lines[i + 1].trim();
                if (nextLine.startsWith("\\[") || nextLine.startsWith("$$")) {
                    nextStartsBlock = true;
                }
            }

            // 不在 block 中，当前行未以 </br> 结尾，且下一行不是 block 起始 → 添加 </br>
            if (!inBlockFormula && !trimmed.endsWith("</br>") && !nextStartsBlock) {
                sb.append(line).append("</br>\n");
            } else {
                sb.append(line).append("\n");
            }
        }

        return sb.toString();
    }



    public ProblemFixUtil(){
        super("Problem Note");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800,600);
        setLayout(null);
        setLocationRelativeTo(null);
        JTextArea jta = new JTextArea();
        JScrollPane jScrollPane = new JScrollPane(jta);
        jta.setLineWrap(true);
        jScrollPane.setBounds(10,10,600,400);
        this.getContentPane().add(jScrollPane);
        JButton jb = new JButton("格式化");
        jb.setBounds(650,10,100,30);
        this.getContentPane().add(jb);
        jb.addActionListener(e->{
           String s = jta.getText();
           s = fix(s);
           jta.setText(s);
        });
        setVisible(true);
    }

    public static void main(String[] args) {
        new ProblemFixUtil();
    }
}
