package work.hoodie.kubernetes.mojo;

import lombok.extern.slf4j.Slf4j;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Slf4j
abstract class AbstractKubeCtlMojo extends AbstractMojo {

    @Parameter
    protected boolean skipKubeCtl;

    protected void runCommand(String command) {
        getLog().debug(command);
        try {
            Process exec = Runtime.getRuntime().exec(command);

            InputStream standardOut = exec.getInputStream();
            InputStream standardError = exec.getErrorStream();

            logOutput(standardOut);
            logOutput(standardError);

            exec.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void logOutput(InputStream standardOutput) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(standardOutput));
        String line;
        while ((line = reader.readLine()) != null) {
            log.info("{}", line);
        }
    }
}
