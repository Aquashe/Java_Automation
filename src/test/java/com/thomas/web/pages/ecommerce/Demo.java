package com.thomas.web.pages.ecommerce;

import com.thomas.reporting.ExecutionManager;
import org.testng.annotations.Test;

public class Demo {

    @Test
    public void demo(){

        String fullClassName = this.getClass().getPackage().getName();
        String module = fullClassName.substring(fullClassName.lastIndexOf(".") + 1);

        String className = this.getClass().getSimpleName();

        String screenshotDir = ExecutionManager.getExecutionFolder()
                + "\\" + module + "\\" + className + "\\screenshots\\";

        System.out.println("Output : "+screenshotDir);
    }
}
