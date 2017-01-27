package work.hoodie.kubernetes.mojo;


import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import work.hoodie.kubernetes.common.KubeCtlBaseOptions;
import work.hoodie.kubernetes.common.KubeCtlHorizontalAutoScaleOptions;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Mojo(name = "create", defaultPhase = LifecyclePhase.INSTALL)
public class KubeCtlCreate extends AbstractKubeCtlMojo {

    @Parameter(defaultValue = "${project.build.directory}/kubernetes/resources")
    private String outputDirectory;

    @Parameter
    private KubeCtlBaseOptions kubeCtlBaseOptions;

    @Parameter
    private List<KubeCtlHorizontalAutoScaleOptions> kubeCtlHorizontalAutoScaleOptionses;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!skipKubeCtl) {
            if (nonNull(kubeCtlBaseOptions)) {
                getLog().debug(kubeCtlBaseOptions.toString());

                String command = kubeCtlBaseOptions.buildCommand()
                        .concat(" create -f ")
                        .concat(outputDirectory);

                runCommand(command);
            } else {
                getLog().info("No Create Options");
            }

            if (nonNull(kubeCtlHorizontalAutoScaleOptionses)) {
                getLog().debug(kubeCtlHorizontalAutoScaleOptionses.toString());
                kubeCtlHorizontalAutoScaleOptionses
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .map(KubeCtlHorizontalAutoScaleOptions::buildCommand)
                        .forEach(command -> {
                            getLog().info(command);
                            runCommand(command);
                        });
            } else {
                getLog().info("No Horizontal Autoscale Options");
            }
        }
    }

}
