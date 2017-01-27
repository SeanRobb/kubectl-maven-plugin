package work.hoodie.kubernetes.mojo;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.snakeyaml.Yaml;
import io.fabric8.kubernetes.api.model.HasMetadata;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.ReplicationController;
import io.fabric8.kubernetes.api.model.Service;
import org.apache.commons.io.FileUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import work.hoodie.kubernetes.common.FileType;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.Objects.nonNull;
import static org.apache.commons.io.FileUtils.writeStringToFile;

@Mojo(name = "make-files", defaultPhase = LifecyclePhase.GENERATE_RESOURCES)
public class MakeFiles extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.directory}/kubernetes/resources")
    private String outputDirectory;

    @Parameter
    private List<ReplicationController> replicationControllers;

    @Parameter
    private List<Service> services;

    @Parameter
    private List<Pod> pods;

    @Parameter
    private FileType fileType;

    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info(outputDirectory);

        new File(outputDirectory).mkdirs();

        if (nonNull(replicationControllers)) {
            replicationControllers
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(this::buildFiles);
        }
        if (nonNull(services)) {
            services
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(this::buildFiles);

        }
        if (nonNull(pods)) {
            pods
                    .stream()
                    .filter(Objects::nonNull)
                    .forEach(this::buildFiles);
        }
    }

    private void buildFiles(HasMetadata kubeObject) {
        try {
            String name = kubeObject.getMetadata().getName();
            String fileSuffix = null;
            String fileContents = null;

            String prettyJSONString = new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(kubeObject);

            if (FileType.JSON == fileType) {
                fileSuffix=".json";
                fileContents = prettyJSONString;
            } else if (FileType.YML == fileType) {
                Yaml yaml = new Yaml();
                Map<String, Object> map = (Map<String, Object>) yaml.load(prettyJSONString);
                fileContents = map.toString();
                fileSuffix=".yaml";
            }
            writeStringToFile(new File(outputDirectory + "/" + name + fileSuffix), fileContents);
        } catch (IOException e) {
        }
    }

}
