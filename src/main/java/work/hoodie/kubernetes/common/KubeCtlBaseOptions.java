package work.hoodie.kubernetes.common;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import static org.apache.commons.lang.StringUtils.isNotBlank;

@Data
@ToString
@Slf4j
public class KubeCtlBaseOptions {
    //    --alsologtostderr                  log to standard error as well as files
    private boolean alsoLogToStdErr;
    //    --as string                        Username to impersonate for the operation
    private String as;
    //    --certificate-authority string     Path to a cert. file for the certificate authority
    private String certificateAuthority;
    //    --client-certificate string        Path to a client certificate file for TLS
    private String clientCertificate;
    //    --client-key string                Path to a client key file for TLS
    private String clientKey;
    //    --cluster string                   The name of the kubeconfig cluster to use
    private String cluster;
    //    --context string                   The name of the kubeconfig context to use
    private String context;
    //    --insecure-skip-tls-verify         If true, the server's certificate will not be checked for validity. This will make your HTTPS connections insecure
    private boolean insecureSkipTlsVerify;
    //            --kubeconfig string                Path to the kubeconfig file to use for CLI requests.
    private String kubeConfig;
    //            --log-backtrace-at traceLocation   when logging hits line file:N, emit a stack trace (default :0)
    private String logBacktraceAt;
    //    --log-dir string                   If non-empty, write log files in this directory
    private String logDir;
    //    --logtostderr                      log to standard error instead of files
    private boolean logToStdErr;
    //    --match-server-version             Require server version to match client version
    private boolean matchServerVersion;
    //    -n, --namespace string                 If present, the namespace scope for this CLI request
    private String namespace;
    //    --password string                  Password for basic authentication to the API server
    private String password;
    //    --request-timeout string           The length of time to wait before giving up on a single server request. Non-zero values should contain a corresponding time unit (e.g. 1s, 2m, 3h). A value of zero means don't timeout requests. (default "0")
    private String requestTimeout;
    //            -s, --server string                    The address and port of the Kubernetes API server
    private String server;
    //    --stderrthreshold severity         logs at or above this threshold go to stderr (default 2)
    private String stdErrThreshold;
    //    --token string                     Bearer token for authentication to the API server
    private String token;
    //    --user string                      The name of the kubeconfig user to use
    private String user;
    //    --username string                  Username for basic authentication to the API server
    private String username;
    //    -v, --v Level                          log level for V logs
    private String v;
    //    --vmodule moduleSpec               comma-separated list of pattern=N settings for file-filtered logging
    private String vmodule;

    public String buildCommand() {

        log.info("building Kubectl command");
        String command = "kubectl";
        if (alsoLogToStdErr) {
            command = command.concat(" --alsologtostderr");
        }
        if (isNotBlank(as)) {
            command = command.concat(" --as " + as);
        }
        if (isNotBlank(certificateAuthority)) {
            command = command.concat(" --certificate-authority " + certificateAuthority);
        }
        if (isNotBlank(clientCertificate)) {
            command = command.concat(" --client-certificate " + clientCertificate);
        }
        if (isNotBlank(clientKey)) {
            command = command.concat(" --client-key " + clientKey);
        }
        if (isNotBlank(cluster)) {
            command = command.concat(" --cluster " + cluster);
        }
        if (isNotBlank(context)) {
            command = command.concat(" --context " + context);
        }
        if (insecureSkipTlsVerify) {
            command = command.concat(" --insecure-skip-tls-verify");
        }
        if (isNotBlank(kubeConfig)) {
            command = command.concat(" --kubeconfig " + as);
        }
        if (isNotBlank(logBacktraceAt)) {
            command = command.concat(" --log-backtrace-at " + logBacktraceAt);
        }
        if (isNotBlank(logDir)) {
            command = command.concat(" --log-dir " + logDir);
        }
        if (logToStdErr) {
            command = command.concat(" --logtostderr");
        }
        if (matchServerVersion) {
            command = command.concat(" --match-server-version");
        }
        if (isNotBlank(namespace)) {
            command = command.concat(" --namespace " + namespace);
        }
        if (isNotBlank(password)) {
            command = command.concat(" --password " + password);
        }
        if (isNotBlank(requestTimeout)) {
            command = command.concat(" --request-timeout " + requestTimeout);
        }
        if (isNotBlank(server)) {
            command = command.concat(" --server " + server);
        }
        if (isNotBlank(stdErrThreshold)) {
            command = command.concat(" --stderrthreshold " + stdErrThreshold);
        }
        if (isNotBlank(token)) {
            command = command.concat(" --token " + token);
        }
        if (isNotBlank(user)) {
            command = command.concat(" --user " + user);
        }
        if (isNotBlank(username)) {
            command = command.concat(" --username " + username);
        }
        if (isNotBlank(v)) {
            command = command.concat(" --v " + v);
        }
        if (isNotBlank(vmodule)) {
            command = command.concat(" --vmodule " + vmodule);
        }
        return command;
    }

}
