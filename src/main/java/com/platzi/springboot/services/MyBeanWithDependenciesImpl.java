package com.platzi.springboot.services;

import com.platzi.springboot.bean.MyOperation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MyBeanWithDependenciesImpl implements BeanWithDependencies {

    private final Log LOGGER= LogFactory.getLog(this.getClass());

    private MyOperation myOperation;

    public MyBeanWithDependenciesImpl(MyOperation myOperation) {
        this.myOperation = myOperation;
    }

    @Override
    public int operationWithDependencies() {
        LOGGER.info("Hemos ingresado al metodo operationWithDependencies");

        int value = 0;
        LOGGER.debug("El numero enciado como parametro a la dependecia operation es: "+value);
        value = myOperation.sumOne(value);
        return value;
    }
}
