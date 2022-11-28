package Experiment.E3;
/**
 * Exception class for access in empty containers
 * such as stacks, queues, and priority queues.
 */
public class UnderflowException extends RuntimeException
{
    UnderflowException(){
    }
    UnderflowException(String message){
        super(message);
    }
}
