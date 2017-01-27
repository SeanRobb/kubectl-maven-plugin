package work.hoodie.kubernetes.mojo;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import work.hoodie.kubernetes.common.KubeCtlBaseOptions;

import static java.util.Objects.nonNull;

@Mojo(name = "delete", defaultPhase = LifecyclePhase.PRE_CLEAN)
public class KubeCtlDelete extends AbstractKubeCtlMojo {

    @Parameter(defaultValue = "${project.build.directory}/kubernetes/resources")
    private String outputDirectory;

    @Parameter
    private KubeCtlBaseOptions kubeCtlBaseOptions;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!skipKubeCtl) {
            if (nonNull(kubeCtlBaseOptions)) {
                getLog().debug(kubeCtlBaseOptions.toString());
                String command = kubeCtlBaseOptions.buildCommand()
                        .concat(" delete -f ")
                        .concat(outputDirectory);
                getLog().info(command);
                runCommand(command);
            } else {
                getLog().info("No Delete Options");
            }
        }
    }
}
