package controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

//import org.springframework.stereotype.

//@Controller
@Controller
@RequestMapping(value = "/file")
public class FileController {

    @Autowired
    SimpleJobLauncher jobLauncher;
    
    @Autowired
    Job testJob;
    
    
    
    public FileController() {
        System.out.println("File Controller");
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public void handleFileUpload(@RequestParam(name = "name") String name,
            @RequestParam(name = "file") MultipartFile uploadFile, HttpServletResponse response) throws IOException {
        System.out.println("Test");
        if (!uploadFile.isEmpty()) {
            try {
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream("D:\\" + name));
                FileCopyUtils.copy(uploadFile.getInputStream(), stream);
                stream.close();
                // redirectAttributes.addFlashAttribute("message",
                // "You successfully uploaded " + name + "!");
            } catch (Exception e) {
//                redirectAttributes.addFlashAttribute("message",
//                        "You failed to upload " + name + " => " + e.getMessage());
                response.sendRedirect("http://www.yahoo.com");
            }
        } //else {
            // redirectAttributes.addFlashAttribute("message",
            // "You failed to upload " + name + " because the file was empty");
//        }
        response.sendRedirect("http://www.google.com");
    }
    
    @RequestMapping(value = "/launch")
    @ResponseBody
    public String launchJob() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
        
        Map<String, JobParameter> data = new HashMap<String, JobParameter>();
        data.put("data", new JobParameter("{customerId: '123'}"));
        JobParameters jobParas = new JobParameters(data);
        
        
        jobLauncher.run(testJob, jobParas);
        System.out.println("OK");
        return "Successfully";
    }
}
