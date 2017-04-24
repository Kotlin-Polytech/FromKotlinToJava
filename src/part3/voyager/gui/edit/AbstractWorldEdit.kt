package part3.voyager.gui.edit

import part3.voyager.world.World
import javax.swing.undo.UndoableEdit

abstract class AbstractWorldEdit(val world: World) : UndoableEdit {
    override fun isSignificant() = true

    override fun addEdit(anEdit: UndoableEdit?) = false

    override fun canRedo() = true

    override fun canUndo() = true

    override fun replaceEdit(anEdit: UndoableEdit?): Boolean {
        throw UnsupportedOperationException("Should never be called")
    }

    override fun die() {
    }

    override fun getUndoPresentationName() = "Undo '$presentationName'"

    override fun getRedoPresentationName() = "Redo '$presentationName'"
}