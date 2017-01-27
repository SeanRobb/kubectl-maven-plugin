package work.hoodie.kubernetes.common;

import lombok.Data;
import lombok.ToString;

import static org.apache.commons.lang.StringUtils.isBlank;

@Data
@ToString
public class KubeCtlHorizontalAutoScaleOptions extends KubeCtlBaseOptions {
    //    --cpu-percent int         The target average CPU utilization (represented as a percent of requested CPU) over all the pods. If it's not specified or negative, a default autoscaling policy will be used. (default -1)
    private Integer cpuPercent;
    //    --dry-run                 If true, only print the object that would be sent, without sending it.
    private boolean dryRun;
    //    -f, --filename stringSlice  KubeCtlHorizontalAutoScaleOptions  Filename, directory, or URL to files identifying the resource to autoscale.
    private String fileName;
    //    --generator string        The name of the API generator to use. Currently there is only 1 generator. (default "horizontalpodautoscaler/v1")
    private String generator;
    //    --max int                 The upper limit for the number of pods that can be set by the autoscaler. Required. (default -1)
    private Integer max;
    //    --min int                 The lower limit for the number of pods that can be set by the autoscaler. If it's not specified or negative, the server will apply a default value. (default -1)
    private Integer min;
    //    --no-headers              When using the default or custom-column output format, don't print headers.
    private boolean noHeaders;
    //    -o, --output string           Output format. One of: json|yaml|wide|name|custom-columns=...|custom-columns-file=...|go-template=...|go-template-file=...|jsonpath=...|jsonpath-file=... See custom columns [http://kubernetes.io/docs/user-guide/kubectl-overview/#custom-columns], golang template [http://golang.org/pkg/text/template/#pkg-overview] and jsonpath template [http://kubernetes.io/docs/user-guide/jsonpath].
    private String output;
    //    --output-version string   Output the formatted object with the given group version (for ex: 'extensions/v1beta1').
    private String outputVersion;
    //    --record                  Record current kubectl command in the resource annotation. If set to false, do not record the command. If set to true, record the command. If not set, default to updating the existing annotation value only if one already exists.
    private boolean record;
    //    -R, --recursive               Process the directory used in -f, --filename recursively. Useful when you want to manage related manifests organized within the same directory.
    private boolean recursive;
    //    --save-config             If true, the configuration of current object will be saved in its annotation. This is useful when you want to perform kubectl apply on this object in the future.
    private boolean saveConfig;
    //    -a, --show-all                When printing, show all resources (default hide terminated pods.)
    private boolean showAll;
    //    --show-labels             When printing, show all labels as the last column (default hide labels column)
    private boolean showLabels;
    //    --sort-by string          If non-empty, sort list types using this field specification.  The field specification is expressed as a JSONPath expression (e.g. '{.metadata.name}'). The field in the API resource specified by this JSONPath expression must be an integer or a string.
    private String sortBy;
    //    --template string         Template string or path to template file to use when -o=go-template, -o=go-template-file. The template format is golang templates [http://golang.org/pkg/text/template/#pkg-overview].
    private String template;

    private String type;

    private String name;

    public String buildCommand() {
        String command = super.buildCommand();

        command = command.concat(" autoscale ")
                .concat(type)
                .concat(" ")
                .concat(name);

        if (cpuPercent != null) {
            command = command.concat(" --cpu-percent " + cpuPercent);
        }
        if (dryRun) {
            command = command.concat("--dry-run");
        }
        if (!isBlank(fileName)) {
            command = command.concat(" --filename ").concat(fileName);
        }
        if (!isBlank(generator)) {
            command = command.concat(" --generator ").concat(generator);
        }
        if (max != null) {
            command = command.concat(" --max " + max);
        }
        if (min != null) {
            command = command.concat(" --min " + min);
        }
        if (!isBlank(name)) {
            command = command.concat(" --name ").concat(name);
        }
        if (noHeaders) {
            command = command.concat(" --no-headers ");
        }
        if (!isBlank(output)) {
            command = command.concat(" --output ").concat(output);
        }
        if (!isBlank(outputVersion)) {
            command = command.concat(" --output-version ").concat(outputVersion);
        }
        if (record) {
            command = command.concat(" --record ");
        }
        if (recursive) {
            command = command.concat(" --recursive ");
        }
        if (saveConfig) {
            command = command.concat(" --saveConfig ");
        }
        if (showAll) {
            command = command.concat(" --show-all ");
        }
        if (showLabels) {
            command = command.concat(" --show-labels ");
        }
        if (!isBlank(sortBy)) {
            command = command.concat(" --sort-by ").concat(sortBy);
        }
        if (!isBlank(template)) {
            command = command.concat(" --template ").concat(template);
        }
        return command;
    }

}
