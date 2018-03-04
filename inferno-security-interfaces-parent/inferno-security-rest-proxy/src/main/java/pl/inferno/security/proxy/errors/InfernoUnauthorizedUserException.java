/**
 * Project: inferno-security-rest-proxy
 * File: InfernoUnauthorizedUserException.java
 * Package: pl.inferno.security.proxy.errors
 * Location:
 * 4 mar 2018 00:33:14 by Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 * &copy;2018
 */
package pl.inferno.security.proxy.errors;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.nio.file.attribute.UserPrincipalNotFoundException;

/**
 * Class InfernoUnauthorizedUserException
 *
 * @author Łukasz Przesmycki (lukasz.przesmycki@gmail.com)
 */
public class InfernoUnauthorizedUserException extends UserPrincipalNotFoundException {

    /**
     * long serialVersionUID
     */
    private static final long serialVersionUID = -264513086785686846L;

    /*
     * (non-Javadoc)
     * @see java.nio.file.attribute.UserPrincipalNotFoundException#getName()
     */
    @Override
    public String getName() {
        // TODO Auto-generated method stub
        return super.getName();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getLocalizedMessage()
     */
    @Override
    public String getLocalizedMessage() {
        // TODO Auto-generated method stub
        return super.getLocalizedMessage();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getCause()
     */
    @Override
    public synchronized Throwable getCause() {
        // TODO Auto-generated method stub
        return super.getCause();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#initCause(java.lang.Throwable)
     */
    @Override
    public synchronized Throwable initCause(Throwable cause) {
        // TODO Auto-generated method stub
        return super.initCause(cause);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace()
     */
    @Override
    public void printStackTrace() {
        // TODO Auto-generated method stub
        super.printStackTrace();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace(java.io.PrintStream)
     */
    @Override
    public void printStackTrace(PrintStream s) {
        // TODO Auto-generated method stub
        super.printStackTrace(s);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#printStackTrace(java.io.PrintWriter)
     */
    @Override
    public void printStackTrace(PrintWriter s) {
        // TODO Auto-generated method stub
        super.printStackTrace(s);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#fillInStackTrace()
     */
    @Override
    public synchronized Throwable fillInStackTrace() {
        // TODO Auto-generated method stub
        return super.fillInStackTrace();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getStackTrace()
     */
    @Override
    public StackTraceElement[] getStackTrace() {
        // TODO Auto-generated method stub
        return super.getStackTrace();
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#setStackTrace(java.lang.StackTraceElement[])
     */
    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {
        // TODO Auto-generated method stub
        super.setStackTrace(stackTrace);
    }

    /**
     * @param name
     */
    public InfernoUnauthorizedUserException(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

}
