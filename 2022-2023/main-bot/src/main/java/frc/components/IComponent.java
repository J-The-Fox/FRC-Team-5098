package frc.components;

/*
 * 'public': This class can be accessed outside of this folder.
 * 'interface': This can contain method declarations without their definitions. Any class that implements this
 *              interface MUST implement all methods this interface describes. Otherwise, the compiler reports an
 *              error.
 * 'IComponent': The name of this particular interface.
 */
public interface IComponent
{
    /*
     * 'public': This method can be accessed outside this class.
     * 'void': This method does not return anything.
     * 'update': This method is called "update".
     * '()': This method accepts no arguments.
     * ';' This method contains no body; it is not defined (only declared); it is not implemented.
     */
    public void update();
}
