package solvr.ui

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.SwingUtilities

class UI {
    companion object {
        private fun createAndShowGUI() {
            // Create and set up the window.
            val frame = JFrame("HelloWorldSwing")
            frame.setSize(600, 600)
            frame.setLocationRelativeTo(null)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            // Add the ubiquitous "Hello World" label.
            val label = JLabel("Hello World")
            frame.contentPane.add(label)

            // Display the window.
            frame.isVisible = true
        }

        @JvmStatic
        fun main(args: Array<String>) {
            // Schedule a job for the event-dispatching thread:
            // creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI() }
        }
    }
}
