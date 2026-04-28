package model;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class PainterMillerRabin {

    private final Canvas canvas;

    public PainterMillerRabin(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * Dibuja el resultado visual en el canvas.
     */
    public void drawResult(String number, boolean isPrime) {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        clear(); // Limpia antes de dibujar el nuevo resultado

        double size = 120;
        double x = (canvas.getWidth() - size) / 2;
        double y = (canvas.getHeight() - size) / 2;

        // Color: Verde para primos, Rojo para compuestos
        gc.setFill(isPrime ? Color.LIMEGREEN : Color.RED);
        gc.fillOval(x, y, size, size);

        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.strokeOval(x, y, size, size);

        // Renderizado de texto
        gc.setFill(Color.WHITE);
        gc.setFont(new Font("System Bold", 14));

        String text = number.length() > 10 ? number.substring(0, 10) + "..." : number;

        // Centrado manual simple
        double textX = x + (size / 2) - (text.length() * 3.8);
        double textY = y + (size / 2) + 5;

        gc.fillText(text, textX, textY);
    }

    public void clear() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }
}
