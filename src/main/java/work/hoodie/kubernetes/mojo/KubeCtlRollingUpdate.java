package work.hoodie.kubernetes.mojo;


import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import work.hoodie.kubernetes.common.KubeCtlRollingUpdateOptions;

import java.util.List;
import java.util.Objects;

import static java.util.Objects.nonNull;

@Mojo(name = "rolling-update", defaultPhase = LifecyclePhase.INSTALL)
public class KubeCtlRollingUpdate extends AbstractKubeCtlMojo {

    @Parameter
    private List<KubeCtlRollingUpdateOptions> kubeCtlRollingUpdateOptionses;

    public void execute() throws MojoExecutionException, MojoFailureException {
        if (!skipKubeCtl) {
            if (nonNull(kubeCtlRollingUpdateOptionses)) {
                getLog().debug(kubeCtlRollingUpdateOptionses.toString());
                kubeCtlRollingUpdateOptionses
                        .parallelStream()
                        .filter(Objects::nonNull)
                        .map(KubeCtlRollingUpdateOptions::buildRollingUpdateCommand)
                        .forEach(command -> {
                            getLog().info(command);
                            runCommand(command);
                        });
            } else {
                getLog().info("No Rolling Update Options");
            }
        }
    }

}
