package work.hoodie.kubernetes.common;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang.StringUtils.isBlank;
import static org.apache.commons.lang.StringUtils.isNotBlank;

@Data
@ToString
@Slf4j
public class KubeCtlRollingUpdateOptions extends KubeCtlBaseOptions {
    //    --container string              Container name which will have its image upgraded. Only relevant when --image is specified, ignored otherwise. Required when using --image on a multi-container pod
    private String container;
    //    --deployment-label-key string   The key to use to differentiate between two different controllers, default 'deployment'.  Only relevant when --image is specified, ignored otherwise (default "deployment")
    private String deploymentLabelKey;
    //    --dry-run                       If true, only print the object that would be sent, without sending it.
    private boolean dryRun;
    //    -f, --filename stringSlice          Filename or URL to file to use to createJsonFile the new replication controller.
    private String filename;
    //    --image string                  Image to use for upgrading the replication controller. Must be distinct from the existing image (either new image or new image tag).  Can not be used with --filename/-f
    private String image;
    //    --image-pull-policy string      Explicit policy for when to pull container images. Required when --image is same as existing image, ignored otherwise.
    private String imagePullPolicy;
    //    --no-headers                    When using the default or custom-column output format, don't print headers.
    private boolean noHeaders;
    //     -o, --output string                 Output format. One of: json|yaml|wide|name|custom-columns=...|custom-columns-file=...|go-template=...|go-template-file=...|jsonpath=...|jsonpath-file=... See custom columns [http://kubernetes.io/docs/user-guide/kubectl-overview/#custom-columns], golang template [http://golang.org/pkg/text/template/#pkg-overview] and jsonpath template [http://kubernetes.io/docs/user-guide/jsonpath].
    private String output;
    //    --output-version string         Output the formatted object with the given group version (for ex: 'extensions/v1beta1').
    private String outputVersion;
    //    --poll-interval duration        Time delay between polling for replication controller status after the update. Valid time units are "ns", "us" (or "µs"), "ms", "s", "m", "h". (default 3s)
    private String pullInterval;
    //    --rollback                      If true, this is a request to abort an existing rollout that is partially rolled out. It effectively reverses current and next and runs a rollout
    private boolean rollback;
    //    --schema-cache-dir string       If non-empty, load/store cached API schemas in this directory, default is '$HOME/.kube/schema' (default "~/.kube/schema")
    private String schemaCacheDir;
    //    -a, --show-all                      When printing, show all resources (default hide terminated pods.)
    private boolean showAll;
    //    --show-labels                   When printing, show all labels as the last column (default hide labels column)
    private boolean showLabels;
    //    --sort-by string                If non-empty, sort list types using this field specification.  The field specification is expressed as a JSONPath expression (e.g. '{.metadata.name}'). The field in the API resource specified by this JSONPath expression must be an integer or a string.
    private String sortBy;
    //    --template string               Template string or path to template file to use when -o=go-template, -o=go-template-file. The template format is golang templates [http://golang.org/pkg/text/template/#pkg-overview].
    private String template;
    //    --timeout duration              Max time to wait for a replication controller to update before giving up. Valid time units are "ns", "us" (or "µs"), "ms", "s", "m", "h". (default 5m0s)
    private String timeout;
    //    --update-period duration        Time to wait between updating pods. Valid time units are "ns", "us" (or "µs"), "ms", "s", "m", "h". (default 1m0s)
    private String updatePeriod;
    //    --validate                      If true, use a schema to validate the input before sending it (default true)
    private boolean validate;

    private String oldControllerName;

    private String newControllerName;

    public String buildRollingUpdateCommand() {
        log.info("building Kubectl rolling update command");
        String command = super.buildCommand();
        command = command.concat(" rolling-update");
        command = command.concat(" " + oldControllerName);
        if (isNotBlank(newControllerName)) {
            command = command.concat(" " + newControllerName);
        }
        if (isNotBlank(container)) {
            command = command.concat(" --container " + container);
        }
        if (isNotBlank(deploymentLabelKey)) {
            command = command.concat(" --deployment-label-key " + deploymentLabelKey);
        }
        if (dryRun) {
            command = command.concat(" --dry-run");
        }
        if (isNotBlank(filename)) {
            command = command.concat(" --filename " + filename);
        }
        if (isNotBlank(image)) {
            command = command.concat(" --image " + image);
        }
        if (isNotBlank(imagePullPolicy)) {
            command = command.concat(" --image-pull-policy " + imagePullPolicy);
        }
        if (noHeaders) {
            command = command.concat(" --no-headers");
        }
        if (isNotBlank(output)) {
            command = command.concat(" --output " + output);
        }
        if (isNotBlank(outputVersion)) {
            command = command.concat(" --output-version " + outputVersion);
        }
        if (isNotBlank(pullInterval)) {
            command = command.concat(" --pull-interval " + pullInterval);
        }
        if (rollback) {
            command = command.concat(" --rollback");
        }
        if (isNotBlank(schemaCacheDir)) {
            command = command.concat(" --schema-cache-dir " + schemaCacheDir);
        }
        if (showAll) {
            command = command.concat(" --show-all");
        }
        if (showAll) {
            command = command.concat(" --show-labels");
        }
        if (isNotBlank(sortBy)) {
            command = command.concat(" --sort-by " + sortBy);
        }
        if (isNotBlank(template)) {
            command = command.concat(" --template " + template);
        }
        if (isNotBlank(timeout)) {
            command = command.concat(" --timeout " + timeout);
        }
        if (isNotBlank(updatePeriod)) {
            command = command.concat(" --update-period " + updatePeriod);
        }
        if (validate) {
            command = command.concat(" --validate");
        }
        return command;
    }

    public String buildRollbackCommand() {
        log.info("building Kubectl rolling update rollback command");
        String command = super.buildCommand();
        command = command.concat(" rolling-update");
        if (isBlank(newControllerName)) {
            command = command.concat(" " + oldControllerName);
        } else {
            command = command.concat(" " + newControllerName);
        }
        command = command.concat(" --rollback");
        return command;
    }
}
