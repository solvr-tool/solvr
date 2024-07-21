package solvr.ui

import solvr.core.parser.FileParser
import java.awt.GridLayout
import java.io.File
import javax.swing.*

class UI {
    companion object {
        private fun createAndShowGUI(filePath: String) {
            val file = File(filePath)

            val fileParser = FileParser()

            val headFile = fileParser.getHeadFile(file.readText())
            val otherFile = fileParser.getOtherFile(file.readText())

            // Create and set up the window.
            val frame = JFrame("Solvr")
            frame.setSize(1000, 600)
            frame.setLocationRelativeTo(null)
            frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

            // Create a panel with GridLayout
            val panel = JPanel(GridLayout(1, 3))
            panel.border = BorderFactory.createEmptyBorder(10, 10, 10, 10)

            val left = JTextArea(headFile)
            left.border = BorderFactory.createTitledBorder("Head")
            left.isEditable = false
            panel.add(left)

            val center = JTextArea(file.readText())
            center.border = BorderFactory.createTitledBorder("Result")
            panel.add(center)

            val right = JTextArea(otherFile)
            right.border = BorderFactory.createTitledBorder("Other")
            right.isEditable = false
            panel.add(right)

            // Add the panel to the frame
            frame.contentPane.add(panel)

            // Display the window.
            frame.isVisible = true
        }

        @JvmStatic
        fun main(args: Array<String>) {
            // Schedule a job for the event-dispatching thread:
            // creating and showing this application's GUI.
            SwingUtilities.invokeLater { createAndShowGUI(args[0]) }
        }
    }
}
